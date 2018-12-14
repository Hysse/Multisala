package classi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import eccezioni.BigliettoPresenteException;
import eccezioni.FilmNonPresenteException;
import eccezioni.OraPrenotazioneException;
import eccezioni.OraSpettacoloException;
import eccezioni.PostoIndisponibileException;
import eccezioni.PostoNonEsistenteException;
import eccezioni.PostoOccupatoException;
import eccezioni.SpettacoloIDException;
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
		Cliente cliente2 = new Cliente("Ciro","bella",20);
		
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
		
		Sala sala1 = new Sala(1, posti1,3);
		Sala sala2 = new Sala(2,posti2,3);
		
		Film film1 = new Film("L'uomo che visse due volte","Un uomo che praticamente visse due volte. Assurdo fra",120, 1);
		Film film2 = new Film("La città incantata","Film d'animazione di Miazaki",80, 2);
		Film film3 = new Film("Spiderman","Un ragazzo viene morso da un ragno e ne acquista i poteri",110, 3);
		
		GregorianCalendar data1 = new GregorianCalendar(2018,11,26,23,50);
		GregorianCalendar data2 = new GregorianCalendar(2018,11,14,18,10);
		GregorianCalendar data3 = new GregorianCalendar(2018,11,15,12,0);
		GregorianCalendar data4 = new GregorianCalendar(2018,11,10,12,30);
		
		ModuloGestore modGes = new ModuloGestore(multisala);
		ModuloCliente modCli = new ModuloCliente(multisala,cliente);
		ModuloCliente modCli2 = new ModuloCliente(multisala,cliente2);
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
			modGes.addSpettacolo(1, 1, 1, 20.00, data1);
			modGes.addSpettacolo(2, 2, 2, 25.00, data2);
			modGes.addSpettacolo(3, 2, 2, 30.00, data3);
		}
		catch(OraSpettacoloException e){
			System.out.println(e.getMessage());
		} catch (FilmNonPresenteException e) {
			System.out.println(e.getMessage());
		} catch (SpettacoloIDException e) {
			System.out.println(e.getMessage());
		}
		/*
		try
		{
			modCli.acquistoDiretto(1, 'a', 1);
			if(modCli.acquistoPrenotazione(1) == false)
				System.out.println("Non si ha una prenotazione per questo spettacolo");
			else
				System.out.println("Biglietto acquistato con successo");
		}
		catch(PostoIndisponibileException e)
		{
			System.out.println(e.getMessage());
		}
		catch(OraPrenotazioneException e) {
			System.out.println(e.getMessage());
		} catch (PostoNonEsistenteException e) {
			System.out.println(e.getMessage());
		} catch (BigliettoPresenteException e) {
			System.out.println(e.getMessage());
		} catch (PostoOccupatoException e) {
			System.out.println(e.getMessage());
		}
		*/
		for(Spettacolo s : modCli.getSpettacoliSala())
		{
			System.out.println(s.displayContent());
		}
		
		multisala.addAmount(20.00);
		System.out.println("Fine");
}
}

