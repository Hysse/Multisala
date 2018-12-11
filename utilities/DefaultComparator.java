package utilities;

import java.util.Comparator;

/**
 * Classe parametrica che implementa l'interfaccia Comparator
 * @param <T> Oggetto su cui usare il metodo compare
 */
public class DefaultComparator <T extends Comparable<T>> implements Comparator<T>{

	public int compare(T o1, T o2) {
		
		return o1.compareTo(o2);
	}

}
