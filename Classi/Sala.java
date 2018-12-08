package classi;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * Classe che rappresenta il concetto di Sala come un numero identificativo univoco all'interno di un multisala
 * e una collezione di Posto che indica i posti di cui la Sala dispone.
 *
 */
public class Sala implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Indica il numero che identifica univocamente la sala all'interno del cinema.
	 */
	private int numero;
	/**
	 * E' una collezione di posti di cui la sala dispone.
	 */
	private ArrayList<Posto> posti;
	/**
	 * Costruisce un oggetto Sala
	 * @param numero Numero che identifica univocamente la sala all'interno del cinema.
	 * @param posti Collezione di posti iniziale della sala.
	 */
	public Sala(int numero,ArrayList<Posto> posti)
	{
		this.numero = numero;
		this.posti = posti;
	}
	/**
	 * Imposta un nuovo numero identificativo alla sala.
	 * @param numero nuovo numero da assegnare alla sala.
	 */
	public void setNum(int numero)
	{
		this.numero = numero;
	}
	/**
	 * 
	 * @return Restituisce il numero della sala.
	 */
	public int getNum()
	{
		return numero;
	}
	/**
	 * Metodo che restituisce la quantità di posti della sala.
	 * @return Numero dei posti della sala.
	 */
	public int getNumPosti()
	{
		return posti.size();
	}
	/**
	 * Metodo per l'inserimento di un posto in sala.
	 * @param p Posto da inserire nella sala.
	 */
	public void addPosto(Posto p)
	{
		posti.add(p);
	}
	/**
	 * Metodo per la rimozione di un posto in sala.
	 * @param p Posto da rimuovere dalla sala.
	 */
	public void removePosto(Posto p)
	{
		posti.remove(p);
	}
	/**
	 * Metodo che restituisce il puntatore a un Posto contenuto nella sala cercato per numero e per lettera.
	 * @param lettera Lettera del posto da cercare.
	 * @param numero Numero del posto da cercare.
	 * @return Restituisce il puntatore al Posto cercato se presente nella collezione di posti della sala, altrimenti restituisce null.
	 */
	public Posto getPosto(char lettera,int numero)
	{
		Posto daCercare = new Posto(lettera,numero);
		for(Posto p : posti)
		{
			if(daCercare.matches(p))
				return p;
		}
		return null;
	}
	/**Metodo di accesso per le informazioni essenziali.
	 * @return Restituisce una stringa che indica le informazioni della Sala.
	 */
	public String toString()
	{
		return getClass().getName() + "[Numero : "+numero+" Posti : "+posti+"]";
	}
	/**
	 * Effettua una copia della Sala chiamante.
	 * @return Restituisce una Sala con lo stesso numero e la stessa collezione di Posti. N.B. : I Posti restituiti sono copie, quindi tramite questo metodo non si accede ai puntatori agli originali.
	 * 
	 */
	public Sala clone()
	{
		ArrayList<Posto> posticlone = new ArrayList<Posto>();
		for(Posto p : posti)
			posticlone.add(p.clone());
		Sala clone = new Sala(numero,posticlone);
		return clone;
	}
	/**
	 * Metodo per il controllo dell'uguaglianza tra la Sala chiamante e un Oggetto obj.
	 * @param obj Oggetto da confrontare.
	 */
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		if(obj.getClass() != getClass())
			return false;
		Sala other = (Sala) obj;
		return other.numero == numero && other.posti.equals(posti);
	}
}
