package classi;

import java.io.Serializable;

public class Film implements Serializable{
	
	public Film(String titolo, String descrizione)
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
	
	private String descrizione;
	private String titolo;
	private static final long serialVersionUID = 2958174258762431745L;
}
