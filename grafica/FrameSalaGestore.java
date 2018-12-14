package grafica;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import classi.Multisala;
import classi.Posto;
import classi.Sala;
import listeners.CloseListener;
import moduli.ModuloSala;

public class FrameSalaGestore extends JFrame{

	private static final long serialVersionUID = -3932710676857678276L;
	private Multisala multisala;
	private Sala sala;
	
	public FrameSalaGestore(Multisala m, Sala s)
	{
		this.multisala = m;
		this.sala = s;
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new CloseListener(multisala));
		add(createSalaPanel());
	}
	
	private JPanel createSalaPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(sala.getNumPosti()/sala.getNumColonne(), sala.getNumColonne()));
		for(Posto p : sala.getPosti())
		{
			panel.add(creaBottone(p, sala.getNumSala()));
		}
		return panel;

	}

	private JButton creaBottone(Posto p, int numSala) {
		
		JButton btn = new JButton(""+p.getLet()+" "+p.getNum());
		if(sala.getPosto(p.getLet(), p.getNum()).isDisponibile())
		{
			btn.setBackground(Color.GREEN);
		}
		else
		{
			btn.setBackground(Color.RED);
		}
		
		class ButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				if (p.isDisponibile())
					p.setIndisponibile();
				else
					p.setDisponibile();
				
				FrameGestore f = new FrameGestore(multisala);
				f.setVisible(true);
				dispose();
			}
		}
		
		btn.addActionListener(new ButtonListener());
		return btn;
	}
}
