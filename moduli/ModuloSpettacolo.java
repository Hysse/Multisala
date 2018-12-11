package moduli;

import java.util.ArrayList;
import classi.Spettacolo;
import eccezioni.OraSpettacoloException;

/**
 * Classe per gestire gli Spettacoli di un multisala
 */
public class ModuloSpettacolo {
	
	private ArrayList<Spettacolo> listaSpettacoli;
	
	/**
	 * Costruttore del moduloSpettacolo per gestire gli spettacoli del multisala
	 * @param multisala Multisala da dove prendere la lista degli Spettacoli da gestire
	 */
	public ModuloSpettacolo(classi.Multisala multisala)
	{
		this.listaSpettacoli = multisala.getListaSpettacoli();
	}
	
	/**
	 * Metodo per aggiungere uno spettacolo a una lista di spettacoli
	 * @param spettacolo spettacolo da aggiungere
	 * @throws OraSpettacoloException eccezione lanciata nel caso in cui
	 * lo spettacolo non può essere aggiunto poichè nell'ora in cui inizia è 
	 * gia presente un altro spettacolo che ancora deve finire
	 */
	public void addSpettacolo(Spettacolo spettacolo) throws OraSpettacoloException
	{
		for(Spettacolo s : listaSpettacoli)
		{
			if(s.getDataInizio().before(spettacolo.getDataInizio())
				&& s.getDataFine().after(spettacolo.getDataInizio()))
				throw new OraSpettacoloException();
		}
		listaSpettacoli.add(spettacolo);
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
			return listaSpettacoli.remove(s);
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
		for (Spettacolo s: listaSpettacoli)
		{
			if (s.getId() == id)
				return s;
		}
		
		return null;
	}
}
