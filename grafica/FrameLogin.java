package grafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classi.CloseListener;
import classi.FlussoGenerico;
import classi.Multisala;
import classi.Utente;
import classi.Cliente;
import moduli.ModuloAutenticazione;
/**
 * Classe che estende JFrame che rappresenta il frame di login del sistema multisala. FrameLogin si serve di un ModuloAutenticazione
 * per controllare la presenza delle credenziali di accesso dell'Utente nella collezione di Utenti del multisala.
 * 
 *
 */
public class FrameLogin extends JFrame{

	private static final long serialVersionUID = 1L;
	private ModuloAutenticazione autenticazione;
	
	public FrameLogin()
	{
		setSize(525,150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			autenticazione = new ModuloAutenticazione();
		} catch (ClassNotFoundException | IOException e) {
			new FrameErrore(e).setVisible(true);
		}
		addWindowListener(new CloseListener(autenticazione.getMultisala()));
		add(createLoginPanel());
	}
	
	private JPanel createLoginPanel()
	{
		JPanel panel = new JPanel();
		JLabel usrlab = new JLabel("Username : ");
		JTextField usrtxt = new JTextField(10);
		JLabel pwdlab = new JLabel("Password : ");
		JTextField pwdtxt = new JTextField(10);
		JLabel err = new JLabel("");
		JButton login = new JButton("Login");
		JButton registrati = new JButton("Registrati");
		
		class LoginListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				Utente utente;
				if((utente =  autenticazione.loginUtente(usrtxt.getText(), pwdtxt.getText())) == null)
				{
					err.setText("Username o password errati!");
				}
				else
				{
					if(utente.isGestore())
						new FrameGestore(utente).setVisible(true);
					else
						new FrameCliente((Cliente)utente).setVisible(true);
					setVisible(false);
				}
			}
		}
		
		class RegistratiListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				new FrameRegistrazione(autenticazione).setVisible(true);
				setVisible(false);
			}
		}
		
		ActionListener listenerLogin = new LoginListener();
		ActionListener listenerRegistrati = new RegistratiListener();
		login.addActionListener(listenerLogin);
		registrati.addActionListener(listenerRegistrati);
		panel.setLayout(new GridLayout(4,3));
		panel.add(usrlab);
		panel.add(usrtxt);
		panel.add(new JLabel(""));
		panel.add(pwdlab);
		panel.add(pwdtxt);
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(err);
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(login);
		panel.add(registrati);
		return panel;
	}
}
