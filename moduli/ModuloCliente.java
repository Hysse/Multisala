package moduli;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

import classi.Multisala;
import classi.Sala;
import classi.Spettacolo;
import utilities.Sort;

/**
 * Classe che permette a un utente di compiere varie operazioni su un multisala, tra cui:
 * 1. visualizzare il programma settimanale complessivo o per sala;
 * 2. visualizzare la lista degli spettacoli non ancora iniziati (e quindi ancora fruibili) in ordine
 * cronologico, per numero di sala crescente o per titolo in ordine alfabetico;
 * 3. visualizzare le informazioni su ciascuno degli spettacoli;
 * 4. selezionare uno spettacolo fruibile e prenotare un posto nella sala in cui viene proiettato;
 * 5. cancellare una prenotazione;
 * 6. acquistare un biglietto per una prenotazione effettuata o direttamente.
 */
public class ModuloCliente {
	
	private Multisala multisala;
	
	/**
	 * Costruttore moduloCliente che salva un Multisala da cui prendere le informazioni
	 * @param multisala Multisala da cui prendere le informazioni
	 */
	public ModuloCliente(Multisala multisala)
	{
		this.multisala = multisala;
	}
	
	public ArrayList<Spettacolo> getSpettacoliSettimana()
	{
		ModuloSpettacolo modSpettacolo = new ModuloSpettacolo(multisala);
		return modSpettacolo.getSpettacoliSettimana();
	}
	
	public ArrayList<Spettacolo> getSpettacoliSale(Sala sala)
	{
		ModuloSpettacolo modSpettacolo = new ModuloSpettacolo(multisala);
		return modSpettacolo.getSpettacoliPerSala(sala);
	}
	
	public void SortSpettacoliCronologico(ArrayList<Spettacolo> array)
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
		
		Sort<Spettacolo> sorter = new Sort<Spettacolo>(new CronologicComparator(),array);
		sorter.insertionSort();
	}
	
	public void SortSpettacoliSalaCrescente(ArrayList<Spettacolo> array)
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
		
		Sort<Spettacolo> sorter = new Sort<Spettacolo>(new SalaComparator(),array);
		sorter.insertionSort();
	}
	
	
	public void SortSpettacoliTitolo(ArrayList<Spettacolo> array)
	{
		class TitoloComparator implements Comparator<Spettacolo>
		{
			public int compare(Spettacolo s1, Spettacolo s2) {
				return s1.getFilm().getTitolo().compareTo(s2.getFilm().getTitolo());
				}
		}
		
		Sort<Spettacolo> sorter = new Sort<Spettacolo>(new TitoloComparator(),array);
		sorter.insertionSort();
	}
	
	

}
