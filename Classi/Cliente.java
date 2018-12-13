package classi;

import java.util.ArrayList;

/**
 * Rappresenta il concetto di Cliente come specializzazione di Utente. Un
 * Cliente ha tutti i dati di un Utente ma possiede, in più, una collezione di
 * prenotazioni effettuate (Collezione di Biglietti con stato = "non
 * acquistato"). Nella documentazione di questa classe si farà riferimento alla
 * collezione di Biglietti come una collezione di Prenotazioni.
 *
 */
public class Cliente extends Utente {

	private static final long serialVersionUID = 1L;
	private ArrayList<Biglietto> biglietti;
	private int eta;

	/**
	 * Metodo costruttore del Cliente che estende il costruttore dell'Utente
	 * aggiungendo ad esso la creazione di una collezione di Biglietti
	 * (prenotazioni).
	 * 
	 * @param username Lo username del Cliente.
	 * @param password La password del Cliente.
	 * @param eta      L'eta del Cliente.
	 */
	public Cliente(String username, String password, int eta) {
		super(username, password, false);
		biglietti = new ArrayList<Biglietto>();
		this.eta = eta;
	}

	/**
	 * Metodo modificatore del Cliente che elimina il biglietto relativo alla
	 * prenotazione.
	 * 
	 * @param b Biglietto da rimuovere
	 */
	public boolean removePrenotazione(Biglietto b) {
		return biglietti.remove(b);
	}

	/**
	 * Metodo che ritorna la lista delle prenotazioni di un cliente
	 * 
	 * @return ArrayList con le prenotazioni
	 */
	public ArrayList<Biglietto> getListaBiglietti() {
		return this.biglietti;
	}

	/**
	 * Metodo modificatore di aggiunta di una Prenotazione.
	 * 
	 * @param biglietto La prenotazione che si vuole aggiungere.
	 */
	public void addPrenotazione(Biglietto biglietto) {
		biglietti.add(biglietto);
	}

	/**
	 * Metodo di accesso all'età del Cliente.
	 * 
	 * @return Restituisce l'età attuale del Cliente.
	 */
	public int getEta() {
		return eta;
	}

	/**
	 * Metodo modificatore dell'età del Cliente.
	 * 
	 * @param eta Restituisce l'età del Cliente.
	 */
	public void setEta(int eta) {
		this.eta = eta;
	}

	/**
	 * Metodo di visualizzazione che amplia il metodo toString() di Utente
	 * aggiungendo la stampa delle prenotazioni.
	 * 
	 * @return Restituisce una stringa contenente le informazioni principali del
	 *         Cliente.
	 */
	public String toString() {
		return super.toString() + "[Età = " + eta + ", Prenotazioni = " + biglietti + "]";
	}

	/**
	 * Metodo di clonazione per un Cliente.
	 * 
	 * @return Restituisce un puntatore ad una copia del Cliente chiamante.
	 */
	public Cliente clone() {
		Cliente clone = (Cliente) super.clone();
		ArrayList<Biglietto> biglietticlone = new ArrayList<Biglietto>();
		for (Biglietto p : biglietti)
			biglietticlone.add(p.clone());
		clone.biglietti = biglietticlone;
		return clone;
	}

	/**
	 * Metodo di controllo dell'uguaglianza tra un Cliente chiamante ed un Oggetto.
	 * 
	 * @param obj L'Oggetto con cui si vuole confrontare il Cliente chiamante.
	 * @return Restituisce true se lo stato del Cliente chiamante e quello
	 *         dell'Oggetto obj sono gli stessi, false altrimenti.
	 */
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		Cliente other = (Cliente) obj;
		return biglietti.equals(other.biglietti);
	}
}
