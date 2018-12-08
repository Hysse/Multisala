package classi;

import java.util.Date;
/**
 * Rappresenta il concetto di Spettacolo come Film proiettato in una Sala in una determinata data (anno,mese,giorno,ora).
 * 
 *
 */
public class Spettacolo {
	/**
	 * La sala in cui lo spettacolo si tiene. In sala vengono anche memorizzati gli stati dei posti relativi a questo spettacolo.
	 */
	private Sala sala;
	/**
	 * Il prezzo base dello spettacolo.
	 */
	private double prezzo;
	/**
	 * Il film che è proiettato durante lo spettacolo.
	 */
	private Film film;
	/**
	 * La data in cui il film è proiettato.
	 */
	private Date data;
	/**
	 * Crea uno Spettacolo partendo dalla Sala in cui è proiettato, dal film che è proiettato e dalla datà dello spettacolo.
	 * 
	 * @param sala Sala in cui il film è proiettato.
	 * @param film Film proiettato durante lo spettacolo.
	 * @param data Data dello spettacolo. (anno,mese,giorno,ora).
	 */
	public Spettacolo(Sala sala, Film film, Date data,double prezzo)
	{
		this.sala = sala;
		this.film = film;
		this.data = data;
		this.prezzo = prezzo;
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
	 * Metodo di accesso alla data di uno spettacolo.
	 * @return Restituisce la data di uno spettacolo.
	 */
	public Date getDataInizio()
	{
		return data;
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
	public void setData(Date nuovaData)
	{
		this.data = nuovaData;
	}
	/**
	 * Metodo di accesso alla data di fine spettacolo (quindi la data inizio addizionata dei minuti del film).
	 * @return Restituisce la data di fine spettacolo.
	 */
	public Date getDataFine()
	{
		Date datafine = (Date)data.clone();
		datafine.setMinutes(datafine.getMinutes()+film.getMinuti());
		return datafine;
	}
	/**
	 * Metodo modificatore per l'occupazione di un posto riguardante lo spettacolo. Occupa un posto della sala relativo allo spettacolo.
	 * @param lettera La lettera del posto da occupare.
	 * @param numero Il numero del posto da occupare.
	 * @return Restituisce il posto appena occupato se esiste, altrimenti restituisce null;
	 */
	public Posto occupa(char lettera,int numero)
	{
		Posto p = sala.getPosto(lettera, numero);
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
	 * @param lettera La lettera del posto da liberare.
	 * @param numero Il numero del posto da liberare.
	 * @return Restituisce il posto appena liberato se esiste, altrimenti restituisce null;
	 */
	public Posto libera(char lettera,int numero)
	{
		Posto p = sala.getPosto(lettera, numero);
		if(p == null)
			return null;
		else
		{
			p.setLibero();
			return p;
		}
	}
	
}
