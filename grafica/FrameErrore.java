package grafica;

import javax.swing.JFrame;
import javax.swing.JLabel;

import classi.Multisala;
import listeners.CloseListener;

public class FrameErrore extends JFrame{

	public FrameErrore(Multisala multisala,Exception e)
	{
		setSize(500, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new CloseListener(multisala));
		JLabel errore = new JLabel(e.getMessage());
		add(errore);
	}
}
