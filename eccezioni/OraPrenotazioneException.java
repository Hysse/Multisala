package eccezioni;

public class OraPrenotazioneException extends Exception{

	private static final long serialVersionUID = 1983323698380308937L;
	private String str;
	
	public OraPrenotazioneException()
	{
		str = "Impossibile prenotare il posto per lo spettacolo"
				+ " poichè le prenotazioni scadono 12 ore prima dello spettacolo";
	}
	
	public String getMessage()
	{
		return this.str;
	}

}
