package grafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classi.Film;
import classi.Multisala;
import listeners.CloseListener;
import moduli.ModuloSpettacolo;

public class FrameInfo extends JFrame{

	private static final long serialVersionUID = 4061777330956141087L;
	private Multisala m;
	int idSpettacolo;
	
	public FrameInfo(Multisala m, int idSpettacolo)
	{
		this.m = m;
		this.idSpettacolo = idSpettacolo;
		setSize(300, 300);
		createFrameInfo();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new CloseListener(m));
	}
	
	private void createFrameInfo()
	{
		JPanel dati = new JPanel();
		dati.setLayout(new BorderLayout());
		JPanel info = new JPanel();
		
		
		ModuloSpettacolo modSpe = new ModuloSpettacolo(m);
		Film film = modSpe.getSpettacolo(idSpettacolo).getFilm();
		info.add(new JLabel("Titolo film: " + film.getTitolo()));
		info.add(new JLabel("Descrizione film: " + film.getDescrizione()));
		info.add(new JLabel("Durata film: " + film.getMinuti() + " minuti"));
		info.add(new JLabel("Prezzo: "));
		JTextField field = new JTextField(Double.toString(modSpe.getSpettacolo(idSpettacolo).getPrezzo()));
		info.add(field);
		
		JButton setPrezzo = new JButton("imposta prezzo");
		
		class setPrezzoListener implements ActionListener
		{

			public void actionPerformed(ActionEvent e) {
				
				modSpe.getSpettacolo(idSpettacolo).setPrezzo(Double.parseDouble(field.getText()));
				FrameGestore f = new FrameGestore(m);
				f.setVisible(true);
				dispose();
				
			}
			
		}
		
		setPrezzo.addActionListener(new setPrezzoListener());
		
		dati.add(info, BorderLayout.CENTER);
		dati.add(setPrezzo, BorderLayout.SOUTH);
		add(dati);
	}

}
