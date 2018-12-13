package eccezioni;

public class BigliettoPresenteException extends Exception{

	private static final long serialVersionUID = 1983323698380308937L;
	private String str;
	
	public BigliettoPresenteException()
	{
		str = "Il Biglietto che si vuole prenotare/acquistare � per uno Spettacolo di cui si ha gi� un biglietto";
	}
	
	public String getMessage()
	{
		return this.str;
	}

}
