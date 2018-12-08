package classi;

import java.io.Serializable;

/**
 * Questa classe è immutabile(final) e viene utilizzata per salvare
 * descizione, titolo e durata(in minuti) di un film.
 */
public final class Film implements Serializable{

	private String descrizione;
	private String titolo;
	private int minuti;
	private static final long serialVersionUID = 2958174258762431745L;
	
	/**
	 * Il costruttore prende una Stringa titolo, una String descizione e un in per i minuti
	 * @param titolo titolo del film
	 * @param descrizione descrizione del film con breve trama
	 * @param minuti durata del film in minuti
	 */
	public Film(String titolo, String descrizione, int minuti)
	{
		this.titolo = titolo;
		this.descrizione = descrizione;
	}
	
	/**
	 * Metodo che ritorna il titolo del film
	 * @return Stringa con titolo
	 */
	public String getTitolo()
	{
		return this.titolo;
	}
	
	/**
	 * Metodo che ritorna la descrizione del film
	 * @return Stringa con descrizione
	 */
	public String getDescrizione()
	{
		return this.descrizione;
	}
	
	/**
	 * Metodo che ritorna la durata in minuti
	 * @return int con durata in minuti
	 */
	public int getMinuti()
	{
		return this.minuti;
	}
	
	/**
	 * Metodo per stampare tutte le informazioni del film.
	 * @return stringa con: titolo, descrizione e durata
	 */
	public String toString()
	{
		return getClass().getSimpleName() + "[Titolo = " + this.titolo + ", Descrizione = "
				+ this.descrizione + ", Durata = " + this.minuti + "]";			
	}
	
}
