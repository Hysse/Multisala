package classi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

import eccezioni.FilmNonPresenteException;
import eccezioni.OraSpettacoloException;
import eccezioni.SpettacoloIDException;
import grafica.FrameLogin;
import moduli.ModuloGestore;
import moduli.ModuloSala;
import moduli.ModuloSconto;
import moduli.ModuloSpettacolo;

public class Visualizzatore {

	public static void main(String[] args) {
		Multisala multisala = null;
		File file = new File("multisala.dat");
		try
		{
			if(!file.exists())
			{
					multisala = new Multisala();
					file.createNewFile();
					FlussoGenerico<Multisala> flusso = new FlussoGenerico<Multisala>("multisala.dat");
					Utente gestore = new Utente("admin","admin",true);
					multisala.addUtente(gestore);
					ArrayList<Posto> posti1 = new ArrayList<Posto>();
					posti1.add(new Posto('a',1));
					posti1.add(new Posto('a',2));
					posti1.add(new Posto('a',3));
					posti1.add(new Posto('b',1));
					posti1.add(new Posto('b',2));
					posti1.add(new Posto('b',3));
					posti1.add(new Posto('c',1));
					posti1.add(new Posto('c',2));
					posti1.add(new Posto('c',3));
					
					ArrayList<Posto> posti2 = new ArrayList<Posto>();
					posti2.add(new Posto('a',1));
					posti2.add(new Posto('a',2));
					posti2.add(new Posto('a',3));
					posti2.add(new Posto('b',1));
					posti2.add(new Posto('b',2));
					posti2.add(new Posto('b',3));
					posti2.add(new Posto('c',1));
					posti2.add(new Posto('c',2));
					posti2.add(new Posto('c',3));
					posti2.add(new Posto('d',1));
					posti2.add(new Posto('d',2));
					posti2.add(new Posto('d',3));
					
					Sala sala1 = new Sala(1,posti1,3);
					Sala sala2 = new Sala(2,posti2,3);
					
					Film film1 = new Film("L'uomo che visse due volte","Un uomo che praticamente visse due volte.",120, 1);
					Film film2 = new Film("Superman","Un uomo dai poteri fantastici con forza sovraumana",85, 2);
					Film film3 = new Film("La città incantata","Film d'animazione di Miazaki che parla di una città incantata e di una ragazzina che ci si ritrova per errore",80, 3);
					Film film4 = new Film("Spiderman","Un ragazzo viene morso da un ragno e ne acquista i poteri",110, 4);
					
					GregorianCalendar data1 = new GregorianCalendar(2018,11,26,23,50);
					GregorianCalendar data2 = new GregorianCalendar(2018,11,14,18,10);
					GregorianCalendar data3 = new GregorianCalendar(2018,11,15,12,0);
					GregorianCalendar data4 = new GregorianCalendar(2018,11,22,13,0);
					
					ModuloGestore modGes = new ModuloGestore(multisala);
					ModuloSpettacolo modSpe = new ModuloSpettacolo(multisala);
					ModuloSala modSala = new ModuloSala(multisala);
					
					modSala.addSala(sala1);
					modSala.addSala(sala2);
					
					modSpe.addFilm(film1);
					modSpe.addFilm(film2);
					modSpe.addFilm(film3);
					modSpe.addFilm(film4);
					try
					{
						modGes.addSpettacolo(1, 1, 1, 20.00, data1);
						modGes.addSpettacolo(2, 2, 2, 25.00, data2);
						modGes.addSpettacolo(3, 3, 2, 30.00, data3);
						modGes.addSpettacolo(4, 4, 1, 22.00, data4);
					}
					catch(OraSpettacoloException e){
						System.out.println(e.getMessage());
					} catch (FilmNonPresenteException e) {
						System.out.println(e.getMessage());
					} catch (SpettacoloIDException e) {
						System.out.println(e.getMessage());
					}
					flusso.save(multisala);
			}
			FlussoGenerico<Multisala> flusso = new FlussoGenerico<Multisala>("multisala.dat");
			multisala = flusso.load();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		JFrame loginframe = new FrameLogin();
		loginframe.setVisible(true);
	}
}
