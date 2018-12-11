package moduli;

import java.time.LocalDate;
import classi.Multisala;
import classi.Sala;
import classi.Spettacolo;

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
	
	/**
	 * Metodo che visualizza il programma complessivo della settiamana a partire dal giorno in cui
	 * l'utente loggato chiede di visualizzare, fino alla domenica
	 * @return stringa con programma complessivo della settimana
	 */
	public String visualizzaProgrammaSettimanale()
	{
		String str = "";
		
		LocalDate dataUtente = LocalDate.now();
		int i = dataUtente.getDayOfWeek().getValue();
		while(i <= 7)
		{
			for (Spettacolo s: multisala.getListaSpettacoli())
			{
				if (confontaDate(s.getDataInizio(), dataUtente))
					str = str + s.toString() + "\n";
			}
			
			i++;
		}
		return str;
	}
	
	/**
	 * Metodo che visualizza il programma complessivo della settiamana a partire dal giorno in cui
	 * l'utente loggato chiede di visualizzare, fino alla domenica per sala
	 * @param s Sala per cui si vuole stampare il programma settimanale
	 * @return String con programma settimanale per sala
	 */
	public String visualizzaProgrammaSettimanaleSala(Sala sala)
	{
		String str = "";
		
		LocalDate dataUtente = LocalDate.now();
		int i = dataUtente.getDayOfWeek().getValue();
		while(i <= 7)
		{
			for (Spettacolo s: multisala.getListaSpettacoli())
			{
				if (confontaDate(s.getDataInizio(), dataUtente) && s.getSala().equals(sala))
				{
					str = str + s.toString() + "\n";
				}
			}
			
			i++;
		}
		return str;
	}
	
	/**
	 * Metodo privato per confrontare una Date con una Localdate in base a: giorno, mese, anno
	 * @param d1 Date
	 * @param d2 LocalDate
	 * @return true se giorno mese e anno sono gli stessi, false altrimenti
	 */
	@SuppressWarnings("deprecation")
	private static boolean confontaDate(java.util.Date d1, LocalDate d2)
	{
		if (d1.getDay() == d2.getDayOfWeek().getValue() && d1.getMonth() == d2.getMonthValue()
				&& (d1.getYear() + 1900) == d2.getYear())
			return true;
		else
			return false;
	}
	

}
