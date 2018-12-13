package classi;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe che rappresenta il concetto di Sala come un numero identificativo
 * univoco all'interno di un multisala e una collezione di Posto che indica i
 * posti di cui la Sala dispone.
 */
public class Sala implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Posto> posti;
	private int numSala;
	private int numeroColonne;

	/**
	 * Costruisce un oggetto Sala
	 * 
	 * @param numero Numero che identifica univocamente la sala all'interno del
	 *               cinema.
	 * @param posti  Collezione di posti iniziale della sala.
	 */
	public Sala(int numSala, ArrayList<Posto> posti,int numcol) {
		this.numSala = numSala;
		this.posti = posti;
		this.numeroColonne = numcol;
	}
	public int getNumColonne()
	{
		return numeroColonne;
	}
	/**
	 * Imposta un nuovo numero identificativo alla sala.
	 * 
	 * @param numero nuovo numero da assegnare alla sala.
	 */
	public void setNum(int numSala) {
		this.numSala = numSala;
	}

	/**
	 * Metodo che restituisce il numero della sala
	 * 
	 * @return Restituisce il numero della sala.
	 */
	public int getNumSala() {
		return numSala;
	}
	/**
	 * Metodo di accesso alla collezione di posti di una sala.
	 * @return Restituisce la collezione di posti di una sala.
	 */
	public ArrayList<Posto> getPosti()
	{
		return posti;
	}
	/**
	 * Metodo che restituisce la quantità di posti della sala.
	 * 
	 * @return Numero dei posti della sala.
	 */
	public int getNumPosti() {
		return posti.size();
	}

	/**
	 * Metodo di accesso di numero di posti disponibili.
	 * 
	 * @return Restituisce il numero di posti disponibili per o spettacolo.
	 */
	public int getNumPostiDisponibili() {
		int cont = 0;
		for (Posto p : posti) {
			if (p.isDisponibile())
				cont++;
		}
		return cont;
	}

	/**
	 * Metodo per l'inserimento di un posto in sala.
	 * 
	 * @param p Posto da inserire nella sala.
	 */
	public void addPosto(Posto p) {
		posti.add(p);
	}

	/**
	 * Metodo per la rimozione di un posto in sala.
	 * 
	 * @param p Posto da rimuovere dalla sala.
	 */
	public void removePosto(Posto p) {
		posti.remove(p);
	}

	/**
	 * Metodo che restituisce il puntatore a un Posto contenuto nella sala cercato
	 * per numero e per lettera.
	 * 
	 * @param lettera Lettera del posto da cercare.
	 * @param numero  Numero del posto da cercare.
	 * @return Restituisce il puntatore al Posto cercato se presente nella
	 *         collezione di posti della sala, altrimenti restituisce null.
	 */
	public Posto getPosto(char lettera, int numero) {
		Posto daCercare = new Posto(lettera, numero);
		for (Posto p : posti) {
			if (daCercare.matches(p))
				return p;
		}
		return null;
	}

	/**
	 * Metodo che restituisce il puntatore a un Posto contenuto nella sala cercato
	 * per uguaglianza con un Posto passato per parametro.
	 * 
	 * @param posto Posto da cercare.
	 * @return Restituisce il puntatore al Posto cercato se presente nella
	 *         collezione di posti della sala, altrimenti restituisce null.
	 */
	public Posto getPosto(Posto posto) {
		for (Posto p : posti) {
			if (p.matches(posto))
				return p;
		}
		return null;
	}

	/**
	 * Metodo per visualizzare in modo formattato i posti di una sala
	 * 
	 * @return Stringa con le informazioni dei posti
	 */
	public String displayPosti() {
		String stringa = "";
		for (Posto p : posti) {
			stringa += " Lettera : " + p.getLet() + " Numero: " + p.getNum() + " Disponibile : " + p.isDisponibile()
					+ " Occupato : " + p.isOccupato() + "\n";
		}
		return stringa;
	}

	/**
	 * Metodo di accesso per le informazioni essenziali.
	 * 
	 * @return Restituisce una stringa che indica le informazioni della Sala.
	 */
	public String toString() {
		return getClass().getName() + "[Numero Sala = " + numSala + " Posti = " + posti + "]";
	}

	/**
	 * Effettua una copia della Sala chiamante. N.B. : I Posti restituiti sono
	 * copie, quindi tramite questo metodo non si accede ai puntatori agli
	 * originali.
	 * 
	 * @return Restituisce una Sala con lo stesso numero e la stessa collezione di
	 *         Posti.
	 */
	public Sala clone() {
		try {
			Sala clone = (Sala) super.clone();
			ArrayList<Posto> posticlone = new ArrayList<Posto>();
			for (Posto p : posti)
				posticlone.add(p.clone());
			clone.posti = posticlone;
			return clone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Metodo per il controllo dell'uguaglianza tra la Sala chiamante e un Oggetto
	 * obj.
	 * 
	 * @param obj Oggetto da confrontare.
	 * @return true se le Sale hanno lo stesso stato, false altrimenti
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != getClass())
			return false;
		Sala other = (Sala) obj;
		return other.numSala == numSala && other.posti.equals(posti);
	}
}
