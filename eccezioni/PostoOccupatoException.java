package eccezioni;

public class PostoOccupatoException extends Exception{

	private static final long serialVersionUID = 1983323698380308937L;
	private String str;
	
	public PostoOccupatoException()
	{
		str = "Il posto che si vuole prenotare è già occupato";
	}
	
	public String getMessage()
	{
		return this.str;
	}

}
