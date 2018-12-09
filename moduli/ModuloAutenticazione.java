package moduli;

import java.util.ArrayList;
import classi.Utente;

/**
 * Questa classe viene utilizzata per controllare se un utente
 * si trova nell'arraylist degli utenti registrati
 */
public class ModuloAutenticazione {
	
	private ArrayList<Utente> utenti;
	
	/**
	 * Il costruttore instanzia un arraylist di clienti
	 */
	public ModuloAutenticazione()
	{
		utenti = new ArrayList<Utente>();
	}
	
	/**
	 * Questo metodo controlla se un utente è presente nella lista
	 * @param u Utente che deve effettuare il login
	 * @return true se presente nella lista, false altrimenti
	 */
	public Utente login(Utente u)
	{
		for (Utente user: utenti)
			if (user.equals(u))
				return user;
		
		return null;
	}
}
