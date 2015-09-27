import javax.swing.*;
import java.awt.*;

public class CounterView extends JPanel {

	private CounterInterface ctr; // en räknare
	// eller private CounterModel ctr;
	private Font font;

	public CounterView(CounterInterface ctr) {
		this.ctr = ctr;
		setPreferredSize(new Dimension(190,130));
		setBackground(Color.BLACK);
		setOpaque(true);
		font = new Font(Font.MONOSPACED,Font.BOLD,140);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.setFont(font);
		int val = ctr.getValue();
		if (val < 10) g.drawString("0" + val,10,110);
		else g.drawString("" + val,10,110);
	}
}
