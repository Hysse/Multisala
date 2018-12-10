package eccezioni;

public class SignUpException extends RuntimeException{

	private static final long serialVersionUID = 597686563954585989L;
	private String str;
	
	public SignUpException()
	{
		str = "Non è possibile registrare l'utente, username già presente";
	}
	
	public String getMessage()
	{
		return str;
	}

}
