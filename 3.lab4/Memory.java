import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 2015-10-13.
 */
public class Memory extends JFrame{

    public int width, height, players, playerTurn;
    public int score[];
    public Kort kort [][];

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
        this.kort = new Kort [width] [height];

        JPanel main = new JPanel(new BorderLayout());
        JPanel playPanel = new JPanel(new GridLayout(players, 1));
        JPanel optionPanel = new JPanel(new FlowLayout());
        JPanel gamePanel = new JPanel(new GridLayout(width, height));
        Buttons buttons = new Buttons();

        JButton option1 = new JButton("NEW");
        JButton option2 = new JButton("QUIT");
        option1.setActionCommand("new");
        option1.addActionListener(buttons);
        option2.setActionCommand("exit");
        option2.addActionListener(buttons);

        nyttSpel();

            for(int i = 0; i < players; i++){
                playPanel.add(new Person(i + 1));
            }

        main.setBackground(Color.BLACK);

        optionPanel.add(option1);
        optionPanel.add(option2);

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

    public void nyttSpel(){
        //Nollställer score
        for(int i = 0; i < score.length;i++){
            score[i]=0;
        }
        for(int i = 0; i < width; i++){
            for(int k = 0; k < height; k++){
            }
        }
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


    private class Buttons implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            System.out.println(str);
            if(str.equals("exit")){
                System.exit(0);
            }else if(str.equals("new")){
                  //todo
            }
        }
    }

    public static void main(String [] args){
        Memory memory = new Memory();}
}
