package classi;

import java.io.Serializable;

/**
 * Classe astratta che determina una politica di sconto
 */
public abstract class PoliticaSconto implements Cloneable, Serializable{

	private static final long serialVersionUID = 7290073301037036149L;

	/**
	 * Metodo da implementare che applica uno sconto a un prezzo in base a degli attributi
	 * di un cliente
	 * @param biglietto biglietto con il prezzo da scontare
	 * @return Double con il prezzo scontato se l'utente verifica le condizioni
	 */
	public abstract double getSconto(Biglietto biglietto);
	
	/**
	 * Metodo per clonare una politica di sconto
	 */
	public PoliticaSconto clone()
	{
		try {
			PoliticaSconto p = (PoliticaSconto) super.clone();
			return p;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo per verificare che due politiche di sconto siano uguali in base al nome della classe
	 */
	public boolean equals(Object o)
	{
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		else
			return getClass().getName() == o.getClass().getName();
	}
}

