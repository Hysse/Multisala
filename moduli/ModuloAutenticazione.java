package moduli;

import classi.Cliente;
import classi.Multisala;
import classi.Utente;
import eccezioni.LoginException;
import eccezioni.SignUpException;

/**
 * Questa classe viene utilizzata per controllare se un utente
 * si trova nell'arraylist degli utenti registrati
 */
public class ModuloAutenticazione {
	
	private Multisala multisala;
	

	/**
	 * Costruttore che associa a un moduloAutenticazione un Multisala dove si trova i clienti
	 * @param multisala Multisala dove prendere la lista di clienti
	 */
	public ModuloAutenticazione(Multisala multisala)
	{
		this.multisala = multisala;
	}
	
	/**
	 * Questo metodo aggiunge un utente nella lista degli utenti registrati
	 * @param u Utente da aggiungere
	 */
	public void aggiungiUtente(Utente u)
	{
		multisala.addUtente(u);
	}
	
	/**
	 * Questo metodo rimuove un utente nella lista degli utenti registrati
	 * @param u Utente da rimuovere
	 * @return true se è stato rimosso e quindi presente nella lista, false altrimenti
	 */
	public boolean removeUtente(Utente u)
	{
		return multisala.removeUtente(u);
	}
	/**
	 * Metodo che controlla se esiste già un Utente in collezione con lo username uguale a quello passato come parametro.
	 * @param username Username che si vuole controllare nella collezione.
	 * @return Restituisce true se esiste un Cliente con quell'username nella collezione, false altrimenti.
	 */
	public boolean alreadyExists(String username)
	{
		for(Utente user : multisala.getListaUtenti())
		{
			if(user.getUsername().equals(username))
				return true;
		}
		return false;
	}
	/**
	 * Questo metodo controlla se un utente è presente nella lista e fa effettuare il login
	 * @param username Stringa con username dell'utente
	 * @param password Stringa con password dell'utente
	 * @return L'utente che deve essere loggato se presente, altrimenti lancia l'eccezione
	 * @throws LoginException eccezione che viene lanciata se l'utente non è presente nella lista
	 * degli utenti registrati
	 */
	public Utente loginUtente(String username, String password) throws LoginException
	{
		for (Utente user: multisala.getListaUtenti())
		{
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
				return user;	
		}
		
		throw new LoginException();
	}
	
	/**
	 * Metodo per la registrazione di un Cliente (se non vi sono già utenti con lo stesso username).
	 * @param username Username del Cliente da creare.
	 * @param password Password del Cliente da creare.
	 * @param eta L'età del Cliente da creare.
	 * @return Restituisce true se la creazione è andata a buon fine, false altrimenti;
	 * @throws SignUpException Eccezione lanciata se esiste già un Cliente nella collezione con lo stesso username.
	 */
	public boolean signUpUtente(String username, String password,int eta) throws SignUpException
	{
		if (alreadyExists(username))
			throw new SignUpException();
		else
		{
			multisala.addUtente(new Cliente(username, password,eta));
			return true;
		}
			
	}
	
	/**
	 * Metodo che può essere usato solo se un utente ha fatto il login
	 * @param utente
	 * @param password
	 */
	public void changePassword(Utente utente, String password)
	{
		for (Utente user: multisala.getListaUtenti())
		{
			if (user.getUsername().equals(utente.getUsername()) &&
					user.getPassword().equals(utente.getPassword()))
				user.setPassword(password);	
		}
	}
}
