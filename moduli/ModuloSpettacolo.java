package moduli;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

import classi.Film;
import classi.Multisala;
import classi.Sala;
import classi.Spettacolo;
import eccezioni.FilmNonPresenteException;
import eccezioni.OraSpettacoloException;
import utilities.Sort;

/**
 * Classe per gestire gli Spettacoli di un multisala
 */
public class ModuloSpettacolo {
	
	private Multisala multisala;
	
	/**
	 * Costruttore del moduloSpettacolo per gestire gli spettacoli del multisala
	 * @param multisala Multisala da dove prendere la lista degli Spettacoli da gestire
	 */
	public ModuloSpettacolo(classi.Multisala multisala)
	{
		this.multisala = multisala;
	}
	
	/**
	 * Metodo che aggiunge uno spettacolo alla lista degli spettacoli del multisala.
	 * Vengono passati nei parametri gli attributi per instanziare un nuovo spettacolo.
	 * Poichè ogni spettacolo ha uno stato della sala diverso in base alle prenotazioni
	 * viene assegnato al nuovo spettacolo una copia della sala in cui si vuole aggiungere lo
	 * spettacolo
	 * @param film Film dello spettacolo
	 * @param numeroSala int con numero della sala
	 * @param prezzo Double con prezzo dello spettacolo
	 * @param data Calendar con data e orario dello spettacolo
	 * @throws OraSpettacoloException eccezione lanciata nel caso in cui si tenta di aggiungere
	 * lo spettacolo in un orario in cui è presente uno spettacolo ancora in corso nella sala
	 * in considerazione
	 */
	public void addSpettacolo(int id,Film film, int numeroSala, Double prezzo, Calendar data) throws OraSpettacoloException
	{
		ModuloSala modSala = new ModuloSala(multisala);
		Spettacolo spettacolo = new Spettacolo(id,modSala.getSala(numeroSala).clone(),film, data, prezzo);
		for (Spettacolo s: multisala.getListaSpettacoli())
		{
			if((s.getDataInizio().before(spettacolo.getDataInizio())
					&& s.getDataFine().after(spettacolo.getDataInizio()) &&
					s.getSala().getNumSala() == spettacolo.getSala().getNumSala()) || s.getDataInizio().equals(spettacolo.getDataInizio()))
				throw new OraSpettacoloException();
		}
		multisala.getListaSpettacoli().add(spettacolo);
	}
	
	/**
	 * Metodo per rimuovere uno spettacolo dalla lista spettacoli
	 * @param id int con id dello spettacolo da rimuovere
	 * @return true se lo spettacolo è stato rimosso, false altrimenti
	 */
	public boolean removeSpettacolo(int id)
	{
		Spettacolo s;
		if ((s = getSpettacolo(id)) != null)
			return multisala.getListaSpettacoli().remove(s);
		else
			return false;
	}
	
	/**
	 * Metodo che ritorna uno spettacolo cercato con l'id rispettivo
	 * @param id int con id dello spettacolo
	 * @return Spettacolo con id cercato se presente nella lista, null se non è presente
	 */
	public Spettacolo getSpettacolo(int id)
	{
		for (Spettacolo s: multisala.getListaSpettacoli())
		{
			if (s.getID() == id)
				return s;
		}
		
		return null;
	}
	
	/**
	 * Metodo per aggiungere un film alla lista dei film del multisala
	 * @param f Film da aggiungere
	 */
	public void addFilm(Film f)
	{
		multisala.getListaFilm().add(f);
	}
	
	/**
	 * Metodo che rimuove un film dalla lista dei film di un multisala
	 * @param idFilm int con id del film da eliminare
	 * @return true se il film è stato eliminato, false altrimenti
	 */
	public boolean removeFilm(int idFilm)
	{
		Film f;
		if ((f = getFilm(idFilm)) != null)
				return multisala.getListaFilm().remove(f);
		else
			return false;
	}
	
	/**
	 * Metodo che ritorna un film cercato per id se presente nella lista dei film
	 * @param filmId int con id del film
	 * @return Film cercato per id se presente, altrimenti viene lanciata un'eccezione
	 * @throws FilmNonPresenteException eccezione lanciata nel caso in cui un film cercato
	 * non è presente nella lista dei film del multisala
	 */
	public Film getFilm(int filmId) throws FilmNonPresenteException
	{
		for (Film f: multisala.getListaFilm())
		{
			if (f.getIdFilm() == filmId)
				return f;
		}
		
		throw new FilmNonPresenteException();
	}
	
	/**
	 * Metodo per creare una sotto-collezione di spettacoli che iniziano tra la data attuale del sistema e il fine settimana.
	 * @return Restituisce un ArrayList<Spettacolo> contenente tutti gli Spettacoli che iniziano tra la data corrente del sistema e il fine settimana.
	 */
	public ArrayList<Spettacolo> getSpettacoliFruibili() {
		ArrayList<Spettacolo> spettacoliFruibili = new ArrayList<Spettacolo>();
		Calendar dataUtente = Calendar.getInstance();
		for (Spettacolo s : multisala.getListaSpettacoli()) {
			if (s.getDataInizio().after(dataUtente))
				spettacoliFruibili.add(s);
		}
		return spettacoliFruibili;
	}
	
	/**
	 * Metodo per creare una sotto-collezione di spettacoli che iniziano tra la data attuale del sistema e il fine settimana.
	 * @return Restituisce un ArrayList<Spettacolo> contenente tutti gli Spettacoli che iniziano tra la data corrente del sistema e il fine settimana.
	 */
	public ArrayList<Spettacolo> getSpettacoliSettimana() {
		ArrayList<Spettacolo> spettacoliSettimana = new ArrayList<Spettacolo>();
		Calendar dataUtente = Calendar.getInstance();
		Calendar fineSettimana = (Calendar) dataUtente.clone();
		// FACCIO DIVENTARE fineSettimana LA DATA DI FINE SETTIMANA DELLA SETTIMANA
		while (fineSettimana.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			fineSettimana.set(Calendar.DAY_OF_MONTH, fineSettimana.get(Calendar.DAY_OF_MONTH) + 1);
		for (Spettacolo s : multisala.getListaSpettacoli()) {
			// controlla se ((dataSpettacolo >= dataUtente) && (dataSpettacolo <=
			// fineSettimana))
			if ((s.getDataInizio().equals(dataUtente) || s.getDataInizio().after(dataUtente))
					&& (s.getDataInizio().before(fineSettimana) || s.getDataInizio().equals(fineSettimana)))
				spettacoliSettimana.add(s);
		}
		return spettacoliSettimana;
	}
	
	/**
	 * Metodo per creare una sotto-collezione di spettacoli tenuti nella Sala passata.
	 * @param sala La sala di cui si vuole avere la lista Spettacoli.
	 * @return Restituisce un ArrayList<Spettacolo> contenente tutti gli Spettacoli che si terranno in quella sala.
	 */
	public ArrayList<Spettacolo> getSpettacoliPerSala(Sala sala) {
		ArrayList<Spettacolo> spettacoliSala = new ArrayList<Spettacolo>();
		for(Spettacolo s : multisala.getListaSpettacoli()) {
			if(s.getSala().getNumSala() == sala.getNumSala())
				spettacoliSala.add(s);
		}
		return spettacoliSala;
	}
	/**
	 * Metodo per eliminare tutti gli Spettacoli del giorno.
	 */
	public void RemoveDailyProgram()
	{
		Calendar attuale = Calendar.getInstance();
		ArrayList<Spettacolo>listaSpettacoli = multisala.getListaSpettacoli();
		for(Spettacolo s : listaSpettacoli)
		{
			if(s.getDataInizio().get(Calendar.YEAR) == attuale.get(Calendar.YEAR) && s.getDataInizio().get(Calendar.MONTH) == attuale.get(Calendar.MONTH) && s.getDataInizio().get(Calendar.DAY_OF_MONTH) == attuale.get(Calendar.DAY_OF_MONTH))
				listaSpettacoli.remove(s);
		}
	}
	
	public ArrayList<Spettacolo> SortSpettacoliCronologico()
	{
		class CronologicComparator implements Comparator<Spettacolo>
		{
			public int compare(Spettacolo s1, Spettacolo s2) {
				if(s1.getDataInizio().before(s2.getDataInizio()))
					return -1;
				else
				{
					if(s1.getDataInizio().after(s2.getDataInizio()))
						return 1;
					else
						return 0;
				}
				}
		}
		ArrayList<Spettacolo> copia = new ArrayList<Spettacolo>();
		for(Spettacolo s : multisala.getListaSpettacoli())
			copia.add(s.clone());
		Sort<Spettacolo> sorter = new Sort<Spettacolo>(new CronologicComparator(),copia);
		sorter.insertionSort();
		return copia;
	}
	
	public ArrayList<Spettacolo> SortSpettacoliSalaCrescente()
	{
		class SalaComparator implements Comparator<Spettacolo>
		{
			public int compare(Spettacolo s1, Spettacolo s2) {
				if(s1.getSala().getNumSala() < s2.getSala().getNumSala())
					return -1;
				else
				{
					if(s1.getSala().getNumSala() > s2.getSala().getNumSala())
						return 1;
					else
						return 0;
				}
				}
		}
		
		ArrayList<Spettacolo> copia = new ArrayList<Spettacolo>();
		for(Spettacolo s : multisala.getListaSpettacoli())
			copia.add(s.clone());
		Sort<Spettacolo> sorter = new Sort<Spettacolo>(new SalaComparator(),copia);
		sorter.insertionSort();
		return copia;
	}
	
	
	public ArrayList<Spettacolo> SortSpettacoliTitolo()
	{
		class TitoloComparator implements Comparator<Spettacolo>
		{
			public int compare(Spettacolo s1, Spettacolo s2) {
				return s1.getFilm().getTitolo().compareTo(s2.getFilm().getTitolo());
				}
		}
		
		ArrayList<Spettacolo> copia = new ArrayList<Spettacolo>();
		for(Spettacolo s : multisala.getListaSpettacoli())
			copia.add(s.clone());
		Sort<Spettacolo> sorter = new Sort<Spettacolo>(new TitoloComparator(),copia);
		sorter.insertionSort();
		return copia;
	}
	
	public ArrayList<Spettacolo> SortSpettacoloNumPosti()
	{
		class postoLiberoComparator implements Comparator<Spettacolo>
		{

			@Override
			public int compare(Spettacolo s1, Spettacolo s2) {
				return s1.getSala().getNumPostiLiberi() - s2.getSala().getNumPostiLiberi();
			}
		}
		
		ArrayList<Spettacolo> copia = new ArrayList<Spettacolo>();
		for(Spettacolo s : multisala.getListaSpettacoli())
			copia.add(s.clone());
		Sort<Spettacolo> sort = new Sort<Spettacolo>(new postoLiberoComparator(),copia);
		sort.insertionSort();
		return copia;
	}
}
