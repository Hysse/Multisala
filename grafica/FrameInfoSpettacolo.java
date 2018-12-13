package grafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import classi.Cliente;
import classi.Multisala;
import classi.Spettacolo;
import classi.Utente;
import eccezioni.OraPrenotazioneException;
import eccezioni.PostoIndisponibileException;
import listeners.CloseListener;
import moduli.ModuloBiglietto;
import moduli.ModuloCliente;

public class FrameInfoSpettacolo extends JFrame{

	private static final long serialVersionUID = 1L;
	private ModuloCliente modCli;
	private Spettacolo spettacolo;
	private Multisala multisala;
	public FrameInfoSpettacolo(Multisala multisala, Cliente c,Spettacolo s)
	{
		this.modCli = new ModuloCliente(multisala, c);
		this.spettacolo = s;
		this.multisala = multisala;
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new CloseListener(multisala));
		add(createSpettacoloPanel());
	}

	private JPanel createSpettacoloPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JTextArea display = new JTextArea();
		display.setEditable(false);
		JScrollPane scroll = new JScrollPane(display);
		display.setText(spettacolo.infoDettagliate());
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(createSpettacoloOptionPanel(display),BorderLayout.EAST);
		return panel;
	}
	
	private JPanel createSpettacoloOptionPanel(JTextArea display)
	{
		JPanel panel = new JPanel();
		JButton prenota = new JButton("Prenota");
		JButton acquista = new JButton("Acquista");
		JButton back = new JButton("Back");
		class PrenotaListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				ModuloBiglietto modBig = new ModuloBiglietto(multisala, modCli.getCliente());
				if(modBig.alreadyPresente(spettacolo))
				{
					JFrame framerr = new JFrame();
					framerr.setSize(100, 100);
					framerr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JLabel errore = new JLabel("Biglietto già prenotato/acquistato");
					framerr.add(errore);
					framerr.setVisible(true);
				}
				else
				{
					new FrameSalaPrenotazioni(false,multisala, modCli.getCliente(), spettacolo).setVisible(true);
					dispose();
				}
			}
		}
		class AcquistaListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				ModuloBiglietto modBig = new ModuloBiglietto(multisala, modCli.getCliente());
				if(modBig.hasPrenotazione(spettacolo))
				{
					try {
						modBig.acquistoConPrenotazione(modBig.getBiglietto(spettacolo.getID()));
						new FrameCliente(modCli.getCliente(),multisala).setVisible(true);
						dispose();
					} catch (PostoIndisponibileException | OraPrenotazioneException e1) {
						JFrame errore = new FrameErrore(multisala,e1);
						errore.setVisible(true);
						errore.addWindowListener(new CloseListener(multisala));
					}
				}
				else
				{
					new FrameSalaPrenotazioni(true,multisala, modCli.getCliente(), spettacolo).setVisible(true);
					dispose();
				}
			}
		}
		class BackListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				new FrameCliente(modCli.getCliente(),multisala).setVisible(true);
				dispose();
			}
		}
		ActionListener backlistener = new BackListener();
		ActionListener acquistalistener = new AcquistaListener();
		ActionListener prenotalistener = new PrenotaListener();
		acquista.addActionListener(acquistalistener);
		prenota.addActionListener(prenotalistener);
		back.addActionListener(backlistener);
		panel.add(acquista);
		panel.add(prenota);
		panel.add(back);
		return panel;
	}
}
