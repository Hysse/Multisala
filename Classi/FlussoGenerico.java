package p1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class FlussoGenerico<T extends Serializable> {
	private String filename;
	
	public FlussoGenerico(String file)
	{
		filename = file;
	}
	
	public void save(T obj) throws IOException
	{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
		out.writeObject(obj);
		out.close();
	}
	
	public T load() throws IOException,ClassNotFoundException
	{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
		T obj = (T)in.readObject();
		in.close();
		return obj;
	}
}
