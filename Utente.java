package classi;

public class Utente {
	
	public Utente(String username, String password)
	{
		this.username = username;
		this.password = password;
		id = num_prog;
		num_prog++;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public int getId()
	{
		return id;
	}
	
	
	private String username;
	private String password;
	private static int num_prog = 0;
	private int id;
}
