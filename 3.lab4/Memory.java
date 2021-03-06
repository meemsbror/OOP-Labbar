import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

public class Memory extends JFrame{

    private boolean sameCard=false;

    private int width, height, playerTurn, pictureCount, score[];

    //Alla spelare
    public Person [] players;

    //Alla kort
    public Kort kort [], activeKort;

    boolean timerStarted,kortShowing = false;
    JPanel main,playPanel,optionPanel,gamePanel;

    //Alla bildfiler
    File [] bilder = new File ("bildmap").listFiles();

    Timer timer = new Timer(1500,new Listener());


    public Memory(){

        timerStarted=false;
        int players;

        players=userInput();
        createBoard(players);

        /*
        Skapar ett nytt spel. Återställer vissa värden. Till exempel:
        Vilka bilder som visas, poängen spelarna har och lägger ut de nya korten
        (ändrar allas kort status till DOLT)
         */
        nyttSpel();


        }

    //Skapar brädet.
    public void createBoard(int players){
        /*
        Initierar arrayer med kort och spelare med rätt längd.
        */
        pictureCount = width * height;
        this.score = new int [players];
        this.kort = new Kort [pictureCount];
        this.players = new Person[players];

        main = new JPanel(new BorderLayout());
        playPanel = new JPanel(new GridLayout(players, 1));
        optionPanel = new JPanel(new FlowLayout());
        gamePanel = new JPanel(new GridLayout(height,width));

        /*
        Lägger till spelare i players arrayen.
        */
        for(int i = 0; i < players; i++){
            this.players[i]=new Person(i + 1);
            playPanel.add(this.players[i]);
        }

        /*
        Byter färg på spelaren vars tur det är, altså spelare 1 just nu.
         */
        this.players[playerTurn].changeTurn(true);

        createButtons();


        //Lägger till
        main.add(playPanel, BorderLayout.WEST);
        main.add(optionPanel, BorderLayout.SOUTH);
        main.add(gamePanel, BorderLayout.CENTER);

        add(main);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(50,50);
        setSize(width * 150 + 100, height * 150);
        setResizable(false);
        setVisible(true);

    }
    /*
    Skapar 2 knappar (new och quit) samt ger dem actionListener och actionCommand.
    */
    public void createButtons(){
        Buttons buttons = new Buttons();
        JButton option1 = new JButton("NEW");
        JButton option2 = new JButton("QUIT");
        option1.setActionCommand("new");
        option1.addActionListener(buttons);
        option2.setActionCommand("exit");
        option2.addActionListener(buttons);
        optionPanel.add(option1);
        optionPanel.add(option2);
    }
    /*
    Tar indata och kollar om datan är giltig. Om den inte är det
    skriver den ut vad som är fel och ber om giltig indata.
    */
    public int userInput(){
        int players;
        while(true) {
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

            if(((height*width) % 2 == 0 && height*width <= 36) && (players<10)){
                return players;
            }else if(!((height*width) % 2 == 0 )){
                JOptionPane.showMessageDialog(null, "Inte jämnt antal ju..");
            }else if(!(height*width <= 36)){
                JOptionPane.showMessageDialog(null, "Så många bilder har vi inte!");
            }else{
                JOptionPane.showMessageDialog(null, "Lite många spelare där eller..?");
            }
        }
    }
    //Denna metod säger om en sträng endast innehåller siffror.
    public boolean isNumerical(String temp) {
        String allNumbers = "1234567890";
        if(temp!=null) {
            if (temp.length() != 0) {
                for (int i = 0; i < temp.length(); i++) {
                    if ((allNumbers.indexOf(temp.charAt(i)) == -1)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
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

        //Slumpar vilka bilder som ska vara med samt sparar bilderna samt dubbletten i en array
        Verktyg.slumpOrdning(bilder);
        for(int i = 0; i<(pictureCount/2);i++){
            String [] tmp = bilder[i].getName().split("\\.");
            int j=1;
            if(tmp.length>1 && (tmp[1].equals("jpg")||tmp[1].equals("png"))) {
                kort[i] = new Kort(new ImageIcon(bilder[i].getPath()), Kort.Status.DOLT);
            }
            else{
                kort[i] = new Kort(new ImageIcon(bilder[bilder.length-j].getPath()), Kort.Status.DOLT);
                j++;
            }
            kort[pictureCount - (1 + i)] = kort[i].copy();
            kort[i].addActionListener(new Listener());
            kort[pictureCount - (1 + i)].addActionListener(new Listener());
        }
        Verktyg.slumpOrdning(kort);
        //Lägger ut bilderna på spelplanen
        for(Kort kor:kort){
            gamePanel.add(kor);
        }
        revalidate();
    }


    /*
    En privat klass som beskriver en spelare.
     */
    private class Person extends JPanel{
        JLabel scoren;
        JLabel player;
        Color rnd;
        public Person(int x){

            setLayout(new BorderLayout());
            Random random = new Random();

            //Random färg är kul.
            rnd = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
            setBackground(rnd);
            setPreferredSize(new Dimension(100, height * 80));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            player = new JLabel("    Player " + x);
            scoren = new JLabel("    " + score[x-1]);

            add(scoren, BorderLayout.CENTER);
            add(player, BorderLayout.NORTH);
        }
        //Updaterar poängen för en specifik spelare
        public void updateScore(int x){
            this.remove(scoren);
            scoren = new JLabel("    " + score[x-1]);
            add(scoren, BorderLayout.CENTER);
            this.repaint();
            this.revalidate();
        }

        //Byter färg på bakgrunden på den spelare vars tur det är.
        public void changeTurn(boolean b){
            if(b){
                this.setBackground(Color.red);
            }
            else{
                this.setBackground(rnd);
            }
        }
    }


    /*
    ActionListenern som knapparna new och exit anropar.
     */
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
    /*
    ActionListenern som korten samt timern anropar. Kollar om två kort som visas är densamma osv.
    */
    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if((e.getSource() instanceof Kort) && !timerStarted){
                Kort k = (Kort)e.getSource();
                if(k!=activeKort && k.getStatus() != Kort.Status.SAKNAS) {
                    if (kortShowing) {
                        //Hanterar i fall de två kort som visas är likadana och kollar även ifall någon har vunnit.
                        timer.restart();
                        timer.start();
                        timerStarted = true;
                        if (k.sammaBild(activeKort)) {
                            sameCard=true;
                        }
                        else {
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

            //Hanterar Timerns actionevent
            }else if (e.getSource() instanceof Timer){
                if(sameCard){
                    for(Kort k:kort){
                        if(k.getStatus()==Kort.Status.SYNLIGT){
                            k.setStatus(Kort.Status.SAKNAS);
                        }
                    }
                    kortShowing = false;
                    score[playerTurn]++;
                    players[playerTurn].updateScore(playerTurn+1);
                    boolean temp = false;
                    for(Kort kot : kort){
                        if(!(kot.getStatus() == Kort.Status.SAKNAS)) {
                            temp = true;
                            break;
                        }
                    }
                    if (!temp) {
                        int tempInt = 0;
                        String winners="";
                        String [] winnersArr;
                        for(int i = 0; score.length-1 > i; i++){
                            if(score[i]>tempInt) {
                                tempInt = score[i];
                            }
                        }
                        for(int i = 0; score.length > i; i++){
                            if(score[i]==tempInt){
                                winners+=(i+1)+" ";
                            }
                        }
                        winners.trim();
                        winnersArr=winners.split(" ");
                        if(winnersArr.length>1){
                            winners="";
                            for(int i=0;i<winnersArr.length-2;i++){
                                winners+=winnersArr[i]+",";
                            }
                            winners+= winnersArr[winnersArr.length-2]+ " och " +winnersArr[winnersArr.length-1]+" fick lika mycket poäng";
                        }else{
                            winners+="vann";
                        }

                        int dialogButton = JOptionPane.showConfirmDialog(null, "Spelare nummer "
                                        + (winners)
                                        + "\nVill ni spela igen? ;)",
                                "Hejja",
                                JOptionPane.YES_NO_OPTION);
                        if(dialogButton == JOptionPane.YES_OPTION) {
                            nyttSpel();
                        }else{
                            System.exit(3);
                        }
                    }
                }

                for(Kort ko : kort) {
                    if(ko.getStatus() == Kort.Status.SYNLIGT) {
                        ko.setStatus(Kort.Status.DOLT);

                    }
                }
                sameCard=false;
                timer.stop();
                timerStarted=false;
                kortShowing=false;
            }
        }

    }



    public static void main(String [] args){
        Memory memory = new Memory();}
}
