package classi;

import java.io.Serializable;
import java.util.ArrayList;
import eccezioni.FilmNonPresenteException;

/**
 * 
 */
public class Multisala implements Serializable, Cloneable{
	
	private static final long serialVersionUID = -8943016259035415610L;
	private ArrayList<Sala> listaSale;
	private ArrayList<Spettacolo> listaSpettacoli;
	private ArrayList<Film> listaFilm;
	private ArrayList<Utente> listaUtenti;
	private ArrayList<PoliticaSconto> listaPoliticheSconto;
	
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
	
	
	public Sala getSala(int numeroSala)
	{
		for (Sala s: listaSale)
		{
			if (s.getNumSala() == numeroSala)
				return s;
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param numeroSala
	 * @return
	 */
	public boolean removeSala(int numeroSala)
	{
		Sala s;
		
		if ((s = getSala(numeroSala)) != null)
				return listaSale.remove(s);
		
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
		Film f;
		if ((f = getFilm(idFilm)) != null)
				return listaFilm.remove(f);
		/*for (Film f: listaFilm)
		{
			if (f.getIdFilm() == idFilm)
				return listaFilm.remove(f);
		}*/
		
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
	
	/**
	 * 
	 * @param s
	 */
	public void addSpettacolo(Spettacolo s)
	{
		listaSpettacoli.add(s);
	}
	
	/**
	 * 
	 * @param id
	 * @return
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
	 * 
	 * @param id
	 * @return
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
	
	/**
	 * 
	 * @param p
	 */
	public void addPoliticaSconto(PoliticaSconto p)
	{
		listaPoliticheSconto.add(p);
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public boolean removePoliticaSconto(PoliticaSconto p)
	{
		if (getPoliticaSconto(p))
			return listaPoliticheSconto.remove(p);
		else
			return false;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public boolean getPoliticaSconto(PoliticaSconto p)
	{
		for (PoliticaSconto politica: listaPoliticheSconto)
		{
			if (politica.equals(p))
				return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 */
	public Multisala clone()
	{
		try {
			Multisala clone = (Multisala) super.clone();
			ArrayList<Sala> a1 = new ArrayList<Sala>();
			ArrayList<Spettacolo> a2 = new ArrayList<Spettacolo>();
			ArrayList<Film> a3 = new ArrayList<Film>();
			ArrayList<Utente> a4 = new ArrayList<Utente>();
			ArrayList<PoliticaSconto> a5 = new ArrayList<PoliticaSconto>();
			
			for (Sala s: listaSale)
			{
				a1.add(s.clone());
			}
			
			for (Spettacolo s: listaSpettacoli)
			{
				a2.add(s.clone());
			}
			
			for (Film f: listaFilm)
			{
				a3.add(f.clone());
			}
			
			for (Utente u: listaUtenti)
			{
				a4.add(u.clone());
			}
			
			for (PoliticaSconto p: listaPoliticheSconto)
			{
				a5.add(p.clone());
			}
			
			clone.listaSale = a1;
			clone.listaSpettacoli = a2;
			clone.listaFilm = a3;
			clone.listaUtenti = a4;
			clone.listaPoliticheSconto = a5;
			
			return clone;
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 */
	public boolean equals(Object o)
	{
		if (o == null || o.getClass() != getClass())
			return false;
		
		Multisala m = (Multisala) o;
		
		return listaFilm.equals(m.listaFilm) && listaSale.equals(m.listaSale) &&
				listaUtenti.equals(m.listaUtenti) && listaSpettacoli.equals(m.listaSpettacoli) &&
				listaPoliticheSconto.equals(m.listaPoliticheSconto);
	}
	
	/**
	 * 
	 */
	public String toString()
	{
		return getClass().getSimpleName() + "[Sale:\n" + listaSale + ", Spettacoli:\n"
				+ listaSpettacoli + ", Film:\n" + listaPoliticheSconto 
				+ "Utenti:\n" + listaUtenti + "]";
	}
}
