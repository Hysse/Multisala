package moduli;

import classi.Cliente;
import classi.Multisala;
import classi.Spettacolo;
import classi.Biglietto;

/**
 * 
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
	 */
	public Biglietto addPrenotazione(Spettacolo spettacolo, char lettera, int numero)
	{
		if (alreadyPrenotato(spettacolo))
			return null;
		
		Biglietto b = new Biglietto(spettacolo, lettera, numero, false);
		cliente.addPrenotazione(b);
		return b;
	}
	
	/**
	 * Metodo per acquistare direttamente un biglietto
	 * @param spettacolo Spettacolo in cui acquistare un biglietto
	 * @param lettera Char con lettera del posto
	 * @param numero int con numero del posto
	 * @return Biglietto dell'acquisto, altrimenti null se il biglietto è già stato acquistato
	 */
	public Biglietto acquistoDiretto(Spettacolo spettacolo, char lettera, int numero)
	{
		if (!alreadyPrenotato(spettacolo))
			return null;
		
		Biglietto b = new Biglietto(spettacolo, lettera, numero, false);
		acquistaBiglietto(b);
		cliente.addPrenotazione(b);
		return b;
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
				return false;
		}
		
		return true;
	}
	
	/**
	 * Metodo che permette l'acquisto diretto di un biglietto o conferma una prenotazione effettuando
	 * l'acquisto. Viene occupato il posto nella sala dello spettacolo, viene impostato lo stato del
	 * biglietto ad acquistato, viene applicato lo sconto in base alle politiche di sconto attive,
	 * viene aggiunto il prezzo finale all'incasso settimanale del multisala e all'incasso del film
	 * @param b Biglietto da acquistare/confermare
	 */
	public void acquistaBiglietto(Biglietto b)
	{
		b.setAcquistato();
		b.getSpettacolo().occupa(b.getLetteraPosto(), b.getNumeroPosto());
		ModuloSconto.applicaSconto(b);
		multisala.addAmount(b.getPrezzo());
		b.getSpettacolo().getFilm().addIncasso(b.getPrezzo());
	}

}
