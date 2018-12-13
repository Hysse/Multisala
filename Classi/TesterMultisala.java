package classi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import eccezioni.OraPrenotazioneException;
import eccezioni.OraSpettacoloException;
import eccezioni.PostoIndisponibileException;
import eccezioni.PostoNonEsistenteException;
import moduli.ModuloCliente;
import moduli.ModuloGestore;
import moduli.ModuloBiglietto;
import moduli.ModuloSala;
import moduli.ModuloSconto;
import moduli.ModuloSpettacolo;

public class TesterMultisala {

	public static void main(String[] args) {
		//LE POLITICHE SARANNO DEFINITE NEL MODULO SCONTI.
		Multisala multisala;
		/*File file = new File("multisala.dat");
		if(!file.exists())
		{
			try
			{
				file.createNewFile();
				FlussoGenerico<Multisala> flusso = new FlussoGenerico<Multisala>("multisala.dat");
				flusso.save(new Multisala());
			}
			catch(IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
		FlussoGenerico<Multisala> flusso = new FlussoGenerico<Multisala>("multisala.dat");
		try {
			multisala = flusso.load();
		} catch (ClassNotFoundException | IOException e) {
			multisala = null;
			System.out.println(e.getMessage());
		}*/
				
		multisala = new Multisala();
		Cliente cliente = new Cliente("David","Ciao",19);
		
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
		posti1.add(new Posto('c',4));
		
		ArrayList<Posto> posti2 = new ArrayList<Posto>();
		posti2.add(new Posto('a',1));
		posti2.add(new Posto('a',2));
		posti2.add(new Posto('a',3));
		posti2.add(new Posto('b',1));
		posti2.add(new Posto('b',2));
		posti2.add(new Posto('b',3));
		
		Sala sala1 = new Sala(1, posti1);
		Sala sala2 = new Sala(2,posti2);
		
		Film film1 = new Film("L'uomo che visse due volte","Un uomo che praticamente visse due volte. Assurdo fra",120, 1);
		Film film2 = new Film("La città incantata","Film d'animazione di Miazaki",80, 2);
		Film film3 = new Film("Spiderman","Un ragazzo viene morso da un ragno e ne acquista i poteri",110, 3);
		
		GregorianCalendar data1 = new GregorianCalendar(2018,11,12,23,50);
		GregorianCalendar data2 = new GregorianCalendar(2018,11,14,18,10);
		GregorianCalendar data3 = new GregorianCalendar(2018,11,15,12,0);
		GregorianCalendar data4 = new GregorianCalendar(2018,11,17,12,30);
		
		Spettacolo spettacolo1 = new Spettacolo(1,sala1, film1, data1, 25.00);
		Spettacolo spettacolo2 = new Spettacolo(2,sala2, film2, data2, 25.00);
		Spettacolo spettacolo3 = new Spettacolo(3,sala2, film3, data3, 30.00);
		
		ModuloGestore modGes = new ModuloGestore(multisala);
		ModuloCliente modCli = new ModuloCliente(multisala,cliente);
		ModuloSpettacolo modSpe = new ModuloSpettacolo(multisala);
		ModuloSala modSala = new ModuloSala(multisala);
		ModuloSconto modSconto = new ModuloSconto(multisala);
		ModuloBiglietto modPre = new ModuloBiglietto(multisala, cliente);
		
		modSala.addSala(sala1);
		modSala.addSala(sala2);
		
		modSpe.addFilm(film1);
		modSpe.addFilm(film2);
		modSpe.addFilm(film3);
		try
		{
			modSpe.addSpettacolo(1,film1, 2, 20.00, data1);
			modSpe.addSpettacolo(2,film2, 2, 20.00, data2);
			modSpe.addSpettacolo(3, film3, 2, 15.00, data3);
			modSpe.addSpettacolo(4, film1, 1, 10.00, data4);
		}
		catch(OraSpettacoloException e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("Spettacoli fruibili TITOLO :");
		ArrayList<Spettacolo> fruibilisorttitolo = modSpe.SortSpettacoliTitolo(modSpe.getSpettacoliFruibili());
		for(Spettacolo s : fruibilisorttitolo)
			System.out.println(""+s.displayContent());
		
		
		try
		{
			if(modPre.addPrenotazione(spettacolo3, 'a', 3) == null)
				System.out.println("Prenotazione già effettuata");
			if(modPre.addPrenotazione(spettacolo2, 'b', 3) == null)
				System.out.println("Prenotazione già effettuata");
		}
		catch(PostoIndisponibileException e)
		{
			System.out.println(e.getMessage());
		}
		catch(OraPrenotazioneException e)
		{
			System.out.println(e.getMessage());
		} catch (PostoNonEsistenteException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Bella");
	}
}

