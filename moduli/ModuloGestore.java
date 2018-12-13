package moduli;

import java.util.ArrayList;
import java.util.Calendar;
import classi.Film;
import classi.Multisala;
import classi.PoliticaSconto;
import classi.Posto;
import classi.Spettacolo;
import eccezioni.OraSpettacoloException;

/**
 * Classe utilizzata per gestire un multisala e permette di fare le seguenti operazioni su un
 * multisala:
 * 1. inserire il programma settimanale;
 * 2. fissare un prezzo per gli spettacoli;
 * 3. visualizzare gli spettacoli per posti disponibili crescenti;
 * 4. attivare politiche di sconto;
 * 5. rendere disponibili/indisponibili posti nelle sale;
 * 6. visualizzare l’incasso della settimana corrente totale e per film.
 */
public class ModuloGestore {
	
	private Multisala multisala;
	/**
	 * Costruttore che salva un multisala
	 * @param multisala multisala da salvare
	 */
	public ModuloGestore(Multisala multisala)
	{
		this.multisala = multisala;
	}
	
	/**
	 * Aggiunge uno spettacolo al programma settimanale
	 * @param id
	 * @param film
	 * @param numeroSala
	 * @param prezzo
	 * @param data
	 * @throws OraSpettacoloException 
	 */
	public void addSpettacolo(int id, Film film, int numeroSala, double prezzo, Calendar data) throws OraSpettacoloException
	{
		ModuloSpettacolo m = new ModuloSpettacolo(multisala);
		m.addSpettacolo(id, film, numeroSala, prezzo, data);
	}

	/**
	 * Metodo per fissare un prezzo per gli spettacoli
	 * @param s Spettacolo in cui modificare il prezzo
	 * @param prezzo Double con il prezzo nuovo
	 */
	public void setPrezzoSpettacolo(Spettacolo s, Double prezzo)
	{
		ModuloSpettacolo m = new ModuloSpettacolo(multisala);
		m.getSpettacolo(s.getID()).setPrezzo(prezzo);
	}
	
	/**
	 * Metodo che ritorna una lista con la copia degli spettacoli del multisala
	 * ordinati per numero di posti disponibili crescente
	 * @return lista ordinata per numero di posti liberi crescente
	 */
	public ArrayList<Spettacolo> spettacoliPostiDisponibili()
	{
		ModuloSpettacolo modulo = new ModuloSpettacolo(multisala);
		return modulo.SortSpettacoloNumPosti();
	}
	
	/**
	 * Metodo che aggiunge una politica di sconto alla lista delle politiche attive
	 * @param p politica di sconto da aggiungere, cioè attivare
	 * @return true se la politica è stata aggiunta, false altrimenti
	 */
	public boolean attivaPoliticaSconto(PoliticaSconto p)
	{
		ModuloSconto modSconto = new ModuloSconto(multisala);
		return modSconto.addPoliticaSconto(p);
	}
	
	/**
	 * Metodo per impostare un posto di una sala come disponibile
	 * @param numSala sala in cui cercare il posto
	 * @param lettera Char con lettera posto
	 * @param numero int con numero posto
	 * @return true se il posto è stato impostato come disponibile, false altrimenti
	 */
	public boolean setPostoDisponibile(int numSala, char lettera, int numero)
	{
		ModuloSala modSala = new ModuloSala(multisala);
		return modSala.setDisponibile(new Posto(lettera, numero), numSala);
	}
	
	/**
	 * Metodo per impostare un posto di una sala come indisponibile
	 * @param numSala sala in cui cercare il posto
	 * @param lettera Char con lettera posto
	 * @param numero int con numero posto
	 * @return true se il posto è stato impostato come indisponibile, false altrimenti
	 */
	public boolean setPostoIndisponibile(int numSala, char lettera, int numero)
	{
		ModuloSala modSala = new ModuloSala(multisala);
		return modSala.setIndisponibile(new Posto(lettera, numero), numSala);
	}
	
	/**
	 * Metodo che restituisce l'incasso settimanale totale corrente
	 * @return double con l'incasso totale della settimana corrente
	 */
	public double getIncassoTotaleSettimana()
	{
		return multisala.getIncassoSettimanale();
	}
	
	/**
	 * Metodo che ritorna una lista con i film della settimana.
	 * @return Arraylist con i film della settimana.
	 */
	public ArrayList<Film> getIncassoSettimanaleFilm()
	{
		ModuloSpettacolo modSpe = new ModuloSpettacolo(multisala);
		return modSpe.getIncassoSettimanaleFilm();
	}
}
