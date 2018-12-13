package grafica;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classi.Cliente;
import classi.Multisala;
import classi.Posto;
import classi.Spettacolo;
import eccezioni.BigliettoPresenteException;
import eccezioni.OraPrenotazioneException;
import eccezioni.PostoIndisponibileException;
import eccezioni.PostoNonEsistenteException;
import eccezioni.PostoOccupatoException;
import listeners.CloseListener;
import moduli.ModuloBiglietto;
import moduli.ModuloSala;

public class FrameSalaPrenotazioni extends JFrame{

	private Multisala multisala;
	private Cliente cliente;
	private Spettacolo spettacolo;
	private boolean perAcquistare;
	
	public FrameSalaPrenotazioni(boolean perAcquistare,Multisala multisala,Cliente cliente,Spettacolo spettacolo)
	{
		this.multisala = multisala;
		this.cliente = cliente;
		this.spettacolo = spettacolo;
		this.perAcquistare = perAcquistare;
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new CloseListener(multisala));
		add(createSalaPanel());
	}
	
	private JPanel createSalaPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(spettacolo.getSala().getNumPosti()/spettacolo.getSala().getNumColonne(),spettacolo.getSala().getNumColonne()));
		for(Posto p : spettacolo.getSala().getPosti())
		{
			panel.add(creaBottone(p,spettacolo.getSala().getNumSala()));
		}
		return panel;
	}
	
	private JButton creaBottone(Posto p,int idSala)
	{
		JButton btn = new JButton(""+p.getLet()+" "+p.getNum());
		ModuloSala modSala = new ModuloSala(multisala);
		if(!modSala.getSala(idSala).getPosto(p.getLet(), p.getNum()).isDisponibile())
		{
			btn.setBackground(Color.RED);
		}
		else
		{
			if(p.isLibero())
				btn.setBackground(Color.GREEN);
			else
				btn.setBackground(Color.ORANGE);
		}
		class ButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!modSala.getSala(idSala).getPosto(p.getLet(), p.getNum()).isDisponibile())
				{
					JFrame framerr = new JFrame();
					framerr.setSize(100, 100);
					framerr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JLabel errore = new JLabel("Posto Indisponibile!!!");
					framerr.add(errore);
					framerr.setVisible(true);
				}
				else
				{
					if(p.isLibero())
					{
						if(perAcquistare)
						{
							ModuloBiglietto modBig = new ModuloBiglietto(multisala, cliente);
							try {
								modBig.acquistoDiretto(spettacolo, p.getLet(), p.getNum());
								new FrameCliente(cliente,multisala).setVisible(true);
								dispose();
							} catch (PostoIndisponibileException | OraPrenotazioneException | PostoOccupatoException
									| PostoNonEsistenteException | BigliettoPresenteException e1) {
								JFrame errore = new FrameErrore(multisala,e1);
								errore.setVisible(true);
								errore.addWindowListener(new CloseListener(multisala));
							}
						}
						else
						{
							ModuloBiglietto modBig = new ModuloBiglietto(multisala, cliente);
							try {
								modBig.addPrenotazione(spettacolo, p.getLet(), p.getNum());
								new FrameCliente(cliente,multisala).setVisible(true);
								dispose();
							} catch (PostoIndisponibileException | OraPrenotazioneException | PostoNonEsistenteException
									| BigliettoPresenteException e1) {
								JFrame errore = new FrameErrore(multisala,e1);
								errore.setVisible(true);
								errore.addWindowListener(new CloseListener(multisala));
							}
						}
					}
					else
					{
						JFrame framerr = new JFrame();
						framerr.setSize(100, 100);
						framerr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						JLabel errore = new JLabel("Posto Occupato!!!");
						framerr.add(errore);
						framerr.setVisible(true);
					}
				}
			}
		}
		ActionListener btnlistener = new ButtonListener();
		btn.addActionListener(btnlistener);
		return btn;
	}
}
