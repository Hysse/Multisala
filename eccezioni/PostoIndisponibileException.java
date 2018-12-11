package eccezioni;

public class PostoIndisponibileException extends RuntimeException{

	private static final long serialVersionUID = -6202665977119824678L;
	private String str;
	
	public PostoIndisponibileException()
	{
		str = "Impossibile acquistare o prenotare il posto perchè è indisponibile";
	}
	
	public String getMessage()
	{
		return str;
	}

}
