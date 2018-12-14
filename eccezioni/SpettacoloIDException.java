package eccezioni;

public class SpettacoloIDException extends Exception{

	private static final long serialVersionUID = 1983323698380308937L;
	private String str;
	
	public SpettacoloIDException()
	{
		str = "L'ID inserito appartiene ad un altro spettacolo!";
	}
	
	public String getMessage()
	{
		return this.str;
	}

}
