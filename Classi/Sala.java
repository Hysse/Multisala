package classi;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe che rappresenta il concetto di Sala come un numero identificativo univoco all'interno di un multisala
 * e una collezione di Posto che indica i posti di cui la Sala dispone.
 */
public class Sala implements Serializable, Cloneable{

	private static final long serialVersionUID = 1L;
	/**
	 * Indica il numero che identifica univocamente la sala all'interno del cinema.
	 */
	private int numeroPosti;
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
		this.numeroPosti = numero;
		this.posti = posti;
	}
	
	/**
	 * Imposta un nuovo numero identificativo alla sala.
	 * @param numero nuovo numero da assegnare alla sala.
	 */
	public void setNum(int numero)
	{
		this.numeroPosti = numero;
	}
	
	/**
	 * 
	 * @return Restituisce il numero della sala.
	 */
	public int getNumSala()
	{
		return numeroPosti;
	}
	
	/**
	 * Metodo che restituisce la quantità di posti della sala.
	 * @return Numero dei posti della sala.
	 */
	public int getNumPosti()
	{
		return posti.size();
	}
	public int getNumPostiLiberi()
	{
		int cont = 0;
		for(Posto p : posti)
		{
			if(p.isLibero())
				cont++;
		}
		return cont;
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
	
	/**
	 * Metodo che restituisce il puntatore a un Posto contenuto nella sala cercato per uguaglianza con un Posto passato per parametro.
	 * @param p Posto da cercare.
	 * @return Restituisce il puntatore al Posto cercato se presente nella collezione di posti della sala, altrimenti restituisce null.
	 */
	public Posto getPosto(Posto p)
	{
		int pos = posti.indexOf(p);
		if(pos < 0)
			return null;
		else
			return posti.get(pos);
	}
	
	/**
	 * 
	 * @return
	 */
	public String displayPosti()
	{
		String stringa = "";
		for(Posto p : posti)
		{
			stringa += " Lettera : "+p.getLet()+" Numero : "+p.getNum()+
					" Disponibile : "+p.isDisponibile()+ " Occupato : " + p.isOccupato() + "\n";
		}
		return stringa;
	}
	/**Metodo di accesso per le informazioni essenziali.
	 * @return Restituisce una stringa che indica le informazioni della Sala.
	 */
	public String toString()
	{
		return getClass().getName() + "[Numero = " + numeroPosti + " Posti = " + posti + "]";
	}
	
	/**
	 * Effettua una copia della Sala chiamante.
	 * @return Restituisce una Sala con lo stesso numero e la stessa collezione di Posti.
	 * N.B. : I Posti restituiti sono copie, quindi tramite questo metodo non si accede ai puntatori agli originali.
	 */
	public Sala clone()
	{
		try {
			Sala clone = (Sala)super.clone();
			ArrayList<Posto> posticlone = new ArrayList<Posto>();
			for(Posto p : posti)
				posticlone.add(p.clone());
			clone.posti = posticlone;
			return clone;
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Metodo per il controllo dell'uguaglianza tra la Sala chiamante e un Oggetto obj.
	 * @param obj Oggetto da confrontare.
	 * @return true se le Sale hanno lo stesso stato, false altrimenti
	 */
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		if(obj.getClass() != getClass())
			return false;
		Sala other = (Sala) obj;
		return other.numeroPosti == numeroPosti && other.posti.equals(posti);
	}
}
