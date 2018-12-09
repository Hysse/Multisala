package classi;

import java.util.ArrayList;

/**
 * Rappresenta il concetto di Cliente come specializzazione di Utente. Un Cliente ha tutti i dati di un Utente ma 
 * possiede, in più, una collezione di prenotazioni effettuate (Collezione di Biglietti con stato = "non acquistato").
 * Nella documentazione di questa classe si farà riferimento alla collezione di Biglietti come una collezione di Prenotazioni.
 *
 */
public class Cliente extends Utente{

	private static final long serialVersionUID = 1L;
	/**
	 * Collezione di Biglietti con stato = "non prenotato" indicanti le prenotazioni attive del Cliente.
	 */
	private ArrayList<Biglietto> prenotazioni;
	
	/**
	 * Metodo costruttore del Cliente che estende il costruttore dell'Utente aggiungendo ad esso la creazione
	 * di una collezione di Biglietti (prenotazioni).
	 * @param username Lo username del Cliente.
	 * @param password La password del Cliente.
	 */
	public Cliente(String username, String password)
	{
		super(username, password);
		prenotazioni = new ArrayList<Biglietto>();
	}
	
	/**
	 * Metodo modificatore del Cliente che elimina la prenotazioni di indice i.
	 * @param i L'indice della prenotazione da eliminare all'interno della collezione.
	 */
	public void removePrenotazione(int i)
	{
		prenotazioni.remove(i);
	}
	
	/**
	 * Metodo di accesso di Cliente che permette l'accesso ad una prenotazione di indice i.
	 * @param i L'indice della prenotazione da prelevare nella collezione.
	 * @return Restituisce la Prenotazione di indice i nella collezione.
	 */
	public Biglietto getPrenotazione(int i)
	{
		return prenotazioni.get(i);
	}
	
	/**
	 * Metodo modificatore di aggiunta di una Prenotazione.
	 * @param biglietto La prenotazione che si vuole aggiungere.
	 */
	public void addPrenotazione(Biglietto biglietto)
	{
		prenotazioni.add(biglietto);
	}
	
	/**
	 * Metodo di controllo che indica se una Prenotazione è contenuta o meno nella collezione.
	 * @param biglietto La Prenotazione da cercare nella collezione.
	 * @return Restituisce l'indice della Prenotazione cercata all'interno della collezione se presente, -1 altrimenti.
	 */
	public int haPrenotazione(Biglietto biglietto)
	{
		return prenotazioni.indexOf(biglietto);
	}
	
	/**
	 * Metodo di visualizzazione che amplia il metodo toString() di Utente aggiungendo la stampa delle prenotazioni.
	 * @return Restituisce una stringa contenente le informazioni principali del Cliente.
	 */
	public String toString()
	{
		return super.toString()+"[Prenotazioni = "+prenotazioni+"]";
	}
	
	/**
	 * Metodo di clonazione per un Cliente.
	 * @return Restituisce un puntatore ad una copia del Cliente chiamante.
	 */
	public Cliente clone()
	{
		Cliente clone = (Cliente)super.clone();
		ArrayList<Biglietto> biglietticlone = new ArrayList<Biglietto>();
		for(Biglietto p : prenotazioni)
			biglietticlone.add(p.clone());
		clone.prenotazioni = biglietticlone;
		return clone;
	}
	
	/**
	 * Metodo di controllo dell'uguaglianza tra un Cliente chiamante ed un Oggetto.
	 * @param obj L'Oggetto con cui si vuole confrontare il Cliente chiamante.
	 * @return Restituisce true se lo stato del Cliente chiamante e quello dell'Oggetto obj sono gli stessi, false altrimenti. 
	 */
	public boolean equals(Object obj)
	{
		if(!super.equals(obj))
			return false;
		Cliente other = (Cliente) obj;
		return prenotazioni.equals(other.prenotazioni);
	}
}
