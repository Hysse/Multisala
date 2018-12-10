package eccezioni;

public class OraSpettacoloException extends RuntimeException{

	private static final long serialVersionUID = 7956048222594311507L;
	private String str;
	
	public OraSpettacoloException()
	{
		str = "Non può essere inserito lo spettacolo poichè nell'ora in cui inizia"
				+ "è gia presente un altro spettacolo che ancora deve finire";
	}
	
	public String getMessage()
	{
		return str;
	}

}
