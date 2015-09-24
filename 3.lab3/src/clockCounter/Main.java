import javax.swing.*;
public class Main {

	public static void main(String[] args) {
		ClockView view = new ClockView(	Integer.parseInt(args[0]),
										Integer.parseInt(args[1]),
										Integer.parseInt(args[2]));
		
		JFrame f = new JFrame("Clock");	
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(view);
		f.setLocation(100,100);
		f.pack();
		f.setVisible(true);
	}
}
