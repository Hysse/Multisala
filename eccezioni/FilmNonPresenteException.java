package eccezioni;

public class FilmNonPresenteException extends RuntimeException{
	
	private static final long serialVersionUID = -3328461718730781857L;
	private String str;
	
	public FilmNonPresenteException()
	{
		str = "Il film non è presente nella lista dei Film";
	}
	
	public String getMessage()
	{
		return str;
	}

}
