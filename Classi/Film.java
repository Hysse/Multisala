package classi;

import java.io.Serializable;

/**
 * Questa classe è immutabile(final) e viene utilizzata per salvare descizione,
 * titolo, durata(in minuti) di un film, identificativo e incasso.
 */
public final class Film implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private String descrizione;
	private String titolo;
	private int minuti;
	private int idFilm;
	private double incasso;

	/**
	 * Il costruttore prende una Stringa titolo, una String descizione e un in per i
	 * minuti
	 * 
	 * @param titolo      titolo del film
	 * @param descrizione descrizione del film con breve trama
	 * @param minuti      durata del film in minuti
	 * @param idFilm      int che identifica un film
	 */
	public Film(String titolo, String descrizione, int minuti, int idFilm) {
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.minuti = minuti;
		this.idFilm = idFilm;
		this.incasso = 0.0;
	}

	/**
	 * Metodo per aggiungere una somma di denaro(Double) all'incasso totale del film
	 * 
	 * @param incasso
	 */
	public void addIncasso(Double incasso) {
		this.incasso += incasso;
	}

	public Double getIncasso() {
		return incasso;
	}

	/**
	 * Metodo che ritorna il titolo del film
	 * 
	 * @return Stringa con titolo
	 */
	public String getTitolo() {
		return this.titolo;
	}

	/**
	 * Metodo che ritorna la descrizione del film
	 * 
	 * @return Stringa con descrizione
	 */
	public String getDescrizione() {
		return this.descrizione;
	}

	/**
	 * Metodo che ritorna la durata in minuti
	 * 
	 * @return int con durata in minuti
	 */
	public int getMinuti() {
		return this.minuti;
	}

	/**
	 * Metodo che ritorna id identificativo del Film
	 * 
	 * @return int con id del film
	 */
	public int getIdFilm() {
		return this.idFilm;
	}

	/**
	 * Metodo per stampare tutte le informazioni del film.
	 * 
	 * @return stringa con: titolo, descrizione e durata
	 */
	public String toString() {
		return getClass().getSimpleName() + "[Id = "+idFilm+"Titolo = " + this.titolo + ", Descrizione = " + this.descrizione
				+ ", Durata = " + this.minuti + "Incasso = "+incasso+"]";
	}

	/**
	 * Metodo per clonare un oggetto film
	 * 
	 * @return Film clonato
	 */
	public Film clone() {
		try {
			Film clone = (Film) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Metodo per confrontare se due film sono uguali
	 * 
	 * @param o Object che deve essere di tipo Film
	 * @return true se i film hanno lo stesso stato, false altrimenti
	 */
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;

		Film f = (Film) o;

		return idFilm == f.idFilm && titolo.equals(f.getTitolo()) && descrizione.equals(f.getDescrizione()) && minuti == f.getMinuti()
				&& incasso == f.getIncasso();
	}
}
