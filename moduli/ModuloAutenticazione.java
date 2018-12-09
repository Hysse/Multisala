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
	 * Questo costruttore instanzia un arraylist di clienti
	 */
	public ModuloAutenticazione()
	{
		utenti = new ArrayList<Utente>();
	}
	
	/**
	 * Questo costruttore salva un arraylist di utentu
	 * @param utenti arraylist di utenti da salvare
	 */
	public ModuloAutenticazione(ArrayList<Utente> utenti)
	{
		this.utenti = utenti;
	}
	
	/**
	 * Questo metodo aggiunge un utente nella lista degli utenti registrati
	 * @param u Utente da aggiungere
	 */
	public void aggiungiUtente(Utente u)
	{
		utenti.add(u);
	}
	
	/**
	 * Questo metodo rimuove un utente nella lista degli utenti registrati
	 * @param u Utente da rimuovere
	 * @return true se è stato rimosso e quindi presente nella lista, false altrimenti
	 */
	public boolean removeUtente(Utente u)
	{
		return utenti.remove(u);
	}
	
	/**
	 * Questo metodo controlla se un utente è presente nella lista
	 * @param u Utente che deve effettuare il login
	 * @return L'utente che deve essere loggato se presente, null altrimenti
	 */
	public Utente cercaUtente(Utente u)
	{
		for (Utente user: utenti)
			if (user.equals(u))
				return user;
		
		return null;
	}
}
