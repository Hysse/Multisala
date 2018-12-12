package moduli;

import java.util.ArrayList;
import classi.Posto;
import classi.Sala;

/**
 * Classe per gestire Sale di un multisala
 */
public class ModuloSala {
	
	private ArrayList<Sala> listaSale;
	
	/**
	 * Costruttore del moduloSala che salva una lista di sale da gestire
	 * @param multisala multisala da cui prendere la lista di sale da salvare
	 */
	public ModuloSala(classi.Multisala multisala)
	{
		listaSale = multisala.getListaSale();
	}
	
	/**
	 * Metodo che aggiunge una sala alla lista delle sale
	 * @param s Sala da aggiungere
	 */
	public void addSala(Sala s) 
	{
		listaSale.add(s);
	}
	
	/**
	 * Metodo che restituisce una Sala con un numero sala che vi può essere associato
	 * @param numeroSala int con numero della sala da cercare
	 * @return Sala cercata con numero corrispondente, altrimenti null se la sala non è presente
	 */
	public Sala getSala(int numeroSala)
	{
		for (Sala s: listaSale)
		{
			if (s.getNumSala() == numeroSala)
				return s;
		}
		
		return null;
	}
	public int getNumeroSale()
	{
		return listaSale.size();
	}
	/**
	 * Metodo che rimuove una sala identificata da un numero
	 * @param numeroSala int con numero della sala da rimuovere
	 * @return true se la sala è stata rimossa, false altrimenti
	 */
	public boolean removeSala(int numeroSala)
	{
		Sala s;
		
		if ((s = getSala(numeroSala)) != null)
				return listaSale.remove(s);
		
		return false;
	}
	
	/**
	 * Cerca un posto in una sala e lo imposta disponibile
	 * @param p posto da cercare nella sala
	 * @param numSala numero sala dove cercare il posto da imposare disponibile
	 * @return true se il posto è presente ed è stato impostato disponibile e la sala esiste
	 * false altrimenti
	 */
	public boolean setDisponibile(Posto p, int numSala)
	{
		Posto posto;
		for (Sala s: listaSale)
		{
			if (s.getNumSala() == numSala)
			{
				if ((posto = s.getPosto(p)) != null)
				{
					posto.setDisponibile();
					return true;
				}
				else
					return false;
			}
		}
		
		return false;
	}
	
	/**
	 * Cerca un posto in una sala e lo imposta indisponibile
	 * @param p posto da cercare nella sala
	 * @param numSala numero sala dove cercare il posto da imposare indisponibile
	 * @return true se il posto è presente ed è stato impostato indisponibile e la sala esiste,
	 * false altrimenti
	 */
	public boolean setIndisponibile(Posto p, int numSala)
	{
		Posto posto;
		for (Sala s: listaSale)
		{
			if (s.getNumSala() == numSala)
			{
				if ((posto = s.getPosto(p)) != null)
				{
					posto.setIndisponibile();
					return true;
				}
				else
					return false;
			}
		}
		
		return false;
	}
}
