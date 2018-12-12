package classi;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe che possiede tutte le informazioni di un multisala e un incasso
 * settimanale
 */
public class Multisala implements Serializable, Cloneable {

	private static final long serialVersionUID = -8943016259035415610L;
	private ArrayList<Sala> listaSale;
	private ArrayList<Spettacolo> listaSpettacoli;
	private ArrayList<Film> listaFilm;
	private ArrayList<Utente> listaUtenti;
	private ArrayList<PoliticaSconto> listaPoliticheSconto;
	private double incassoSettimanale;

	/**
	 * Costruttore di un multisala
	 * 
	 * @param listaSale            ArrayList delle sale
	 * @param listaSpettacoli      ArrayList degli spettacoli
	 * @param listaFilm            ArrayList dei film
	 * @param listaUtenti          ArrayList degli utenti
	 * @param listaPoliticheSconto ArrayList delle politiche di sconto
	 */
	public Multisala(ArrayList<Sala> listaSale, ArrayList<Spettacolo> listaSpettacoli, ArrayList<Film> listaFilm,
			ArrayList<Utente> listaUtenti, ArrayList<PoliticaSconto> listaPoliticheSconto) {
		this.listaSale = listaSale;
		this.listaSpettacoli = listaSpettacoli;
		this.listaFilm = listaFilm;
		this.listaUtenti = listaUtenti;
		this.listaPoliticheSconto = listaPoliticheSconto;
	}

	public Multisala() {
		listaSale = new ArrayList<Sala>();
		listaFilm = new ArrayList<Film>();
		listaSpettacoli = new ArrayList<Spettacolo>();
		listaUtenti = new ArrayList<Utente>();
		listaPoliticheSconto = new ArrayList<PoliticaSconto>();
		this.incassoSettimanale = 0.0;
	}

	/**
	 * Metodo per aggiungere fondi all'incasso settimanale
	 * 
	 * @param incasso Double con il valore da aggiungere
	 */
	public void addAmount(Double incasso) {
		this.incassoSettimanale += incasso;
	}

	/**
	 * Metodo che ritorna l'incasso settimanale del multisala
	 * 
	 * @return Double con l'incasso settimanale
	 */
	public double getIncassoSettimanale() {
		return this.incassoSettimanale;
	}

	/**
	 * Meotodo per resettare l'incasso settimanale quando si arriva al lunedì della
	 * settimana successiva
	 */
	public void resetAmount() {
		this.incassoSettimanale = 0.0;
	}

	/**
	 * Meotodo che aggiunge un utente alla lista degli utenti
	 * 
	 * @param u Utente da aggiungere
	 */
	public void addUtente(Utente u) {
		listaUtenti.add(u);
	}

	/**
	 * Metodo che ritorna un Utente cercato con l'username nella lista degli utenti
	 * del multisala
	 * 
	 * @param username Stringa con l'username dell'utente
	 * @return Utente cercato con l'username se presente, altrimenti null se non è
	 *         presente
	 */
	public Utente getUtente(String username) {
		for (Utente u : listaUtenti) {
			if (u.getUsername().equals(username))
				return u;
		}

		return null;
	}

	/**
	 * Metodo che cerca un utente nella lista degli utenti e se presente lo rimuove
	 * 
	 * @param u Utente da rimuovere
	 * @return true se l'utente è stato rimosso, false altrimenti
	 */
	public boolean removeUtente(Utente u) {
		if (getUtente(u.getUsername()) != null)
			return listaUtenti.remove(u);

		return false;
	}

	/**
	 * Metodo che ritorna la lista degli utenti di un multisala
	 * 
	 * @return ArrayList degli utenti del multisala
	 */
	public ArrayList<Utente> getListaUtenti() {
		return this.listaUtenti;
	}

	/**
	 * Metodo che ritorna la lista delle sale di un Multisala
	 * 
	 * @return ArrayList con le sale del Multisala
	 */
	public ArrayList<Sala> getListaSale() {
		return listaSale;
	}

	/**
	 * Metodo che ritorna la lista dei film del Multisala
	 * 
	 * @return ArrayList delle politiche di sconto del multisala
	 */
	public ArrayList<Film> getListaFilm() {
		return this.listaFilm;
	}

	/**
	 * Metodo che ritorna la lista delle politiche di sconto di un multisala
	 * 
	 * @return ArrayList delle politiche di sconto
	 */
	public ArrayList<PoliticaSconto> getListaPoliticheSconto() {
		return this.listaPoliticheSconto;
	}

	/**
	 * Metodo che ritorna la lista degli spettacoli del multisala
	 * 
	 * @return ArrayList con gli spettacoli del multisala
	 */
	public ArrayList<Spettacolo> getListaSpettacoli() {
		return listaSpettacoli;
	}

	/**
	 * Meotodo che clona un Multisala
	 */
	public Multisala clone() {
		try {
			Multisala clone = (Multisala) super.clone();
			ArrayList<Sala> a1 = new ArrayList<Sala>();
			ArrayList<Spettacolo> a2 = new ArrayList<Spettacolo>();
			ArrayList<Film> a3 = new ArrayList<Film>();
			ArrayList<Utente> a4 = new ArrayList<Utente>();
			ArrayList<PoliticaSconto> a5 = new ArrayList<PoliticaSconto>();

			for (Sala s : listaSale) {
				a1.add(s.clone());
			}

			for (Spettacolo s : listaSpettacoli) {
				a2.add(s.clone());
			}

			for (Film f : listaFilm) {
				a3.add(f.clone());
			}

			for (Utente u : listaUtenti) {
				a4.add(u.clone());
			}

			for (PoliticaSconto p : listaPoliticheSconto) {
				a5.add(p.clone());
			}

			clone.listaSale = a1;
			clone.listaSpettacoli = a2;
			clone.listaFilm = a3;
			clone.listaUtenti = a4;
			clone.listaPoliticheSconto = a5;

			return clone;

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Metodo che verifica se die multisala sono uguali
	 */
	public boolean equals(Object o) {
		if (o == null || o.getClass() != getClass())
			return false;

		Multisala m = (Multisala) o;

		return listaFilm.equals(m.listaFilm) && listaSale.equals(m.listaSale) && listaUtenti.equals(m.listaUtenti)
				&& listaSpettacoli.equals(m.listaSpettacoli) && listaPoliticheSconto.equals(m.listaPoliticheSconto);
	}

	/**
	 * Metodo che ritorna una stringa con la formattazione testuale di un multisala
	 * 
	 * @return Stringa con le informazioni
	 */
	public String toString() {
		return getClass().getSimpleName() + "[Sale:\n" + listaSale + ", Spettacoli:\n" +
				listaSpettacoli + ", Film:\n" + listaPoliticheSconto + "Utenti:\n" +
				listaUtenti + "]";
	}
}
