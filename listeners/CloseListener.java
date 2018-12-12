package listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import classi.FlussoGenerico;
import classi.Multisala;

public class CloseListener implements WindowListener{
	private Multisala multisala;
	public CloseListener(Multisala multisala)
	{
		this.multisala = multisala;
	}
	
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		FlussoGenerico<Multisala> flusso = new FlussoGenerico<Multisala>("multisala.dat");
		try {
			flusso.save(multisala);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
