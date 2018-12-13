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
	
	public ArrayList<Spettacolo> getSpettacoliSale(Sala sala)
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
	
	public boolean prenotaBiglietto(Spettacolo spettacolo,char lettera,int numero) throws OraPrenotazioneException,PostoIndisponibileException,PostoNonEsistenteException
	{
		ModuloPrenotazione modPre = new ModuloPrenotazione(multisala, cliente);
		if(modPre.addPrenotazione(spettacolo, lettera, numero) == null)
			return false;
		else
			return true;
	}
	
	public boolean cancellaPrenotazione(int idSpettacolo)
	{
		ModuloPrenotazione modPre = new ModuloPrenotazione(multisala, cliente);
		Biglietto b = modPre.getBiglietto(idSpettacolo);
		return modPre.removePrenotazione(b);
	}
	
	public boolean acquistoDiretto(Spettacolo spettacolo,char lettera,int numero) throws PostoIndisponibileException, OraPrenotazioneException, PostoOccupatoException, PostoNonEsistenteException
	{
		ModuloPrenotazione modPre = new ModuloPrenotazione(multisala, cliente);
		if(modPre.acquistoDiretto(spettacolo, lettera, numero) == null)
			return false;
		else 
			return true;
	}
	
	public boolean acquistoPrenotazione(int idSpettacolo) throws PostoIndisponibileException, OraPrenotazioneException
	{
		ModuloPrenotazione modPre = new ModuloPrenotazione(multisala, cliente);
		Biglietto b = modPre.getBiglietto(idSpettacolo);
		if(modPre.acquistoConPrenotazione(b) == null)
			return false;
		else 
			return true;
	}

}
