package grafica;

import javax.swing.JFrame;

import classi.Cliente;
import classi.Multisala;
import classi.Spettacolo;
import classi.Utente;
import listeners.CloseListener;
import moduli.ModuloCliente;

public class FrameInfoSpettacolo extends JFrame{

	private static final long serialVersionUID = 1L;
	private ModuloCliente modCli;
	private Spettacolo spettacolo;
	
	public FrameInfoSpettacolo(Multisala multisala, Cliente c,Spettacolo s)
	{
		this.modCli = new ModuloCliente(multisala, c);
		this.spettacolo = s;
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new CloseListener(multisala));
	}
}
