package grafica;

import javax.swing.JFrame;

import classi.Utente;
import moduli.ModuloAutenticazione;

public class FrameRegistrazione extends JFrame{
	
	private ModuloAutenticazione autenticazione;
	
	public FrameRegistrazione(ModuloAutenticazione autenticazione)
	{
		this.autenticazione = autenticazione;
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
