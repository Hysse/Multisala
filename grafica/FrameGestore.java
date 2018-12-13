package grafica;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import classi.Multisala;
import classi.Spettacolo;
import listeners.CloseListener;
import moduli.ModuloGestore;

public class FrameGestore extends JFrame{
	
	private static final long serialVersionUID = 1455478804991929894L;
	private ModuloGestore modGes;
	
	// Menu Operazione
	private JMenu menuOperazione;
	
	private JMenu menuOrdina;
	private JMenuItem postiDisponibili;
	private JMenuItem complessivo;
	
	private JMenuItem sconto;
	
	private JMenuItem sale;
	
	private JMenu menuIncasso;
	private JMenuItem totale;
	private JMenuItem perFilm;
	
	private JMenuItem aggiungiSpettacolo;
	
	private JMenuItem logOut;
	// Area di testo.
	private JTextArea textMultisala;
	// Input
	private JLabel labelMostraInformazioni;
	private JTextField fieldIdSpettacolo;
	// Button
	private JButton cercaId;
	//Jpanel
	private JPanel panelCentro;
	private JPanel panelEst;
	private JPanel panelResult;
	private JScrollPane scroll;
	
	public FrameGestore(Multisala m)
	{
		this.modGes = new ModuloGestore(m);
		setSize(700,300);
		setName("Gestore");
		createMenu();
		createTextArea();
		createCercaSpettacolo();
		createPanelResult();
		add(panelResult);
		addWindowListener(new CloseListener(m));
	}
	
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar = new JMenuBar();
		menuOperazione = new JMenu("Operazioni");
		menuOrdina = new JMenu("Ordina spettacoli");
		menuIncasso = new JMenu("Incassi");
		createMenuItemComplessivo();
		createMenuItemDisponibili();
		createMenuItemTotale();
		createMenuItemPerFilm();
		createMenuItemAggiungi();
		menuOperazione.add(menuOrdina);
		menuOperazione.add(menuIncasso);
		createMenuItemSconto();
		createMenuItemSale();
		createMenuItemLogOut();
		menuBar.add(menuOperazione);
		setJMenuBar(menuBar);
	}
	
	private void createMenuItemComplessivo()
	{
		complessivo = new JMenuItem("Complessivo");
		menuOrdina.add(complessivo);
		
		class ComplessivoListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				stampaIniziale();
			}
		}
		
		ComplessivoListener c = new ComplessivoListener();
		complessivo.addActionListener(c);
	}
	
	private void createMenuItemDisponibili()
	{
		postiDisponibili = new JMenuItem("Posti disponibili");
		menuOrdina.add(postiDisponibili);
		
		class DisponibiliListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				
				textMultisala.setText("");
				for (Spettacolo s: modGes.spettacoliPostiDisponibili())
				{
					textMultisala.append("Id Spettacolo: " + s.getID() +
							", Numero posti disponibili: " + s.getSala().getNumPostiDisponibili() + "\n");
				}
			}
		}
		
		DisponibiliListener c = new DisponibiliListener();
		postiDisponibili.addActionListener(c);
	}
	
	private void createMenuItemSconto()
	{
		sconto = new JMenuItem("Sconto");
		menuOperazione.add(sconto);
		
		class ScontoListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		
		ScontoListener s = new ScontoListener();
		sconto.addActionListener(s);
	}
	
	private void createMenuItemSale()
	{
		sale = new JMenuItem("Sale");
		menuOperazione.add(sale);
		
		class SaleListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		
		SaleListener s = new SaleListener();
		sale.addActionListener(s);
	}
	
	private void createMenuItemTotale()
	{
		totale = new JMenuItem("Totale");
		menuIncasso.add(totale);
		
		class TotaleListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		
		TotaleListener t = new TotaleListener();
		totale.addActionListener(t);
	}
	
	private void createMenuItemAggiungi()
	{
		aggiungiSpettacolo = new JMenuItem("Aggiungi Spettacolo");
		menuOperazione.add(aggiungiSpettacolo);
		
		class AggiungiSpettacoloListener implements ActionListener
		{

			public void actionPerformed(ActionEvent e) {
				
			}
			
		}
		
		AggiungiSpettacoloListener a = new AggiungiSpettacoloListener();
		aggiungiSpettacolo.addActionListener(a);
	}
	
	private void createMenuItemPerFilm()
	{
		perFilm = new JMenuItem("Per film");
		menuIncasso.add(perFilm);
		
		class IncassoPerFilmListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		
		IncassoPerFilmListener i = new IncassoPerFilmListener();
		perFilm.addActionListener(i);
	}
	
	private void createMenuItemLogOut()
	{
		logOut = new JMenuItem("Log Out");
		menuOperazione.add(logOut);
		
		class LogOutListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {	
				new FrameLogin().setVisible(true);
				dispose();
			}
		}
		
		LogOutListener l = new LogOutListener();
		logOut.addActionListener(l);
	}
	
	//DIVISORE
	private void createTextArea() {
		textMultisala = new JTextArea(5, 50);
		scroll = new JScrollPane(textMultisala);
		textMultisala.setEditable(false);
		panelCentro = new JPanel();
		panelCentro.add(scroll);
	}
	
	private void createCercaSpettacolo()
	{
		panelEst = new JPanel();
		panelEst.setLayout(new GridLayout(3, 1));
		panelEst.add(new JLabel("Inserisci id Spettacolo"));
		JTextField pannello = new JTextField();
		pannello.setSize(5, 1);
		panelEst.add(pannello);
		cercaId = new JButton("Cerca id");
		
		class cercaIdListener implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0) {
				
				textMultisala.setText("");
				for (Spettacolo s: modGes.listaFruibili())
				{
					if (Integer.parseInt(pannello.getText()) == s.getID())
							textMultisala.append(s.displayContent());
				}
				pannello.setText("");
			}
		}
		
		cercaIdListener c = new cercaIdListener();
		cercaId.addActionListener(c);
		panelEst.add(cercaId);
	}
	
	private void stampaIniziale()
	{
		textMultisala.setText("");
		for (Spettacolo s: modGes.listaFruibili())
		{
			textMultisala.append(s.displayContent() + "\n");
		}
	}
	
	
	private void createPanelResult() {
		panelResult = new JPanel();
		panelResult.setLayout(new BorderLayout());
		panelResult.add(panelCentro, BorderLayout.CENTER);
		panelResult.add(panelEst, BorderLayout.EAST);
		add(panelResult);
	}
	
	//DIVISORE
	
}
