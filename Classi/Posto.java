package classi;

import java.io.Serializable;

/**
 * Posto rappresenta un singolo posto all'interno di una sala. Esso ha una
 * lettera e un numero che indicano l'ubicazione del posto in questione
 * all'interno della sala. La variabile stato indica, mediante un char, lo stato
 * attuale del posto, che pu� essere libero/occupato e
 * disponibile/indisponibile.
 */
public class Posto implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private char lettera;
	private int numero;
	private boolean isLibero;
	private boolean isDisponibile;

	/**
	 * Costruisce un oggetto Posto.
	 * 
	 * @param lettera lettera che identifica il posto.
	 * @param numero  numero che identifica il posto.
	 */
	public Posto(char lettera, int numero) {
		this.lettera = lettera;
		this.numero = numero;
		isDisponibile = true;
		isLibero = true;
	}

	/**
	 * Metodo di restituzione della lettera del un posto.
	 * 
	 * @return Restituisce la lettera associata al posto.
	 */
	public char getLet() {
		return lettera;
	}

	/**
	 * Metodo di accesso allo stato del Posto.
	 * 
	 * @return Restituisce true se disponibile, false altrimenti.
	 */
	public boolean isDisponibile() {
		return isDisponibile;
	}

	/**
	 * Metodo di restituzione del numero del un posto.
	 * 
	 * @return Restituisce il numero associato al posto.
	 */
	public int getNum() {
		return numero;
	}

	/**
	 * Metodo di restituzione dello stato del un posto.
	 * 
	 * @return Restituisce true se il posto � libero, false altrimenti.
	 */
	public boolean isLibero() {
		return isLibero;
	}

	/**
	 * Metodo di restituzione dello stato del posto.
	 * 
	 * @return Restituisce true se il posto � occupato, false altrimenti,
	 */
	public boolean isOccupato() {
		return !isLibero();
	}

	/**
	 * Imposta il posto come libero.
	 */
	public void setLibero() {
		isLibero = true;
	}

	/**
	 * Imposta il posto come occupato.
	 */
	public void setOccupato() {
		isLibero = false;
	}

	/**
	 * Imposta il posto come indisponibile.
	 */
	public void setIndisponibile() {
		isDisponibile = false;
	}

	/**
	 * Imposta il posto come disponibile
	 */
	public void setDisponibile() {
		isDisponibile = true;
	}

	/**
	 * Copia il Posto chiamante.
	 * 
	 * @return Restituisce una copia del parametro implicito.
	 */
	public Posto clone() {
		try {
			Posto clone = (Posto) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	/**
	 * Metodo di accesso per le informazioni essenziali.
	 * 
	 * @return Restituisce una stringa che indica le informazioni del Posto.
	 */
	public String toString() {
		return getClass().getName() + "[Lettera = " + lettera + ", Numero = " + numero + ", Disponibile = "
				+ isDisponibile + ",Libero = " + isLibero + "]";
	}

	/**
	 * Metodo di controllo per verificare l'uguaglianza con un altro Posto.
	 * 
	 * @param obj Oggetto di cui verificare l'uguaglianza.
	 * @return Restituisce true se obj ha le stesse caratteristiche del Posto
	 *         chiamante, false altrimenti.
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != getClass())
			return false;
		Posto other = (Posto) obj;
		return other.lettera == lettera && other.numero == numero && other.isDisponibile == isDisponibile
				&& other.isLibero == isLibero;
	}

	/**
	 * Metodo di controllo per verificare il matching tra due posti (numero e
	 * lettera uguali senza considerare lo stato).
	 * 
	 * @param other Posto di cui verificare il matching.
	 * @return true se la lettera e il numero del posti sono uguali, false
	 *         altrimenti
	 */
	public boolean matches(Posto other) {
		if (other == null)
			return false;
		return other.lettera == lettera && other.numero == numero;
	}
}
