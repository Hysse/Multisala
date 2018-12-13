package grafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import classi.Biglietto;
import classi.Cliente;
import classi.Multisala;
import classi.Spettacolo;
import listeners.CloseListener;
import listeners.InputTextListener;
import moduli.ModuloBiglietto;
import moduli.ModuloCliente;

public class FrameCancellaPrenotazione extends JFrame{

	private Cliente cliente;
	private Multisala multisala;
	private ModuloCliente modCli;
	
	public FrameCancellaPrenotazione(Multisala multisala,Cliente cliente)
	{
		this.cliente = cliente;
		this.multisala = multisala;
		modCli = new ModuloCliente(multisala,cliente);
		setSize(1000,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new CloseListener(multisala));
		add(createPanelCancellaPre());
	}
	
	public JPanel createPanelCancellaPre()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JTextArea display = createTextAreaPrenotazioni();
		JScrollPane scroll = new JScrollPane(display);
		panel.add(paneldiDestraPrenotazioni(display),BorderLayout.EAST);
		panel.add(scroll,BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel paneldiDestraPrenotazioni(JTextArea display)
	{
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Cancellazione"));
		JLabel seleziona = new JLabel("Seleziona Biglietto (id_Spettacolo)");
		JTextField idtxt = new JTextField(10);
		JButton cancella = new JButton("Cancella");
		cancella.setEnabled(false);
		class CancellaListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				if(modCli.cancellaPrenotazione(Integer.parseInt(idtxt.getText())))
				{
					new FrameCliente(cliente,multisala).setVisible(true);
					dispose();
				}
				else
				{
					JFrame framerr = new JFrame();
					framerr.setSize(500, 100);
					framerr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JLabel errore = new JLabel("Nessuna prenotazione di spettacoli con quell'ID!!!");
					framerr.add(errore);
					framerr.setVisible(true);
				}
			}
		}
		ArrayList<Document> documenti = new ArrayList<Document>();
		documenti.add(idtxt.getDocument());
		DocumentListener inputListener = new InputTextListener(documenti,cancella);
		idtxt.getDocument().addDocumentListener(inputListener);
		ActionListener listenercancella = new CancellaListener();
		cancella.addActionListener(listenercancella);
		panel.add(seleziona);
		panel.add(idtxt);
		panel.add(cancella);
		return panel;
		
	}
	
	private JTextArea createTextAreaPrenotazioni()
	{
		JTextArea display = new JTextArea(7,20);
		display.setEditable(false);
		ModuloBiglietto modBig = new ModuloBiglietto(multisala, cliente);
		display.setText("");
		for(Biglietto b : cliente.getListaBiglietti())
		{
			if(b.isPrenotazione())
				display.setText(""+display.getText()+b.displayBiglietto());
		}
		return display;
	}
}
