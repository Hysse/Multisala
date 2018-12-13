package classi;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Rappresenta il concetto di Spettacolo come Film proiettato in una Sala in una
 * determinata data (anno,mese,giorno,ora).
 */
public class Spettacolo implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Sala sala;
	private double prezzo;
	private Film film;
	private Calendar data;

	/**
	 * Crea uno Spettacolo partendo dalla Sala in cui è proiettato, dal film che è
	 * proiettato e dalla datà dello spettacolo.
	 * 
	 * @param id     L'id dello spettacolo.
	 * @param sala   Sala in cui il film è proiettato.
	 * @param film   Film proiettato durante lo spettacolo.
	 * @param data   Data dello spettacolo. (anno,mese,giorno,ora).
	 * @param prezzo Double per il prezzo dello spettacolo
	 */
	public Spettacolo(int id, Sala sala, Film film, Calendar data, double prezzo) {
		this.id = id;
		this.sala = sala;
		this.film = film;
		this.data = data;
		this.prezzo = prezzo;
	}

	/**
	 * Metodo di accesso all'id dello Spettacolo
	 * 
	 * @return Restituisce l'id dello Spettacolo.
	 */
	public int getID() {
		return id;
	}

	/**
	 * Metodo di accesso al prezzo base dello spettacolo.
	 * 
	 * @return Restituisce il prezzo base dello spettacolo.
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Metodo di accesso alla sala di uno spettacolo.
	 * 
	 * @return Restituisce un clone della sala in cui si tiene.
	 */
	public Sala getSala() {
		return sala.clone();
	}

	/**
	 * Metodo di accesso al film di uno spettacolo.
	 * 
	 * @return Restituisce il film dello spettacolo.
	 */
	public Film getFilm() {
		return film;
	}

	/**
	 * Metodo di accesso alla data di iio niz uno spettacolo.
	 * 
	 * @return Restituisce la data di uno spettacolo.
	 */
	public Calendar getDataInizio() {
		return (Calendar) data.clone();
	}

	/**
	 * Metodo modificatore del prezzo base di uno spettacolo.
	 * 
	 * @param nuovoPrezzo Nuovo prezzo da assegnare allo spettacolo.
	 */
	public void setPrezzo(double nuovoPrezzo) {
		prezzo = nuovoPrezzo;
	}

	/**
	 * Metodo modificatore per cambiare sala ad uno spettacolo.
	 * 
	 * @param sala La nuova sala dello spettacolo.
	 */
	public void setSala(Sala nuovaSala) {
		this.sala = nuovaSala;
	}

	/**
	 * Metodo modificatore per cambiare data ad uno spettacolo.
	 * 
	 * @param data La nuova data dello spettacolo.
	 */
	public void setData(Calendar nuovaData) {
		this.data = nuovaData;
	}

	/**
	 * Metodo di accesso alla data di fine spettacolo (quindi la data inizio
	 * addizionata dei minuti del film).
	 * 
	 * @return Restituisce la data di fine spettacolo.
	 */
	public Calendar getDataFine() {
		Calendar datafine = (Calendar) data.clone();
		datafine.set(Calendar.MINUTE, datafine.get(Calendar.MINUTE) + film.getMinuti());
		return (Calendar) datafine.clone();
	}

	/**
	 * Metodo modificatore per l'occupazione di un posto riguardante lo spettacolo.
	 * Occupa un posto della sala relativo allo spettacolo.
	 * 
	 * @param char del posto
	 * @return Restituisce il posto appena occupato se esiste, altrimenti
	 *         restituisce null;
	 */
	public Posto occupa(char lettera, int numero) {
		Posto p = sala.getPosto(lettera, numero);
		if (p == null)
			return null;
		else {
			p.setOccupato();
			return p;
		}
	}

	/**
	 * Metodo modificatore per la liberazione di un posto riguardante lo spettacolo.
	 * Libera un posto della sala relativo allo spettacolo.
	 * 
	 * @param posto Il posto da liberare.
	 * @return Restituisce il posto appena liberato se esiste, altrimenti
	 *         restituisce null;
	 */
	public Posto libera(char lettera, int numero) {
		Posto p = sala.getPosto(lettera, numero);
		if (p == null)
			return null;
		else {
			p.setLibero();
			return p;
		}
	}

	/**
	 * Metodo di accesso alle informazioni principali di uno Spettacolo.
	 * 
	 * @return Restituisce una stringa contente le informazioni sull'id
	 *         Spettacolo,il numero Sala,il titolo del Film, La data di inizio e
	 *         fine e il prezzo.
	 */
	public String displayContent() {
		String am_pm, am_pm2;
		if (data.get(Calendar.AM_PM) == Calendar.AM)
			am_pm = "A.M.";
		else
			am_pm = "P.M.";
		if (getDataFine().get(Calendar.AM_PM) == Calendar.AM)
			am_pm2 = "A.M.";
		else
			am_pm2 = "P.M.";
		return "Id : " + id + " Sala : " + sala.getNumSala() + " Film : " + film.getTitolo() + " Data : "
				+ data.get(Calendar.DAY_OF_MONTH) + "/" + (data.get(Calendar.MONTH) + 1) + "/" + data.get(Calendar.YEAR)
				+ " Inizio : " + data.get(Calendar.HOUR) + ":" + data.get(Calendar.MINUTE) + " " + am_pm + " Fine : "
				+ getDataFine().get(Calendar.HOUR) + ":" + getDataFine().get(Calendar.MINUTE) + " " + am_pm2
				+ "  Prezzo : " + prezzo;
	}

	/**
	 * Metodo di accesso ai dati fondamentali dello Spettacolo.
	 * 
	 * @return Restituisce una stringa con i dati fondamentali dello Spettacolo.
	 */
	public String toString() {
		return getClass().getName() + "[Id = " + id + " Sala = " + sala + " Film = " + film + " Data = " + data
				+ " Prezzo = " + prezzo + "]";
	}

	/**
	 * Metodo di clonazione per Spettacolo.
	 * 
	 * @return Restituisce un puntatore ad una copia esatta dello Spettacolo
	 *         chiamante.
	 */
	public Spettacolo clone() {
		try {
			Spettacolo clone = (Spettacolo) super.clone();
			clone.film = film.clone();
			clone.sala = sala.clone();
			clone.data = (Calendar) data.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo di controllo dell'uguaglianza di uno Oggetto con lo Spettacolo
	 * chiamante.
	 * 
	 * @param obj Oggetto di cui controllare l'uguaglianza con lo Spettacolo
	 *            chiamante.
	 * @return Restituisce true se il chiamante e obj hanno lo stesso stato, false
	 *         altrimenti.
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != getClass())
			return false;
		Spettacolo other = (Spettacolo) obj;
		return other.id == id && other.data.equals(data) && other.film.equals(film) && other.sala.equals(sala)
				&& other.prezzo == prezzo;
	}
}
