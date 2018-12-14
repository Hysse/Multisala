package grafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classi.Multisala;
import eccezioni.FilmNonPresenteException;
import eccezioni.OraSpettacoloException;
import eccezioni.SpettacoloIDException;
import listeners.CloseListener;
import moduli.ModuloSpettacolo;

public class FrameAggiungiSpettacolo extends JFrame{

	private static final long serialVersionUID = 5551613620698079671L;
	public ModuloSpettacolo modSpe;
	Multisala multisala;
	
	public FrameAggiungiSpettacolo(Multisala m)
	{
		this.multisala = m;
		modSpe = new ModuloSpettacolo(m);
		setSize(300, 300);
		createPanelInformazioni();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new CloseListener(m));
	}
	
	private void createPanelInformazioni()
	{
		JPanel pannello = new JPanel(new BorderLayout());
		JPanel campi = new JPanel();
		
		campi.add(new JLabel("id Spettacolo: "));
		JTextField idSp = new JTextField(4);
		campi.add(idSp);
		campi.add(new JLabel("Id Film: "));
		JTextField idFilm = new JTextField(4);
		campi.add(idFilm);
		campi.add(new JLabel("Numero sala: "));
		JTextField numSala = new JTextField(4);
		campi.add(numSala);
		campi.add(new JLabel("Prezzo: "));
		JTextField prezzo = new JTextField(8);
		campi.add(prezzo);
		campi.add(new JLabel("Giorno(1-31): "));
		JTextField giorno = new JTextField(4);
		campi.add(giorno);
		campi.add(new JLabel("Mese(0-11): "));
		JTextField mese = new JTextField(4);
		campi.add(mese);
		campi.add(new JLabel("Anno: "));
		JTextField anno = new JTextField(6);
		campi.add(anno);
		campi.add(new JLabel("Ora: "));
		JTextField ora = new JTextField(4);
		campi.add(ora);
		campi.add(new JLabel("Minuti: "));
		JTextField minuti = new JTextField(4);
		campi.add(minuti);

		pannello.add(campi, BorderLayout.CENTER);
		
		JButton aggiungi = new JButton("Aggiungi Spettacolo");
		
		class aggiungiListener implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0) {
				
				GregorianCalendar data = new GregorianCalendar(Integer.parseInt(anno.getText()),
						Integer.parseInt(mese.getText()), Integer.parseInt(giorno.getText()),
						Integer.parseInt(ora.getText()), Integer.parseInt(minuti.getText()));
				try {
					modSpe.addSpettacolo(Integer.parseInt(idSp.getText()), Integer.parseInt(idFilm.getText()),
					Integer.parseInt(numSala.getText()), Double.parseDouble(prezzo.getText()), data);
					FrameGestore f = new FrameGestore(multisala);
					f.setVisible(true);
					dispose();
					
				} catch (NumberFormatException e) {
					JFrame framerr = new JFrame();
					framerr.setSize(500, 100);
					framerr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JLabel errore = new JLabel(e.getMessage());
					framerr.add(errore);
					framerr.setVisible(true);
				} catch (FilmNonPresenteException e) {
					JFrame framerr = new JFrame();
					framerr.setSize(500, 100);
					framerr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JLabel errore = new JLabel(e.getMessage());
					framerr.add(errore);
					framerr.setVisible(true);
				} catch (OraSpettacoloException e) {
					JFrame framerr = new JFrame();
					framerr.setSize(500, 100);
					framerr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JLabel errore = new JLabel(e.getMessage());
					framerr.add(errore);
					framerr.setVisible(true);
				} catch (SpettacoloIDException e) {
					JFrame framerr = new JFrame();
					framerr.setSize(500, 100);
					framerr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JLabel errore = new JLabel(e.getMessage());
					framerr.add(errore);
					framerr.setVisible(true);
				}
					
				}

			}
		
		aggiungi.addActionListener(new aggiungiListener());
		pannello.add(aggiungi, BorderLayout.SOUTH);
		add(pannello);
			
	}

		
}


