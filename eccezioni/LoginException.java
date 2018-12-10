package eccezioni;

public class LoginException extends RuntimeException{

	private static final long serialVersionUID = 858835404320966185L;
	private String str;
	
	public LoginException()
	{
		str = "Non è possibile effettuare il login username o password errati";
	}
	
	public String getMessage()
	{
		return str;
	}

}
