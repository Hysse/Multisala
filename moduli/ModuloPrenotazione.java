package moduli;

import classi.Cliente;
import classi.Multisala;
import classi.Posto;
import classi.Spettacolo;
import eccezioni.OraPrenotazioneException;
import eccezioni.PostoIndisponibileException;

import java.util.ArrayList;
import java.util.Calendar;

import classi.Biglietto;

/**
 * Classe che gestisce le prenotazioni di uno spettacolo
 */
public class ModuloPrenotazione {
	
	private Multisala multisala;
	private Cliente cliente;
	
	
	public ModuloPrenotazione(Multisala multisala, Cliente cliente)
	{
		this.multisala = multisala;
		this.cliente = cliente;
	}
	
	/**
	 * Metodo che instanzia un biglietto e prenota un posto per uno spettacolo se l'utente
	 * non aveva già effettuato una prenotazione per quest'ultimo
	 * @param spettacolo Spettacolo in cui deve essere prenotato il posto
	 * @param lettera Char con lettera del posto
	 * @param numero int con numero del post
	 * @return Biglietto della prenotazione, altrimenti null se il biglietto è già stato prenotato
	 * oppure se il posto da occupare non esiste oppure è occupato
	 * @throws PostoIndisponibileException eccezione lanciata nel caso in cui nella sala clonata
	 * dello spettacolo il posto sia indisponibile
	 * @throws OraPrenotazioneException eccezione lanciata nel caso in cui l'ora in cui il cliente
	 * cerca di prenotare un biglietto rientra nelle dodici ore prima dello spettacolo
	 */
	public Biglietto addPrenotazione(Spettacolo spettacolo, char lettera, int numero) throws PostoIndisponibileException, OraPrenotazioneException
	{
		if (!isBeforeDodici(spettacolo))
			throw new OraPrenotazioneException();
			
		moduli.ModuloSala m = new ModuloSala(multisala);
		if (alreadyPrenotato(spettacolo))
			return null;
		
		Biglietto b = new Biglietto(spettacolo, lettera, numero, false);
		Posto p = m.getSala(spettacolo.getId()).getPosto(lettera, numero);
		if (spettacolo.occupa(lettera, numero) != null && p.isLibero())
		{
			if (!p.isDisponibile())
				throw new PostoIndisponibileException();
			cliente.addPrenotazione(b);
			return b;
		}
		else
			return null;
	}
	
	/**
	 * Metodo per acquistare direttamente un biglietto
	 * @param spettacolo Spettacolo in cui acquistare un biglietto
	 * @param lettera Char con lettera del posto
	 * @param numero int con numero del posto
	 * @return Biglietto dell'acquisto, altrimenti null se il biglietto è già stato acquistato
	 * oppure se il posto da occupare non è lbero o non esiste
	 * @throws PostoIndisponibileException eccezione lanciata nel caso in cui nella sala clonata
	 * dello spettacolo il posto sia indisponibile
	 * @throws OraPrenotazioneException eccezione lanciata nel caso in cui l'ora in cui il cliente
	 * cerca di acquistare il biglietto rientra nelle dodici ore prima dello spettacolo
	 */
	public Biglietto acquistoDiretto(Spettacolo spettacolo, char lettera, int numero) throws PostoIndisponibileException, OraPrenotazioneException
	{
		if (!isBeforeDodici(spettacolo))
			throw new OraPrenotazioneException();
			
		moduli.ModuloSala m = new ModuloSala(multisala);
		
		if (!alreadyPrenotato(spettacolo))
		{	
			Biglietto b = new Biglietto(spettacolo, lettera, numero, false);
			Posto p = m.getSala(spettacolo.getId()).getPosto(lettera, numero);
			if (spettacolo.occupa(lettera, numero) != null && p.isLibero())
			{
				if (!p.isDisponibile())
					throw new PostoIndisponibileException();
				
				ModuloSconto.applicaSconto(b);
				multisala.addAmount(b.getPrezzo());
				b.getSpettacolo().getFilm().addIncasso(b.getPrezzo());
				b.setAcquistato();
				p.setOccupato();
				cliente.addPrenotazione(b);
				return b;
			}
			else
				return null;	
		}
		else
			return null;
	}
	
	/**
	 * Metodo per confermare l'acquisto di una prenotazione
	 * @param b Biglietto con prenotazione
	 * @return Biglietto con acquisto effettuato, altrimenti null se il biglietto non è una prenotazione
	 * @throws PostoIndisponibileException eccezione lanciata nel caso in cui un posto non è disponibile
	 * @throws OraPrenotazioneException eccezione lanciata nel caso in cui l'ora in cui il cliente
	 * cerca di acquistare il biglietto rientra nelle dodici ore prima dello spettacolo
	 */
	public Biglietto acquistoConPrenotazione(Biglietto b) throws PostoIndisponibileException, OraPrenotazioneException
	{
		if (!b.isPrenotazione())
			return null;
		if (!isBeforeDodici(b.getSpettacolo()))
			throw new OraPrenotazioneException();
		moduli.ModuloSala m = new ModuloSala(multisala);
		Posto p = m.getSala(b.getSpettacolo().getId()).getPosto(b.getLetteraPosto(), b.getLetteraPosto());
		if (p.isDisponibile())
		{
			ModuloSconto.applicaSconto(b);
			multisala.addAmount(b.getPrezzo());
			b.getSpettacolo().getFilm().addIncasso(b.getPrezzo());
			b.setAcquistato();
			p.setOccupato();
			cliente.addPrenotazione(b);
			return b;
		}
		else
			throw new PostoIndisponibileException();
	}

	
	/**
	 * Meotodo che controlla se il cliente ha già effettuato una prenotazione 
	 * per un determinato spettacolo
	 * @param s Spettacolo da cui prendere l'id
	 * @return true se il cliente già ha prenotato un biglietto per uno spettacolo, false altrimenti
	 */
	public boolean alreadyPrenotato(Spettacolo s)
	{
		for (Biglietto b: cliente.getListaPrenotazioni())
		{
			if (b.getSpettacolo().getId() == s.getId() && b.isPrenotazione())
				return true;
		}
		
		return false;
	}
	
	/**
	 * Metodo per rimuovere una prenotazione dalla lista delle prenotazioni di un cliente
	 * @param biglietto Biglietto da rimuovere
	 * @return true se il biglietto è stato trovato e rimosso, false altrimenti
	 */
	public boolean removePrenotazione(Biglietto biglietto)
	{
		ArrayList<Biglietto> prenotazioni = cliente.getListaPrenotazioni();
		for (Biglietto b: prenotazioni)
		{
			if (b.equals(biglietto))
			{
				Posto p = new Posto(b.getLetteraPosto(), b.getNumeroPosto());
				p.setLibero();
				return prenotazioni.remove(b);
			}
		}
		
		return false;
	}
	
	/**
	 * Metodo interno del modulo prenotazione che permette di controllare se un cliente
	 * che tenta di fare una prenotazione, acquisto diretto o acquisto Prenotazione
	 * lo fa dodici ore prima dello spettacolo
	 * @param spettacolo Spettacolo da cui prendere l'ora d'inizio
	 * @return true se l'operazione viene effettuata dodici ore prima dell'inizio dello spettacolo,
	 * false altrimenti
	 */
	private boolean isBeforeDodici(Spettacolo spettacolo)
	{
		Calendar dodiciOrePrima = spettacolo.getDataInizio();
		dodiciOrePrima.set(Calendar.HOUR, dodiciOrePrima.get(Calendar.HOUR) - 12);
		Calendar dataCliente = Calendar.getInstance();
		if (dataCliente.after(dodiciOrePrima))
			return false;
		else
			return true;
	}
}
