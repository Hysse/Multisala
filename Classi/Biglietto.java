package classi;

import java.io.Serializable;
/**
 * Rappresenta il concetto di Biglietto per un determinato spettacolo e per un posto, con associato un prezzo. 
 * Un biglietto ha anche un stato che ne indica l'avvenuto acquisto.
 *
 */
public class Biglietto implements Cloneable, Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Spettacolo associato al biglietto.
	 */
	private final Spettacolo spettacolo;
	/**
	 * Lettera del posto della sala associata allo spettacolo del biglietto.
	 */
	private final char lettera;
	/**
	 * Numero del posto della sala associata allo spettacolo del biglietto.
	 */
	private final int numero;
	/**
	 * Variabile booleana che indica se il biglietto è in realtà una prenotazione oppure un biglietto acquistato.
	 */
	private boolean acquistato;
	/**
	 * Prezzo del biglietto.
	 */
	private double prezzo;
	/**
	 * Metodo costruttore del biglietto.
	 * @param spettacolo Lo spettacolo che si vuole associare al biglietto.
	 * @param lettera La lettera del posto relativo al biglietto.
	 * @param numero Il numero relativo al posto del biglietto.
	 * @param acquistato Variabile booleana che indica se un biglietto è una prenotazione oppure no.
	 * @param prezzo Il prezzo del biglietto.
	 */
	public Biglietto(Spettacolo spettacolo, char lettera,int numero, boolean acquistato, double prezzo)
	{
		this.spettacolo = spettacolo;
		this.lettera = lettera;
		this.numero = numero;
		this.acquistato = acquistato;
		this.prezzo = prezzo;
	}
	/**
	 * Metodo di accesso allo spettacolo relativo al biglietto.
	 * @return Restituisce una copia dello spettacolo relativo al biglietto.
	 */
	public Spettacolo getSpettacolo()
	{
		return spettacolo.clone();
	}
	/**
	 * Metodo di accesso alla lettera del posto relativo al un biglietto.
	 * @return Restituisce la lettera del posto associato al biglietto.
	 */
	public char getLetteraPosto()
	{
		return lettera;
	}
	/**
	 * Metodo di accesso al numero del posto relativo al un biglietto.
	 * @return Restituisce il numero del posto associato al biglietto.
	 */
	public int getNumeroPosto()
	{
		return numero;
	}
	/**
	 * Metodo di identificazione del tipo di biglietto
	 * @return Restituisce true se il biglietto è una prenotazione ed è quindi non "acquistato", false altrimenti.
	 */
	public boolean isPrenotazione()
	{
		return !(acquistato);
	}
	/**
	 * Metodo di accesso al prezzo del biglietto.
	 * @return Restituisce il prezzo del biglietto.
	 */
	public double getPrezzo()
	{
		return prezzo;
	}
	/**
	 * Metodo modificatore del tipo di biglietto. Cambia lo stato del biglietto in "acquistato".
	 */
	public void setAcquistato()
	{
		acquistato = true;
	}
	/**
	 * Metodo modificatore per il prezzo del biglietto.
	 * @param prezzo Il nuovo prezzo da assegnare al biglietto.
	 */
	public void setPrezzo(double prezzo)
	{
		this.prezzo = prezzo;
	}
	/**
	 * Metodo di visualizzazione delle informazioni principali del Biglietto.
	 * @return Restituisce una stringa con le informazioni principali del Biglietto.
	 */
	public String toString()
	{
		return getClass().getName()+"[Spettacolo = "+spettacolo+" Lettera = "+lettera+"Numero = "+numero+" Acquistato = "+acquistato+" Prezzo = "+prezzo+"]";
	}
	/**
	 * Metodo di clonazione che effettua una copia del Biglietto chiamante.
	 * @return Restituisce un puntatore alla copia del Biglietto chiamante.
	 */
	public Biglietto clone()
	{
		try
		{
			Biglietto clone = (Biglietto) super.clone();
			return clone;
		}
		catch(CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Metodo di confronto dell'uguaglianza tra un Oggetto obj ed il Biglietto chiamante.
	 * @param obj L'Oggetto con cui confrontare il Biglietto chiamante.
	 * @return Restituisce true se l'Oggetto obj ha lo stesso stato del Biglietto chiamante, false altrimenti.
	 */
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		if(obj.getClass() != getClass())
			return false;
		Biglietto other = (Biglietto) obj;
		return other.acquistato == acquistato && other.numero == numero && other.lettera == lettera && other.spettacolo.equals(spettacolo) && other.prezzo == prezzo;
	}
	
}
