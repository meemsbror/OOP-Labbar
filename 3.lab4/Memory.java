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
    public Kort kort [];
    JPanel main,playPanel,optionPanel,gamePanel;
    File [] bilder = new File ("bildmap").listFiles();


    public Memory(){

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

        setDefaultCloseOperation(3);
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



        for(int i = 0; i<(pictureCount/2);i++){
            kort[i] = new Kort(new ImageIcon(bilder[i].getPath()), Kort.Status.DOLT);
            kort[pictureCount-(1+i)] = kort[i].copy();
        }

        Verktyg.slumpOrdning(this.kort);

        for(Kort k: kort){
            k.setStatus(Kort.Status.DOLT);
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

            if(str.equals("exit")){
                System.exit(0);
            }else if(str.equals("new")){
                  nyttSpel();
            }
        }
    }



    public static void main(String [] args){
        Memory memory = new Memory();}
}
