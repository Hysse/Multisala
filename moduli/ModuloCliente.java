package moduli;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

import classi.Biglietto;
import classi.Cliente;
import classi.Multisala;
import classi.Sala;
import classi.Spettacolo;
import eccezioni.BigliettoPresenteException;
import eccezioni.OraPrenotazioneException;
import eccezioni.PostoIndisponibileException;
import eccezioni.PostoNonEsistenteException;
import eccezioni.PostoOccupatoException;
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
	private Cliente cliente;
	
	/**
	 * Costruttore moduloCliente che salva un Multisala da cui prendere le informazioni
	 * @param multisala Multisala da cui prendere le informazioni
	 */
	public ModuloCliente(Multisala multisala,Cliente cliente)
	{
		this.cliente = cliente;
		this.multisala = multisala;
	}
	
	public ArrayList<Spettacolo> getSpettacoliSettimana()
	{
		ModuloSpettacolo modSpettacolo = new ModuloSpettacolo(multisala);
		return modSpettacolo.getSpettacoliSettimana();
	}
	
	public ArrayList<Spettacolo> getSpettacoliSala(Sala sala)
	{
		ModuloSpettacolo modSpettacolo = new ModuloSpettacolo(multisala);
		return modSpettacolo.getSpettacoliPerSala(sala);
	}
	
	public ArrayList<Spettacolo> getFruibiliCronologico()
	{
		ModuloSpettacolo modSpettacolo = new ModuloSpettacolo(multisala);
		return modSpettacolo.SortSpettacoliCronologico(modSpettacolo.getSpettacoliFruibili());
	}
	
	public ArrayList<Spettacolo> getFruibiliSalaCresc()
	{
		ModuloSpettacolo modSpettacolo = new ModuloSpettacolo(multisala);
		return modSpettacolo.SortSpettacoliSalaCrescente(modSpettacolo.getSpettacoliFruibili());
	}
	
	public ArrayList<Spettacolo> getFruibiliTitolo()
	{
		ModuloSpettacolo modSpettacolo = new ModuloSpettacolo(multisala);
		return modSpettacolo.SortSpettacoliTitolo(modSpettacolo.getSpettacoliFruibili());
	}
	
	public Spettacolo getInfoSpettacolo(int id)
	{
		ModuloSpettacolo modSpettacolo = new ModuloSpettacolo(multisala);
		return modSpettacolo.getSpettacolo(id);
	}
	
	/**
	 * Metodo che prenota un biglietto per uno Spettacolo.
	 * @param spettacolo L'id dello Spettacolo per cui prenotare il Bilietto.
	 * @param lettera La lettera del Posto da prenotare
	 * @param numero Il numero del Posto da prenotare.
	 * @throws OraPrenotazioneException Eccezione lanciata se l'ora di inizo dello spettacolo da prenotare è a meno di 12 ore dall'ora dell'utente.
	 * @throws PostoIndisponibileException Eccezione lanciata se il Posto che si vuole prenotare è indisponibile.
	 * @throws PostoNonEsistenteException Eccezione lanciata se il Posto non esiste all'interno della Sala in cui si tiene lo SPettacolo.
	 * @throws BigliettoPresenteException Eccezione lanciata se si ha già un biglietto per lo Spettacolo.
	 */
	public void prenotaBiglietto(int idSpettacolo,char lettera,int numero) throws OraPrenotazioneException,PostoIndisponibileException,PostoNonEsistenteException, BigliettoPresenteException
	{
		ModuloBiglietto modPre = new ModuloBiglietto(multisala, cliente);
		ModuloSpettacolo modSpe = new ModuloSpettacolo(multisala);
		modPre.addPrenotazione(modSpe.getSpettacolo(idSpettacolo), lettera, numero);
	}
	/**
	 * Metodo per eliminare un biglietto relativo ad una prenotazione effettuata.
	 * @param idSpettacolo L'ID dello spettacolo di cui si ha una prenotazione.
	 * @return Restituisce true se l'eliminzaione è andata a buon fine, false se non si ha una prenotazione di quello spettacolo.
	 */
	public boolean cancellaPrenotazione(int idSpettacolo)
	{
		ModuloBiglietto modPre = new ModuloBiglietto(multisala, cliente);
		Biglietto b = modPre.getBiglietto(idSpettacolo);
		return modPre.removePrenotazione(b);
	}
	/**
	 * Metodo che acquista direttamente un biglietto per uno Spettacolo.
	 * @param spettacolo L'ID dello SPettacolo per cui acquistare il Bilietto.
	 * @param lettera La lettera del Posto da acquistare
	 * @param numero Il numero del Posto da acquistare.
	 * @return Restituisce true se l'acquisto è andata a buon fine, false se è gia presente una prenotazione o acquisto del Cliente per lo stesso Spettacolo.
	 * @throws OraPrenotazioneException Eccezione lanciata se l'ora di inizo dello spettacolo da prenotare è a meno di 12 ore dall'ora dell'utente.
	 * @throws PostoIndisponibileException Eccezione lanciata se il Posto che si vuole acquistare è indisponibile.
	 * @throws PostoNonEsistenteException Eccezione lanciata se il Posto non esiste all'interno della Sala in cui si tiene lo Spettacolo.
	 * @throws BigliettoPresenteException Eccezione lanciata se si ha già un biglietto per lo Spettacolo.
	 */
	public void acquistoDiretto(int idSpettacolo,char lettera,int numero) throws PostoIndisponibileException, OraPrenotazioneException, PostoOccupatoException, PostoNonEsistenteException, BigliettoPresenteException
	{
		ModuloBiglietto modBig = new ModuloBiglietto(multisala, cliente);
		ModuloSpettacolo modSpe = new ModuloSpettacolo(multisala);
		modBig.acquistoDiretto(modSpe.getSpettacolo(idSpettacolo), lettera, numero);
	}
	/**
	 * Metodo per acquistare un biglietto relativo ad una prenotazione effettuata.
	 * @param idSpettacolo L'ID dello spettacolo di cui si ha una prenotazione.
	 * @return Restituisce true se l'acquisto è andato a buon fine, false se non si ha una prenotazione di quello spettacolo.
	 * @throws PostoIndisponibileException Eccezione lanciata se il Posto che si vuole acquistare è indisponibile.
	 * @throws OraPrenotazioneException Eccezione lanciata se il Posto non esiste all'interno della Sala in cui si tiene lo Spettacolo.
	 */
	public boolean acquistoPrenotazione(int idSpettacolo) throws PostoIndisponibileException, OraPrenotazioneException
	{
		ModuloBiglietto modBig = new ModuloBiglietto(multisala, cliente);
		Biglietto b = modBig.getBiglietto(idSpettacolo);
		if(modBig.acquistoConPrenotazione(b) == null)
			return false;
		else 
			return true;
	}

}
