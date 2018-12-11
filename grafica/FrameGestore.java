package grafica;

import javax.swing.JFrame;

import classi.Utente;

public class FrameGestore extends JFrame{
	
	private Utente utente;
	
	public FrameGestore(Utente utente)
	{
		this.utente = utente;
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
