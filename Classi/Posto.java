package classi;

import java.io.Serializable;

/**
 * 
 * Posto rappresenta un singolo posto all'interno di una sala. Esso ha una lettera e un numero che indicano
 * l'ubicazione del posto in questione all'interno della sala. La variabile stato indica, mediante un char,
 * lo stato attuale del posto, che può essere libero ('L'), occupato ('O'), oppure indisponibile ('I').
 *	
 *	
 *	
 *
 */
public class Posto implements Serializable{
	/**
	 * Indica la lettera associata al posto.
	 */
	private char lettera;
	/**
	 * Indica il numero associato al posto.
	 */
	private int numero;
	/**
	 * Può valere 'L'(libero),'O'(Occupato) oppure 'I'(Indisponibile); indica lo stato del posto.
	 */
	private char stato;
	
	public Posto(char lettera,int numero)
	{
		this.lettera = lettera;
		this.numero = numero;
		stato = 'L';
	}
	/**
	 * 
	 * @return Restituisce la lettera associata al posto.
	 */
	public char getLet()
	{
		return lettera;
	}
	/**
	 * 
	 * @return Restituisce il numero associato al posto.
	 */
	public int getNum()
	{
		return numero;
	}
	/**
	 * 
	 * @return Restituisce true se il posto è libero, false altrimenti.
	 */
	public boolean isLibero()
	{
		return stato == 'L';
	}
	/**
	 * 
	 * @return Restituisce true se il posto è occupato, false altrimenti,
	 */
	public boolean isOccupato()
	{
		return stato == 'O';
	}
	/**
	 * 
	 * @return Restituisce true se il posto è indisponibile, false atrimenti.
	 */
	public boolean isIndisponibile()
	{
		return stato == 'I';
	}
	/**
	 * Imposta il posto come libero.
	 */
	public void setLibero()
	{
		stato = 'L';
	}
	/**
	 * Imposta il posto come occupato.
	 */
	public void setOccupato()
	{
		stato = 'O';
	}
	/**
	 * Imposta il posto come indisponibile.
	 */
	public void setIndisponibile()
	{
		stato = 'I';
	}
	/**Copia il Posto chiamante.
	 * @return Restituisce una copia del parametro implicito.
	 */
	public Posto clone()
	{
		Posto clone = new Posto(lettera,numero);
		clone.setLibero();
		return clone;
	}
	/**Metodo di accesso per le informazioni essenziali.
	 * @return Restituisce una stringa che indica le informazioni del Posto.
	 */
	public String toString()
	{
		return getClass(). getName() + "[Lettera : "+lettera+" Numero : "+numero+" Stato : "+stato+"]";
	}
	/**
	 * Metodo di controllo per verificare l'uguaglianza con un altro Posto.
	 * @return Restituisce true se obj ha le stesse caratteristiche del Posto chiamante, false altrimenti.
	 */
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		if(obj.getClass() != getClass())
			return false;
		Posto other = (Posto) obj;
		return other.lettera == lettera && other.numero == numero && other.stato == stato;
	}
}
