package classi;

import java.io.Serializable;

public final class Film implements Serializable{
	
	public Film(String titolo, String descrizione, int minuti)
	{
		this.titolo = titolo;
		this.descrizione = descrizione;
	}
	
	public String getTitolo()
	{
		return this.titolo;
	}
	
	public String getDescrizione()
	{
		return this.descrizione;
	}
	
	public int getMinuti()
	{
		return this.minuti;
	}
	
	
	private String descrizione;
	private String titolo;
	private int minuti;
	private static final long serialVersionUID = 2958174258762431745L;
}
