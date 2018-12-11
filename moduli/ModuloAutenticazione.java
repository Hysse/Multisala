package moduli;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import classi.Cliente;
import classi.FlussoGenerico;
import classi.Multisala;
import classi.Utente;
import eccezioni.SignUpException;

/**
 * Questa classe viene utilizzata per controllare se un utente
 * si trova nell'arraylist degli utenti registrati
 */
public class ModuloAutenticazione {
	
	private Multisala multisala;
	

	/**
	 * Costruttore che associa a un moduloAutenticazione un Multisala preso dal file multisala.dat. Se
	 * il file non esiste crea un nuovo file chiamato multisala.dat. Dal multisala in seguito il modulo accede
	 * alla collezione di Utenti per verificare l'accesso.
	 */
	public ModuloAutenticazione() throws ClassNotFoundException,IOException
	{
		File file = new File("multisala.dat");
		if(!file.exists())
		{
			file.createNewFile();
			FlussoGenerico<Multisala> flusso = new FlussoGenerico<Multisala>("multisala.dat");
			flusso.save(new Multisala());
		}
		FlussoGenerico<Multisala> flusso = new FlussoGenerico<Multisala>("multisala.dat");
		multisala = flusso.load();
	}
	
	/**
	 * Metodo di accesso al multisala del modulo.
	 * @return Restituisce un puntatore all'oggetto Multisala del modulo.
	 */
	public Multisala getMultisala()
	{
		return multisala;
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
	private boolean alreadyExists(String username)
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
	 * degli utenti registrati
	 */
	public Utente loginUtente(String username, String password) 
	{
		for (Utente user: multisala.getListaUtenti())
		{
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
				return user;	
		}
		return null;
	}
	
	/**
	 * Metodo per la registrazione di un Cliente (se non vi sono già utenti con lo stesso username).
	 * @param username Username del Cliente da creare.
	 * @param password Password del Cliente da creare.
	 * @param eta L'età del Cliente da creare.
	 * @throws SignUpException Eccezione lanciata se esiste già un Cliente nella collezione con lo stesso username.
	 */
	public void signUpUtente(String username, String password,int eta) throws SignUpException
	{
		if (alreadyExists(username))
			throw new SignUpException();
		else
			multisala.addUtente(new Cliente(username, password,eta));		
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
