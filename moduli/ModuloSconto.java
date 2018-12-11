package moduli;

import java.util.ArrayList;
import classi.PoliticaSconto;

/**
 * Classe che serve a gestire le politiche di sconto
 */
public class ModuloSconto {
	
	private ArrayList<PoliticaSconto> listaPoliticheSconto;
	
	/**
	 * Costruttore che salva una lista di poliche di sconto di un multisala
	 * @param multisala Multisala dai cui prendere la lista delle politiche di Sconto
	 */
	public ModuloSconto(classi.Multisala multisala)
	{
		listaPoliticheSconto = multisala.getListaPoliticheSconto();
	}
	
	/**
	 * Metodo che aggiunge una politica di sconto alla lista
	 * @param p PoliticaSconto da aggiungere
	 */
	public void addPoliticaSconto(PoliticaSconto p)
	{
		listaPoliticheSconto.add(p);
	}
	
	/**
	 * Meotodo che rimuove una politica di sconto dalla lista
	 * @param p Politica di sconto da rimuovere
	 * @return true se la Politica di sconto è stata rimossa, false altrimenti
	 */
	public boolean removePoliticaSconto(PoliticaSconto p)
	{
		if (getPoliticaSconto(p) != null)
			return listaPoliticheSconto.remove(p);
		else
			return false;
	}
	
	/**
	 * Metodo utilizzato per verificare se una politica di sconto è presente nella lista
	 * delle politiche di sconto, cioè è attiva
	 * @param p Politica di sconto da cercare
	 * @return una politica di sconto se è presente nella lista, false altrimenti
	 */
	public PoliticaSconto getPoliticaSconto(PoliticaSconto p)
	{
		for (PoliticaSconto politica: listaPoliticheSconto)
		{
			if (politica.equals(p))
				return politica;
		}
		
		return null;
	}

}
