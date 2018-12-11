package utilities;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Classe parametrica che viene utilizzata per ordinare una lista di oggetti
 * con un algoritmo di ordinamento
 * @param <T> Oggetto che implementa l'interfaccia Comparator
 */
public class Sort <T> {
	
	/**
	 * Costruttore che salva un comparatore e una lista di oggetti da ordinare
	 * @param c Comparatore che ha un metodo compare con cui distinguere due oggetti T
	 * @param array lista di elementi da salvare
	 */
	public Sort(Comparator<T> c, ArrayList<T> array)
	{
		this.c = c;
		this.x = array;
	}
	
	/**
	 * Algoritmo di ordinamento
	 */
	public void insertionSort()
	{
		T key;
		int j;
		
		for (int i = 1; i < x.size(); i++)
		{
			key = x.get(i);
			j = i - 1;
			
			while (j >= 0 && c.compare(x.get(j), key) > 0)
			{
				x.set(j + 1, x.get(j));
				j--;
			}
			x.set(j + 1, key);
		}	
	}
	
	private ArrayList<T> x;
	private Comparator<T> c;
}
