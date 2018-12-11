package classi;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Rappresenta il concetto di Spettacolo come Film proiettato in una Sala in una determinata
 *  data (anno,mese,giorno,ora).
 */
public class Spettacolo implements Cloneable, Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * La sala in cui lo spettacolo si tiene. In sala vengono anche memorizzati gli stati dei posti relativi a questo spettacolo.
	 */
	private Sala sala;
	/**
	 * Numero che identifica univocamente uno spettacolo.
	 */
	private static int progressivo = 0;
	/**
	 * variabile che conterr� il progressivo relativo allo Spettacolo.
	 */
	private int id;
	/**
	 * Il prezzo base dello spettacolo.
	 */
	private double prezzo;
	/**
	 * Il film che � proiettato durante lo spettacolo.
	 */
	private Film film;
	/**
	 * La data in cui il film � proiettato.
	 */
	private Calendar data;
	
	/**
	 * Crea uno Spettacolo partendo dalla Sala in cui � proiettato, dal film che � 
	 * proiettato e dalla dat� dello spettacolo.
	 * @param sala Sala in cui il film � proiettato.
	 * @param film Film proiettato durante lo spettacolo.
	 * @param data Data dello spettacolo. (anno,mese,giorno,ora).
	 * @param prezzo Double per il prezzo dello spettacolo
	 */
	public Spettacolo(Sala sala, Film film, Calendar data,double prezzo)
	{
		this.sala = sala;
		this.film = film;
		this.data = data;
		this.prezzo = prezzo;
		id = progressivo;
		progressivo++;
	}
	
	/**
	 * Metodo di accesso al prezzo base dello spettacolo.
	 * @return Restituisce il prezzo base dello spettacolo.
	 */
	public double getPrezzo()
	{
		return prezzo;
	}
	
	/**
	 * Metodo di accesso alla sala di uno spettacolo.
	 * @return Restituisce un clone della sala in cui si tiene.
	 */
	public Sala getSala()
	{
		return sala.clone();
	}
	
	/**
	 * Metodo di accesso al film di uno spettacolo.
	 * @return Restituisce il film dello spettacolo.
	 */
	public Film getFilm()
	{
		return film.clone();
	}
	
	/**
	 * Metodo di accesso all'id dello spettacolo
	 * @return int con id dello spettacolo
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Metodo di accesso alla data di uno spettacolo.
	 * @return Restituisce la data di uno spettacolo.
	 */
	public Calendar getDataInizio()
	{
		return (Calendar)data.clone();
	}
	
	/**
	 * Metodo modificatore del prezzo base di uno spettacolo.
	 * @param nuovoPrezzo Nuovo prezzo da assegnare allo spettacolo.
	 */
	public void setPrezzo(double nuovoPrezzo)
	{
		prezzo = nuovoPrezzo;
	}
	
	/**
	 * Metodo modificatore per cambiare sala ad uno spettacolo.
	 * @param sala La nuova sala dello spettacolo.
	 */
	public void setSala(Sala nuovaSala)
	{
		this.sala = nuovaSala;
	}
	
	/**
	 * Metodo modificatore per cambiare data ad uno spettacolo.
	 * @param data La nuova data dello spettacolo.
	 */
	public void setData(Calendar nuovaData)
	{
		this.data = nuovaData;
	}
	
	/**
	 * Metodo di accesso alla data di fine spettacolo (quindi la data inizio addizionata dei minuti del film).
	 * @return Restituisce la data di fine spettacolo.
	 */
	@SuppressWarnings("deprecation")
	public Calendar getDataFine()
	{
		Calendar datafine = (Calendar)data.clone();
		datafine.set(Calendar.MINUTE, datafine.get(Calendar.MINUTE) + film.getMinuti());
		return datafine;
	}
	
	/**
	 * Metodo modificatore per l'occupazione di un posto riguardante lo spettacolo. Occupa un posto della sala relativo allo spettacolo.
	 * @param p Il posto da occupare.
	 * @return Restituisce il posto appena occupato se esiste, altrimenti restituisce null;
	 */
	public Posto occupa(Posto posto)
	{
		Posto p = sala.getPosto(posto);
				if(p == null)
					return null;
				else
				{
					p.setOccupato();
					return p;
				}
	}
	
	/**
	 * Metodo modificatore per la liberazione di un posto riguardante lo spettacolo. Libera un posto della sala relativo allo spettacolo.
	 * @param posto Il posto da liberare.
	 * @return Restituisce il posto appena liberato se esiste, altrimenti restituisce null;
	 */
	public Posto libera(Posto posto)
	{
		Posto p = sala.getPosto(posto);
		if(p == null)
			return null;
		else
		{
			p.setLibero();
			return p;
		}
	}
	
	/**
	 * Metodo di accesso ai dati fondamentali dello Spettacolo.
	 * @return Restituisce una stringa con i dati fondamentali dello Spettacolo.
	 */
	public String toString()
	{
		return getClass().getName()+"[Sala = "+sala+" Film = "+film+" Data = "+data+" Prezzo = "+prezzo+"]";
	}
	
	/**
	 * Metodo di clonazione per Spettacolo.
	 * @return Restituisce un puntatore ad una copia esatta dello Spettacolo chiamante.
	 */
	public Spettacolo clone()
	{
		try {
			Spettacolo clone = (Spettacolo) super.clone();
			clone.film = film.clone();
			clone.sala = sala.clone();
			clone.data = (Calendar)data.clone();
			return clone;
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo di controllo dell'uguaglianza di uno Oggetto con lo Spettacolo chiamante.
	 * @param obj Oggetto di cui controllare l'uguaglianza con lo Spettacolo chiamante.
	 * @return Restituisce true se il chiamante e obj hanno lo stesso stato, false altrimenti.
	 */
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		if(obj.getClass() != getClass())
			return false;
		Spettacolo other = (Spettacolo) obj;
		return other.data.equals(data) && other.film.equals(film) && other.sala.equals(sala) && other.prezzo == prezzo;
	}
}
