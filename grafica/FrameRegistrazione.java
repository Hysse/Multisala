package grafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import classi.Cliente;
import classi.FlussoGenerico;
import classi.Multisala;
import classi.Utente;
import eccezioni.SignUpException;
import listeners.CloseListener;
import listeners.InputTextListener;
import moduli.ModuloAutenticazione;

public class FrameRegistrazione extends JFrame {

	private ModuloAutenticazione autenticazione;

	public FrameRegistrazione(ModuloAutenticazione autenticazione) {
		this.autenticazione = autenticazione;
		setSize(670, 180);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new CloseListener(autenticazione.getMultisala()));
		add(createSignUpPanel());
	}

	public JPanel createSignUpPanel() {
		JPanel panel = new JPanel();
		JLabel usrlab = new JLabel("Username");
		JTextField usrtxt = new JTextField("");
		JLabel pwdlab = new JLabel("Password");
		JTextField pwdtxt = new JTextField("");
		JLabel etalab = new JLabel("Eta");
		JTextField etatxt = new JTextField("");
		JButton inviabtn = new JButton("Invia");
		inviabtn.setEnabled(false);
		JLabel errlab = new JLabel("");

		class InviaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try
				{
						autenticazione.signUpUtente(usrtxt.getText(), pwdtxt.getText(), Integer.parseInt(etatxt.getText()));
						FlussoGenerico<Multisala> flusso = new FlussoGenerico<Multisala>("multisala.dat");
						flusso.save(autenticazione.getMultisala());
						new FrameLogin().setVisible(true);
						dispose();
				}
				catch(SignUpException err)
				{
					errlab.setText(err.getMessage());
				}
				catch(IOException error)
				{
					errlab.setText(error.getMessage());
				}
			}
		}
		
		ActionListener invialistener = new InviaListener();
		ArrayList<Document> documenti = new ArrayList<Document>();
		documenti.add(usrtxt.getDocument());
		documenti.add(pwdtxt.getDocument());
		documenti.add(etatxt.getDocument());
		DocumentListener inputlistener = new InputTextListener(documenti,inviabtn);
		usrtxt.getDocument().addDocumentListener(inputlistener);
		pwdtxt.getDocument().addDocumentListener(inputlistener);
		etatxt.getDocument().addDocumentListener(inputlistener);
		inviabtn.addActionListener(invialistener);
		panel.setLayout(new GridLayout(4,2));
		panel.add(usrlab);
		panel.add(usrtxt);
		panel.add(pwdlab);
		panel.add(pwdtxt);
		panel.add(etalab);
		panel.add(etatxt);
		panel.add(errlab);
		panel.add(inviabtn);
		return panel;
	}
}
