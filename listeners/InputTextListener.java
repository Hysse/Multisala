package listeners;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 * Listener per la disattivazione di JButtons nel caso in cui una determinata JTextField diventi vuota.
 * 
 */
public class InputTextListener implements DocumentListener{
	private JButton bottone;
	private ArrayList<Document> documenti;
	/**
	 * Il costruttore ha come parametro esplicito un JButton e una collezione di Documenti. Quando il Documento a cui viene associato il listener
	 * camba stato vengono controllati tutti i Documenti della collezione e se anche uno solo è vuoto viene disabilitato il bottone. Sa tutti i Documenti
	 * della collezione hanno del contenuto allora il bottoneviene attivato.
	 * @param array L'ArrayList di Documenti di cui si vuole controllare lo stato.
	 * @param b Il JButton che si vuole disattivare/attivare.
	 */
	public InputTextListener(ArrayList<Document> array,JButton b)
	{
		bottone = b;
		documenti = array;
	}
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		method();
		
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		method();
		
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		method();
		
	}
	/**
	 * Metodo che controlla se anche uno solo dei Documenti della collezione è vuoto allora imposta il JButton bottone come "disabled" altrimenti
	 * viene impostato come "eneabled".
	 */
	public void method()
	{
		boolean qualcunaVuota = false;
		for(Document d : documenti)
		{
			if(d.getLength() == 0)
				qualcunaVuota = true;
		}
		if(qualcunaVuota)
			bottone.setEnabled(false);
		else
			bottone.setEnabled(true);
	}

}
