package eccezioni;

public class SignUpException extends RuntimeException{

	private static final long serialVersionUID = 597686563954585989L;
	private String str;
	
	public SignUpException()
	{
		str = "Non � possibile registrare l'utente, username gi� presente";
	}
	
	public String getMessage()
	{
		return str;
	}

}
