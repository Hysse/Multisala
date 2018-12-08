package classi;

import java.io.Serializable;

/**
 * Questa classe viene utilizzata per salvare
 * username e password relativo ad un utente.
 * Inoltre viene indicato se l'utente è un gestore e
 * ha un ID progressivo.
 */

public class Utente implements Serializable, Cloneable{
	
	private static final long serialVersionUID = 8324800419465457181L;
	private String username;
	private String password;
	/**
	 * num_prog è una variabile statica che viene incrementata
	 * ogni volta che viene creato un utente a cui viene assegnato come ID
	 */
	private static int num_prog = 0;
	private int id;
	private boolean isGestore;
	
	/**
	 * Il costruttore prende username(stringa), password(stringa) e un boolean per
	 * impostare o meno l'utente a gestore
	 * @param username username dell'utente
	 * @param password password dell'utente
	 * @param isGestore indica se l'utente deve essere un gestore
	 */
	public Utente(String username, String password, Boolean isGestore)
	{
		this.username = username;
		this.password = password;
		this.isGestore = isGestore;
		id = num_prog;
		num_prog++;
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
	 * Il metodo ritorna la password dell'utente
	 * @return Stringa con la password
	 */
	public String getPassword()
	{
		return this.password;
	}
	
	/**
	 * Il metodo ritorna l'ID dell'utente
	 * @return int con l'id
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Il metodo ritorna la variabile booleana isGestore
	 * @return true se l'utente è un gestore, false altrimenti
	 */
	public boolean isGestore()
	{
		return this.isGestore;
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
	 * Metodo modificatore per cambiare i permessi dell'utente
	 * @param bool true se l'utente deve essere gestore, false altrimenti
	 */
	public void setGestore(Boolean bool)
	{
		this.isGestore = bool;
	}
	
	/**
	 * Metodo per stampare tutte le informazione dell'Utente
	 * @return stringa con: username, password, ID e permessi gestore
	 */
	public String toString()
	{
		return getClass().getSimpleName() + "[Username = " + this.username + ", Password = "
				+ this.password + ", ID utente = " + this.id + ", E' un gestore = " + this.isGestore + "]";
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
			e.printStackTrace();;
		}
		
		return null;
	}
	
	/**
	 * @param o Object che deve essere di tipo utente
	 * @return true se due utenti sono uguali, false altrimenti
	 */
	public boolean equals(Object o)
	{
		if (o == null || getClass() != o.getClass())
			return false;
		
		Utente u = (Utente) o;
		
		return username.equals(u.getUsername()) && password.equals(u.getPassword())
				&& id == u.getId() && isGestore == u.isGestore();
	}
	
}
