package classi;

/**
 * Interfaccia che determina una politica di sconto
 */
public interface PoliticaSconto {

	/**
	 * Metodo da implementare che applica uno sconto a un prezzo in base a degli attributi
	 * di un cliente
	 * @param biglietto biglietto con il prezzo da scontare
	 * @return Double con il prezzo scontato se l'utente verifica le condizioni
	 */
	public double getSconto(Biglietto biglietto);
}
