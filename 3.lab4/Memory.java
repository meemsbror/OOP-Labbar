import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

public class Memory extends JFrame{
    public int width = 2;
    public int height = 6;
    public int players = 2;
    public int playerTurn,pictureCount;
    public int score[];
    public Kort kort [], activeKort;
    boolean timerStarted,kortShowing = false;
    JPanel main,playPanel,optionPanel,gamePanel;
    File [] bilder = new File ("bildmap").listFiles();
    Timer timer = new Timer(1500,new CardListener());


    public Memory(){
        timerStarted=false;
        //Lägg till exceptions
        /*
        String input = JOptionPane.showInputDialog("Bredd?");
        width = Integer.valueOf(input);
        input = JOptionPane.showInputDialog("Höjd");
        height = Integer.valueOf(input);
        input = JOptionPane.showInputDialog("Antal spelare");
        players = Integer.valueOf(input);
        */
        pictureCount=width*height;
        this.score = new int [players];
        this.kort = new Kort [pictureCount];

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
            playPanel.add(new Person(i + 1));
        }

        nyttSpel();

        main.setBackground(Color.BLACK);

        optionPanel.add(option1);
        optionPanel.add(option2);

        main.add(playPanel, BorderLayout.WEST);
        main.add(optionPanel, BorderLayout.SOUTH);
        main.add(gamePanel, BorderLayout.CENTER);

        add(main);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(50,50);
        setVisible(true);
        setSize(width * 150+100, height * 150);
        setResizable(true);
        }

    public void nyttSpel(){
        //Tar bort alla objekt i gamePanel
        gamePanel.removeAll();

        //Nollställer score
        for(int sc:score){
            sc=0;
        }


        Verktyg.slumpOrdning(bilder);
        for(int i = 0; i<(pictureCount/2);i++){
            kort[i] = new Kort(new ImageIcon(bilder[i].getPath()), Kort.Status.DOLT);
            kort[pictureCount-(1+i)] = kort[i].copy();
            kort[i].addActionListener(new CardListener());
            kort[pictureCount-(1+i)].addActionListener(new CardListener());
        }

        Verktyg.slumpOrdning(kort);

        for(Kort kor:kort){
            gamePanel.add(kor);
        }
        revalidate();
    }

    private class Person extends JPanel{
        public Person(int x){
            setLayout(new BorderLayout());
            Random random = new Random();
            Color rnd = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
            //BYTA FÄRG?******
            setBackground(rnd);
            setPreferredSize(new Dimension(100, height * 80));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel player = new JLabel("    Player " + x);
            JLabel scoren = new JLabel("    " + score[x-1]);

            add(scoren, BorderLayout.CENTER);
            add(player, BorderLayout.NORTH);
        }
    }


    private class Buttons implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            System.out.println(str);
            if(str.equals("exit")){
                System.exit(0);
            }else if(str.equals("new")){
                  nyttSpel();
            }
        }
    }
    private class CardListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("actionperformed");
            System.out.println(e.getSource().getClass());
            if(e.getSource().getClass().toString().equals("class Kort")&&!timerStarted){

                Kort k = (Kort)e.getSource();
                if(k!=activeKort) {
                    if (kortShowing) {
                        if (k.sammaBild(activeKort)) {
                            k.setStatus(Kort.Status.SAKNAS);
                            activeKort.setStatus(Kort.Status.SAKNAS);
                            kortShowing = false;
                            score[playerTurn]++;
                        } else {
                            timer.restart();
                            timer.start();
                            timerStarted = true;
                            playerTurn=(playerTurn+1)%players;
                        }
                    } else {
                        activeKort = k;
                    }
                    if (!(k.getStatus() == Kort.Status.SAKNAS)) {
                        k.setStatus(Kort.Status.SYNLIGT);
                        kortShowing = true;
                    }
                }
                playPanel.repaint();

            }else if (e.getSource().getClass().toString().equals("class javax.swing.Timer")){
                System.out.println("timern");
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
