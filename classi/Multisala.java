package classi;

import java.util.ArrayList;

import eccezioni.FilmNonPresenteException;

/**
 * 
 */
public class Multisala {
	
	private ArrayList<Sala> listaSale;
	private ArrayList<Spettacolo> listaSpettacoli;
	private ArrayList<Film> listaFilm;
	private ArrayList<Utente> listaUtenti;
	
	/**
	 * 
	 * @param listaSale
	 * @param listaSpettacoli
	 * @param listaFilm
	 */
	public Multisala(ArrayList<Sala> listaSale, ArrayList<Spettacolo> listaSpettacoli,
			ArrayList<Film> listaFilm, ArrayList<Utente> listaUtenti)
	{
		this.listaSale = listaSale;
		this.listaSpettacoli = listaSpettacoli;
		this.listaFilm = listaFilm;
		this.listaUtenti = listaUtenti;
	}
	
	/**
	 * 
	 * @param s
	 */
	public void addSala(Sala s)
	{
		listaSale.add(s);
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public boolean removeSala(int i)
	{
		for (Sala s: listaSale)
		{
			if (s.getNumSala() == i)
				return listaSale.remove(s);
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param u
	 */
	public void addUtente(Utente u)
	{
		listaUtenti.add(u);
	}
	
	/**
	 * 
	 * @param u
	 * @return
	 */
	public boolean removeUtente(Utente u)
	{
		return listaUtenti.remove(u);
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Utente> getListaUtenti()
	{
		return this.listaUtenti;
	}
	
	/**
	 * 
	 * @param f
	 */
	public void addFilm(Film f)
	{
		listaFilm.add(f);
	}
	
	/**
	 * 
	 * @param idFilm
	 * @return
	 * @throws FilmNonPresenteException
	 */
	public boolean removeFilm(int idFilm) throws FilmNonPresenteException
	{
		for (Film f: listaFilm)
		{
			if (f.getIdFilm() == idFilm)
				return listaFilm.remove(f);
		}
		
		throw new FilmNonPresenteException();
	}
	
	/**
	 * 
	 * @param filmId
	 * @return
	 * @throws FilmNonPresenteException
	 */
	public Film getFilm(int filmId) throws FilmNonPresenteException
	{
		for (Film f: listaFilm)
		{
			if (f.getIdFilm() == filmId)
				return f;
		}
		
		throw new FilmNonPresenteException();
	}

}
