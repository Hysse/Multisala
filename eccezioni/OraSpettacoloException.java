package eccezioni;

public class OraSpettacoloException extends RuntimeException{

	private static final long serialVersionUID = 7956048222594311507L;
	private String str;
	
	public OraSpettacoloException()
	{
		str = "Non pu� essere inserito lo spettacolo poich� nell'ora in cui inizia"
				+ "� gia presente un altro spettacolo che ancora deve finire";
	}
	
	public String getMessage()
	{
		return str;
	}

}
