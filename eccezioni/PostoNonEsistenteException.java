package eccezioni;

public class PostoNonEsistenteException extends Exception{

	private static final long serialVersionUID = 1983323698380308937L;
	private String str;
	
	public PostoNonEsistenteException()
	{
		str = "Il posto che si vuole prenotare non esiste";
	}
	
	public String getMessage()
	{
		return this.str;
	}

}
