package grafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import classi.Cliente;
import classi.FlussoGenerico;
import classi.Multisala;
import classi.Spettacolo;
import listeners.CloseListener;
import listeners.InputTextListener;
import moduli.ModuloCliente;
import moduli.ModuloSala;
import moduli.ModuloSpettacolo;

public class FrameCliente extends JFrame{
	
	private Cliente cliente;
	private ModuloCliente moduloCliente;
	
	public FrameCliente(Cliente cliente,Multisala multisala)
	{
		moduloCliente = new ModuloCliente(multisala,cliente);
		setSize(1000,500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(createPanelCliente(multisala));
		addWindowListener(new CloseListener(multisala));
	}

	private JPanel createPanelCliente(Multisala multisala) {
		JPanel panelcliente = new JPanel();
		panelcliente.setLayout(new BorderLayout());
		JTextArea display = createTextAreaCliente();
		panelcliente.add(createMenuBarCliente(display),BorderLayout.NORTH);
		panelcliente.add(paneldiDestraCliente(display,multisala),BorderLayout.EAST);
		panelcliente.add(display,BorderLayout.CENTER);
		return panelcliente;
	}
	
	private JPanel createRadioPanelCliente(JTextArea display)
	{
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Selezione"));
		JRadioButton complessivo = new JRadioButton("Settimana");
		JRadioButton per_sala = new JRadioButton("Per Sala");
		ButtonGroup gruppo = new ButtonGroup();
		gruppo.add(complessivo);
		gruppo.add(per_sala);
		complessivo.setSelected(true);
		JButton visualizza = new JButton("Visualizza");
		class VisualizzaListener implements ActionListener{
			public void actionPerformed(ActionEvent e)
			{
				display.setText("");
				if(complessivo.isSelected())
				{
					for(Spettacolo s : moduloCliente.getSpettacoliSettimana())
					{
						display.setText(display.getText()+s.displayContent()+"\n");
					}
				}
				if(per_sala.isSelected())
				{
					for(Spettacolo s : moduloCliente.getSpettacoliSala())
					{
						display.setText(display.getText()+s.displayContent()+"\n");
					}
				}
			}
		}
		ActionListener visualizzalistener = new VisualizzaListener();
		visualizza.addActionListener(visualizzalistener);
		panel.add(complessivo);
		panel.add(per_sala);
		panel.add(visualizza);
		return panel;
	}
	
	private JPanel selezioneSpettacoloPanel(Multisala multisala,JTextArea display)
	{
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Spettacolo"));
		JLabel seleziona = new JLabel("Seleziona Spettacolo (id)");
		JTextField idtxt = new JTextField(10);
		JButton ok = new JButton("OK");
		ok.setEnabled(false);
		class SpettacoloListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				Spettacolo s = moduloCliente.getInfoSpettacolo(Integer.parseInt(idtxt.getText()));
				if(s == null)
					display.setText("Spettacolo non esistente!!!");
				else
				{
					JFrame framespettacolo = new FrameInfoSpettacolo(multisala, moduloCliente.getCliente(), s);
					framespettacolo.setVisible(true);
					framespettacolo.addWindowListener(new CloseListener(multisala));
					setVisible(false);
				}
			}
		}
		ArrayList<Document> documenti = new ArrayList<Document>();
		documenti.add(idtxt.getDocument());
		DocumentListener inputListener = new InputTextListener(documenti,ok);
		idtxt.getDocument().addDocumentListener(inputListener);
		ActionListener listenerok = new SpettacoloListener();
		ok.addActionListener(listenerok);
		panel.add(seleziona);
		panel.add(idtxt);
		panel.add(ok);
		return panel;
	}
	
	private JPanel paneldiDestraCliente(JTextArea display,Multisala multisala)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(createRadioPanelCliente(display),BorderLayout.NORTH);
		panel.add(selezioneSpettacoloPanel(multisala,display),BorderLayout.CENTER);
		return panel;
	}
	
	private JTextArea createTextAreaCliente()
	{
		JTextArea display = new JTextArea(7,20);
		return display;
	}
	
	private JMenuBar createMenuBarCliente(JTextArea display)
	{
		JMenuBar menubar = new JMenuBar();
		JMenu operazioni = new JMenu("Operazioni");
		JMenu ordinaPer = new JMenu("Fruibili...");
		JMenuItem prenotazioni = new JMenuItem("Prenotazioni");
		JMenuItem logout = new JMenuItem("Logout");
		JMenuItem crolonologico = new JMenuItem("Cronologico");
		JMenuItem per_sala = new JMenuItem("Sala crescente");
		JMenuItem per_titolo = new JMenuItem("Titolo");
		ordinaPer.add(crolonologico);
		ordinaPer.add(per_sala);
		ordinaPer.add(per_titolo);
		operazioni.add(ordinaPer);
		operazioni.add(prenotazioni);
		operazioni.add(logout);
		class LogoutListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				new FrameLogin().setVisible(true);
				dispose();
			}
		}
		class PrenotazioniListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		}
		class CronologicoListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				display.setText("");
				for(Spettacolo s : moduloCliente.getFruibiliCronologico())
				{
					display.setText(display.getText()+s.displayContent()+"\n");
				}
			}
		}
		class SalaCrescListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				display.setText("");
				for(Spettacolo s : moduloCliente.getFruibiliSalaCresc())
				{
					display.setText(display.getText()+s.displayContent()+"\n");
				}
			}
		}
		class TitoloListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				display.setText("");
				for(Spettacolo s : moduloCliente.getFruibiliTitolo())
				{
					display.setText(display.getText()+s.displayContent()+"\n");
				}
			}
		}
		ActionListener listenercrono = new CronologicoListener();
		ActionListener listenersala = new SalaCrescListener();
		ActionListener listenertitolo = new TitoloListener();
		ActionListener listenerlogout = new LogoutListener();
		logout.addActionListener(listenerlogout);
		crolonologico.addActionListener(listenercrono);
		per_sala.addActionListener(listenersala);
		per_titolo.addActionListener(listenertitolo);
		menubar.add(operazioni);
		return menubar;
	}
}
