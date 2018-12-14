package moduli;

import classi.Cliente;
import classi.Multisala;
import classi.Posto;
import classi.Spettacolo;
import eccezioni.BigliettoPresenteException;
import eccezioni.OraPrenotazioneException;
import eccezioni.PostoIndisponibileException;
import eccezioni.PostoNonEsistenteException;
import eccezioni.PostoOccupatoException;

import java.util.ArrayList;
import java.util.Calendar;
import classi.Biglietto;

/**
 * Classe che gestisce le prenotazioni di uno spettacolo
 */
public class ModuloBiglietto {
	
	private Multisala multisala;
	private Cliente cliente;
	
	
	public ModuloBiglietto(Multisala multisala, Cliente cliente)
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
	 * @return Biglietto della prenotazione, altrimenti null se il biglietto è già stato prenotato.
	 * @throws PostoIndisponibileException eccezione lanciata nel caso in cui nella sala clonata
	 * dello spettacolo il posto sia indisponibile
	 * @throws OraPrenotazioneException eccezione lanciata nel caso in cui l'ora in cui il cliente
	 * cerca di prenotare un biglietto rientra nelle dodici ore prima dello spettacolo
	 * @throws BigliettoPresenteException 
	 */
	public void addPrenotazione(Spettacolo spettacolo, char lettera, int numero) throws PostoIndisponibileException, OraPrenotazioneException,PostoNonEsistenteException, BigliettoPresenteException
	{
		if (!isBeforeDodici(spettacolo))
			throw new OraPrenotazioneException();
		if(spettacolo.getSala().getPosto(lettera, numero) == null)
			throw new PostoNonEsistenteException();
		ModuloSala moduloSala = new ModuloSala(multisala);
		if (alreadyPresente(spettacolo))
			throw new BigliettoPresenteException();
		
		Biglietto b = new Biglietto(spettacolo, lettera, numero, false);
		Posto postoSala = moduloSala.getSala(spettacolo.getSala().getNumSala()).getPosto(lettera, numero);
		if (!postoSala.isDisponibile())
			throw new PostoIndisponibileException();
		spettacolo.occupa(lettera, numero);
		cliente.addPrenotazione(b);
	}
	/**
	 * Metodo di ricerca di un Biglietto per idSpettacolo (Ogni cliente può avere solo un biglietto per spettacolo).
	 * @param idSpettacolo ID dello spettacolo di cui si vuole avere il biglietto,se presente.
	 * @return Restituisce il Biglietto relativo allo SPettacolo con ID = idSpettacolo, null se non ne esistono.
	 */
	public Biglietto getBiglietto(int idSpettacolo)
	{
		for(Biglietto b : cliente.getListaBiglietti())
		{
			if(b.getSpettacolo().getID() == idSpettacolo)
				return b;
		}
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
	 * @throws BigliettoPresenteException 
	 */
	public void acquistoDiretto(Spettacolo spettacolo, char lettera, int numero) throws PostoIndisponibileException, OraPrenotazioneException,PostoOccupatoException,PostoNonEsistenteException, BigliettoPresenteException
	{
		if (!isBeforeDodici(spettacolo))
			throw new OraPrenotazioneException();
		if(spettacolo.getSala().getPosto(lettera, numero) == null)
			throw new PostoNonEsistenteException();
		moduli.ModuloSala modSala = new ModuloSala(multisala);
		ModuloSconto modSco = new ModuloSconto(multisala);
		if (!alreadyPresente(spettacolo))
		{	
			Biglietto b = new Biglietto(spettacolo, lettera, numero, false);
			Posto postoSala = modSala.getSala(spettacolo.getSala().getNumSala()).getPosto(lettera, numero);
			if (!postoSala.isDisponibile())
				throw new PostoIndisponibileException();
			Posto postoSpettacolo = spettacolo.getSala().getPosto(lettera, numero);
			if(!postoSpettacolo.isLibero())
				throw new PostoOccupatoException();
			spettacolo.occupa(lettera,numero);
			modSco.applicaSconto(b);
			multisala.addAmount(b.getPrezzo());
			b.getSpettacolo().getFilm().addIncasso(b.getPrezzo());
			b.setAcquistato();
			cliente.addPrenotazione(b);
		}
		else
			throw new BigliettoPresenteException();
	}
	
	/**
	 * Metodo per confermare l'acquisto di una prenotazione
	 * @param biglietto Biglietto con prenotazione
	 * @return Biglietto con acquisto effettuato, altrimenti null se il biglietto non è una prenotazione
	 * @throws PostoIndisponibileException eccezione lanciata nel caso in cui un posto non è disponibile
	 * @throws OraPrenotazioneException eccezione lanciata nel caso in cui l'ora in cui il cliente
	 * cerca di acquistare il biglietto rientra nelle dodici ore prima dello spettacolo
	 */
	public Biglietto acquistoConPrenotazione(Biglietto biglietto) throws PostoIndisponibileException, OraPrenotazioneException
	{
		if (!biglietto.isPrenotazione())
			return null;
		if (!isBeforeDodici(biglietto.getSpettacolo()))
			throw new OraPrenotazioneException();
		moduli.ModuloSala modSala = new ModuloSala(multisala);
		ModuloSconto modSco = new ModuloSconto(multisala);
		Posto p = modSala.getSala(biglietto.getSpettacolo().getSala().getNumSala()).getPosto(biglietto.getLetteraPosto(), biglietto.getNumeroPosto());
		if (p.isDisponibile())
		{
			modSco.applicaSconto(biglietto);
			multisala.addAmount(biglietto.getPrezzo());
			biglietto.getSpettacolo().getFilm().addIncasso(biglietto.getPrezzo());
			biglietto.setAcquistato();
			cliente.addPrenotazione(biglietto);
			return biglietto;
		}
		else
			throw new PostoIndisponibileException();
	}
	/**
	 * Metodo che controlla se il cliente ha già effettuato una prenotazione per uno spettacolo s.
	 * @param s Spettacolo di cui si vuole cercare la prenotazione.
	 * @returnRestituisce true se il Cliente ha una prenotazione per lo Spettacolo s,false altrimenti.
	 */
	public boolean hasPrenotazione(Spettacolo s)
	{
		for (Biglietto b: cliente.getListaBiglietti())
		{
			if (b.getSpettacolo().getID() == s.getID() && b.isPrenotazione())
				return true;
		}
		return false;
	}
	/**
	 * Meotodo che controlla se il cliente ha già effettuato una prenotazione/acquisto
	 * per un determinato spettacolo
	 * @param s Spettacolo da cui prendere l'id
	 * @return true se il cliente già ha prenotato/acquistato un biglietto per uno spettacolo, false altrimenti
	 */
	public boolean alreadyPresente(Spettacolo s)
	{
		for (Biglietto b: cliente.getListaBiglietti())
		{
			if (b.getSpettacolo().getID() == s.getID())
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
		ArrayList<Biglietto> prenotazioni = cliente.getListaBiglietti();
		for (Biglietto b: prenotazioni)
		{
			if (b.equals(biglietto))
			{
				b.getSpettacolo().libera(b.getLetteraPosto(), b.getNumeroPosto());
				return cliente.removePrenotazione(b);
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
