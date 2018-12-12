package classi;

import java.util.ArrayList;

/**
 * Rappresenta il concetto di Cliente come specializzazione di Utente. Un Cliente ha tutti i dati di un Utente ma 
 * possiede, in pi�, una collezione di prenotazioni effettuate (Collezione di Biglietti con stato = "non acquistato").
 * Nella documentazione di questa classe si far� riferimento alla collezione di Biglietti come una collezione di Prenotazioni.
 *
 */
public class Cliente extends Utente{

	private static final long serialVersionUID = 1L;
	/**
	 * Collezione di Biglietti con stato = "non prenotato" indicanti le prenotazioni attive del Cliente.
	 */
	private ArrayList<Biglietto> prenotazioni;
	
	/**
	 * Et� del Cliente.
	 */
	private int eta;
	/**
	 * Metodo costruttore del Cliente che estende il costruttore dell'Utente aggiungendo ad esso la creazione
	 * di una collezione di Biglietti (prenotazioni).
	 * @param username Lo username del Cliente.
	 * @param password La password del Cliente.
	 * @param eta L'eta del Cliente.
	 */
	public Cliente(String username, String password,int eta)
	{
		super(username, password,false);
		prenotazioni = new ArrayList<Biglietto>();
		this.eta = eta;
	}
	
	/**
	 * Metodo modificatore del Cliente che elimina la prenotazioni di indice i.
	 * @param b Biglietto da rimuovere
	 */
	public boolean removePrenotazione(Biglietto b)
	{
		return prenotazioni.remove(b);
	}
	
	/**
	 * Metodo che ritorna la lista delle prenotazioni di un cliente
	 * @return ArrayList con le prenotazioni
	 */
	public ArrayList<Biglietto> getListaPrenotazioni()
	{
		return this.prenotazioni;
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
	 * Metodo di accesso all'et� del Cliente.
	 * @return Restituisce l'et� attuale del Cliente.
	 */
	public int getEta()
	{
		return eta;
	}
	/**
	 * Metodo modificatore dell'et� del Cliente.
	 * @param eta Restituisce l'et� del Cliente.
	 */
	public void setEta(int eta)
	{
		this.eta = eta;
	}
	/**
	 * Metodo di visualizzazione che amplia il metodo toString() di Utente aggiungendo la stampa delle prenotazioni.
	 * @return Restituisce una stringa contenente le informazioni principali del Cliente.
	 */
	public String toString()
	{
		return super.toString()+"[Et� = " + eta + ", Prenotazioni = "+prenotazioni+"]";
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
