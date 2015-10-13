import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 2015-10-13.
 */
public class Memory extends JFrame{

    public int width, height, players, playerTurn;
    public int score[];

    public Memory(){

        //temporär bild hehe
        OvalIcon i1 = new OvalIcon(50, 50, Color.red, true);

        //Lägg till exceptions
        String input = JOptionPane.showInputDialog("Bredd?");
        width = Integer.valueOf(input);
        input = JOptionPane.showInputDialog("Höjd");
        height = Integer.valueOf(input);
        input = JOptionPane.showInputDialog("Antal spelare");
        players = Integer.valueOf(input);

        this.score = new int [players];


        JPanel main = new JPanel(new BorderLayout());
        JPanel playPanel = new JPanel(new GridLayout(players, 1));
        JPanel optionPanel = new JPanel(new FlowLayout());
        JPanel gamePanel = new JPanel(new GridLayout(width, height));

        JButton option1 = new JButton("NEW");
        JButton option2 = new JButton("QUIT");
        //option1.addActionListener();


            for(int i = 0; i < width*height; i++){
                gamePanel.add(new Kort(i1, Kort.Status.DOLT));
            }

            for(int i = 0; i < players; i++){
                playPanel.add(new Person(i + 1));
            }

        main.setBackground(Color.BLACK);

        optionPanel.add(option1);
        optionPanel.add(option2);
        optionPanel.setPreferredSize(new Dimension(100, 60));

        main.add(playPanel, BorderLayout.WEST);
        main.add(optionPanel, BorderLayout.SOUTH);
        main.add(gamePanel, BorderLayout.CENTER);

        add(main);

        setDefaultCloseOperation(3);
        setLocation(50, 50);
        setVisible(true);
        setSize(width * 80 + 100, height * 80 + 60);
        setResizable(false);
        }



    private class Person extends JPanel{
        public Person(int x){
            setLayout(new BorderLayout());

            //BYTA FÄRG?******
            setBackground(Color.ORANGE);
            setPreferredSize(new Dimension(100, height * 80));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel player = new JLabel("    Player " + x);
            JLabel scoren = new JLabel("    " + score[x-1]);

            add(scoren, BorderLayout.CENTER);
            add(player, BorderLayout.NORTH);
        }
    }
    /*private class ActionListener implements {

    }*/
    public static void main(String [] args){
        Memory memory = new Memory();}
}
