package classi;

import java.io.Serializable;

/**
 * Questa classe viene utilizzata per salvare
 * username e password relativo ad un utente.
 * Inoltre ha un ID progressivo.
 */

public class Utente implements Serializable, Cloneable{
	
	private static final long serialVersionUID = 8324800419465457181L;
	private String username;
	private String password;
	private boolean isGestore;
	/**
	 * Il costruttore prende username(stringa), password(stringa) e un boolean per
	 * impostare o meno l'utente a gestore o a cliente.
	 * @param username username dell'utente.
	 * @param password password dell'utente.
	 * @param indica se l'Utente è un gestore oppure un cliente.
	 */
	public Utente(String username, String password,boolean isGestore)
	{
		this.username = username;
		this.password = password;
		this.isGestore = isGestore;
	}
	
	public boolean isGestore()
	{
		return isGestore;
	}
	/**
	 * Il metodo ritorna l'username dell'utente
	 * @return Stringa con l'username
	 */
	public String getUsername()
	{
		return this.username;
	}
	/**
	 * Metodo modificatore per impostare un Utente come gestore.
	 */
	public void setGestore()
	{
		isGestore = true;
	}
	/**
	 * Il metodo ritorna la password dell'utente
	 * @return Stringa con la password
	 */
	public String getPassword()
	{
		return this.password;
	}
	/**
	 * Metodo modificatore per cambiare l'username
	 * @param username nuovo username dell'utente
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	/**
	 * Metodo modificatore per cambiare la password
	 * @param password nuova password dell'utente
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	/**
	 * Metodo per stampare tutte le informazione dell'Utente
	 * @return stringa con: username, password, ID.
	 */
	public String toString()
	{
		return getClass().getSimpleName() + "[Username = " + this.username + ", Password = "
				+ this.password + ", isGestore = "+isGestore+ "]";
	}
	
	/**
	 * Metodo per clonare un utente
	 * @return Utente clonato
	 */
	public Utente clone()
	{
		try {
			Utente clone = (Utente) super.clone();
			return clone;
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @param o Object che deve essere di tipo utente
	 * @return true se due utenti hanno lo stesso stato, false altrimenti
	 */
	public boolean equals(Object o)
	{
		if (o == null || getClass() != o.getClass())
			return false;
		
		Utente u = (Utente) o;
		
		return username.equals(u.getUsername()) && password.equals(u.getPassword());
	}
	
}
