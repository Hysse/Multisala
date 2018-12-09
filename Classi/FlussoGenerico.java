package classi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Classe che viene utilizzata per scrivere e leggere flussi di oggetti da file
 * di qualsiasi classe poichè parametrica
 * @param <T> parametro che indica un qualsiasi oggetto Serializable
 */
public class FlussoGenerico<T extends Serializable> {
	
	private String filename;
	
	/**
	 * Costruttore che salva il nome del file dove salvare o leggere un flusso di dati
	 * @param file stringa col nome del file
	 */
	public FlussoGenerico(String file)
	{
		filename = file;
	}
	
	/**
	 * Metodo che serve a salvare un flusso di oggetti nel file
	 * @param obj oggetto da salvare
	 * @throws IOException lanciata se c'è un errore nell'aprire il file
	 */
	public void save(T obj) throws IOException
	{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
		out.writeObject(obj);
		out.close();
	}
	
	/**
	 * Metodo che carica un flusso di oggetti
	 * @return ritorna l'oggetto letto dal flusso
	 * @throws IOException lanciata se c'è un errore nell'aprire il file
	 * @throws ClassNotFoundException lanciata se il nome della classe non può essere specificato
	 */
	public T load() throws IOException,ClassNotFoundException
	{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
		@SuppressWarnings("unchecked")
		T obj = (T)in.readObject();
		in.close();
		return obj;
	}
}
