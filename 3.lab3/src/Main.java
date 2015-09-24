import javax.swing.*;
public class Main {

	public static void main(String[] args) {
		CounterInterface cm = new CounterModel();
		CounterControl control = new CounterControl(cm);
		
		JFrame f = new JFrame("Counter");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(control);
		f.setLocation(100,100);
		f.pack();
		f.setVisible(true); 
	}
}
