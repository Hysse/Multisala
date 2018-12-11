package grafica;

import javax.swing.JFrame;

import classi.Cliente;

public class FrameCliente extends JFrame{
	
	private Cliente cliente;
	
	public FrameCliente(Cliente cliente)
	{
		this.cliente = cliente;
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
