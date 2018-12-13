package grafica;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameErrore extends JFrame{

	public FrameErrore(Exception e)
	{
		setSize(100, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel errore = new JLabel(e.getMessage());
		add(errore);
	}
}
