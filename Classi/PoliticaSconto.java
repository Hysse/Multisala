package classi;

import java.io.Serializable;

/**
 * Interfaccia che determina una politica di sconto
 */
public abstract class PoliticaSconto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo da implementare che applica uno sconto a un prezzo in base a degli attributi
	 * di un cliente
	 * @param biglietto biglietto con il prezzo da scontare
	 * @return Double con il prezzo scontato se l'utente verifica le condizioni
	 */
	public abstract double getSconto(Biglietto biglietto);
}
