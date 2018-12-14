package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classi.Multisala;
import classi.Sala;
import listeners.CloseListener;
import moduli.ModuloSala;

public class FrameSelezioneSala extends JFrame{

	private static final long serialVersionUID = -8281658332905578861L;
	private Multisala multisala;
	
	public FrameSelezioneSala(Multisala m)
	{
		this.multisala = m;
		setSize(300, 200);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new CloseListener(multisala));
		createFrameSala();
	}
	
	private void createFrameSala()
	{
		JPanel panel = new JPanel();
		panel.add(new JLabel("Inserisci numero sala:" ));
		JTextField field = new JTextField(5);
		panel.add(field);
		JButton ok = new JButton("Ok");
		panel.add(ok);
		
		class okListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				ModuloSala modSala = new ModuloSala(multisala);
				Sala sala;
				if ((sala = modSala.getSala(Integer.parseInt(field.getText()))) == null)
				{
					JFrame framerr = new JFrame();
					framerr.setSize(500, 100);
					framerr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JLabel errore = new JLabel("Numero sala incorretto");
					framerr.add(errore);
					framerr.setVisible(true);
				}
				else
				{
					new FrameSalaGestore(multisala, sala).setVisible(true);
					dispose();
				}	
			}
		}
		ok.addActionListener(new okListener());
		add(panel);	
	}
}
