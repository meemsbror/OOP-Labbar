import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

public class Memory extends JFrame{
    public int width = 2;
    public int height = 3;
    public Person [] players;
    public int playerTurn,pictureCount;
    public int score[];
    public Kort kort [], activeKort;
    boolean timerStarted,kortShowing = false;
    JPanel main,playPanel,optionPanel,gamePanel;
    File [] bilder = new File ("bildmap").listFiles();
    Timer timer = new Timer(1500,new Listener());


    public Memory(){
        timerStarted=false;
        int players = 2;
        while(true) {
                //Lägg till exceptions
            String input = JOptionPane.showInputDialog("Bredd?");
            if(!isNumerical(input)) {
                JOptionPane.showMessageDialog(null, "Var god och mata enbart in siffror");
                continue;
            }
            width = Integer.valueOf(input);
            input = JOptionPane.showInputDialog("Höjd");
            if(!isNumerical(input)) {
                JOptionPane.showMessageDialog(null, "Var god och mata enbart in siffror");
                continue;
            }
            height = Integer.valueOf(input);
            input = JOptionPane.showInputDialog("Antal spelare");
            if(!isNumerical(input)) {
                JOptionPane.showMessageDialog(null, "Var god och mata enbart in siffror");
                continue;
            }
            players = Integer.valueOf(input);
            if((height*width) % 2 == 0 && height*width <= 36){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Inte jämnt antal ju..");
            }
        }
        pictureCount=width*height;
        this.score = new int [players];
        this.kort = new Kort [pictureCount];
        this.players = new Person[players];

        main = new JPanel(new BorderLayout());
        playPanel = new JPanel(new GridLayout(players, 1));
        optionPanel = new JPanel(new FlowLayout());
        gamePanel = new JPanel(new GridLayout(height,width));


        Buttons buttons = new Buttons();        //ButtonsListener
        JButton option1 = new JButton("NEW");
        JButton option2 = new JButton("QUIT");
        option1.setActionCommand("new");
        option1.addActionListener(buttons);
        option2.setActionCommand("exit");
        option2.addActionListener(buttons);




        for(int i = 0; i < players; i++){
            this.players[i]=new Person(i + 1);
            playPanel.add(this.players[i]);
        }
        this.players[playerTurn].changeTurn(true);

        nyttSpel();
        optionPanel.add(option1);
        optionPanel.add(option2);

        main.add(playPanel, BorderLayout.WEST);
        main.add(optionPanel, BorderLayout.SOUTH);
        main.add(gamePanel, BorderLayout.CENTER);

        add(main);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(50,50);
        setSize(width * 150 + 100, height * 150);
        setVisible(true);
        setResizable(false);
        }
    
    public boolean isNumerical(String aTemporaryStringJustForThisMethodBecauseWeDontNeedItLaterYouKnow) {
        if(aTemporaryStringJustForThisMethodBecauseWeDontNeedItLaterYouKnow!=null) {
            for (int i = 0; i < aTemporaryStringJustForThisMethodBecauseWeDontNeedItLaterYouKnow.length(); i++) {
                switch (aTemporaryStringJustForThisMethodBecauseWeDontNeedItLaterYouKnow.charAt(i)) {
                    case '1':
                        break;
                    case '2':
                        break;
                    case '3':
                        break;
                    case '4':
                        break;
                    case '5':
                        break;
                    case '6':
                        break;
                    case '7':
                        break;
                    case '8':
                        break;
                    case '9':
                        break;
                    case '0':
                        break;
                    default:
                        return false;
                }
                return true;
            }
        }
        return false;
    }

    public void nyttSpel(){
        kortShowing = false;
        timerStarted = false;
        timer.stop();
        activeKort = null;
        //Tar bort alla objekt i gamePanel
        gamePanel.removeAll();

        //Nollställer score
        for(int i = 0; i<players.length;i++){
            score[i]=0;
            players[i].updateScore(i+1);
        }


        Verktyg.slumpOrdning(bilder);
        for(int i = 0; i<(pictureCount/2);i++){
            kort[i] = new Kort(new ImageIcon(bilder[i].getPath()), Kort.Status.DOLT);
            kort[pictureCount-(1+i)] = kort[i].copy();
            kort[i].addActionListener(new Listener());
            kort[pictureCount-(1+i)].addActionListener(new Listener());
        }
        System.out.println(bilder.length);

        Verktyg.slumpOrdning(kort);

        for(Kort kor:kort){
            gamePanel.add(kor);
        }
        revalidate();
    }

    private class Person extends JPanel{
        JLabel scoren;
        JLabel player;
        Color rnd;
        public Person(int x){
            setLayout(new BorderLayout());
            Random random = new Random();
            rnd = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
            setBackground(rnd);
            setPreferredSize(new Dimension(100, height * 80));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            player = new JLabel("    Player " + x);
            scoren = new JLabel("    " + score[x-1]);

            add(scoren, BorderLayout.CENTER);
            add(player, BorderLayout.NORTH);
        }
        public void updateScore(int x){
            this.remove(scoren);
            scoren = new JLabel("    " + score[x-1]);
            add(scoren, BorderLayout.CENTER);
            this.repaint();
            this.revalidate();
        }
        public void changeTurn(boolean b){
            if(b){
                this.setBackground(Color.red);
            }
            else{
                this.setBackground(rnd);
            }
        }
    }


    private class Buttons implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            if(str.equals("exit")){
                System.exit(0);
            }else if(str.equals("new")){
                  nyttSpel();
            }
        }
    }
    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().getClass().toString().equals("class Kort")&&!timerStarted){

                Kort k = (Kort)e.getSource();
                if(k!=activeKort && k.getStatus() != Kort.Status.SAKNAS) {
                    if (kortShowing) {
                        if (k.sammaBild(activeKort)) {
                            k.setStatus(Kort.Status.SAKNAS);
                            activeKort.setStatus(Kort.Status.SAKNAS);
                            kortShowing = false;
                            score[playerTurn]++;
                            players[playerTurn].updateScore(playerTurn+1);
                            //Detta är buggat, om spelare 2 drar kortet och har mindre poäng än spelare 1 vinner
                            //spelare 2 ändå.
                            boolean temp = false;
                            for(Kort kot : kort){
                                if(!(kot.getStatus() == Kort.Status.SAKNAS)) {
                                    temp = true;
                                    break;
                                }
                            }
                            if (!temp) {
                                int tempInt = 0;
                                int tempI;
                                for(int i = 0; score.length-1 > i; i++){
                                    if(score[i]>tempInt) {
                                        tempInt = score[i];
                                        tempI = i;
                                    }
                                }
                                int dialogButton = JOptionPane.showConfirmDialog(null, "Spelare nummer " + (playerTurn+1) + " vann\nVill ni spela igen? ;)","Hejja", JOptionPane.YES_NO_OPTION);
                                if(dialogButton == JOptionPane.YES_OPTION) {
                                    nyttSpel();
                                }else{
                                    System.exit(3);
                                }
                            }
                        } else {
                            timer.restart();
                            timer.start();
                            timerStarted = true;
                            players[playerTurn].changeTurn(false);
                            playerTurn=(playerTurn+1)%players.length;
                            players[playerTurn].changeTurn(true);
                            activeKort=null;
                        }
                    } else {
                        activeKort = k;
                    }
                    if (!(k.getStatus() == Kort.Status.SAKNAS)) {
                        k.setStatus(Kort.Status.SYNLIGT);
                        kortShowing = true;
                    }
                }


            }else if (e.getSource().getClass().toString().equals("class javax.swing.Timer")){
                for(Kort ko : kort) {
                    if(ko.getStatus() == Kort.Status.SYNLIGT) {
                        ko.setStatus(Kort.Status.DOLT);

                    }
                }
                timer.stop();
                timerStarted=false;
                kortShowing=false;
            }
        }

    }



    public static void main(String [] args){
        Memory memory = new Memory();}
}
