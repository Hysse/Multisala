package classi;

import java.util.ArrayList;
import java.util.Date;

public class TesterMultisala {

	public static void main(String[] args) {
		//LE POLITICHE SARANNO DEFINITE NEL MODULO SCONTI.
		Cliente cliente = new Cliente("David","Ciaociao1.",19);
		
		class PoliticaEta extends PoliticaSconto
		{
			private static final long serialVersionUID = 5537778735811244143L;

			public double getSconto(Biglietto b)
			{
				if(cliente.getEta() > 18)
					return b.getPrezzo()-((b.getPrezzo() * 45) / 100);
				else
					return b.getPrezzo();
			}
		}
		
		class PoliticaDomenica extends PoliticaSconto
		{

			private static final long serialVersionUID = -295664013413977913L;

			public double getSconto(Biglietto b)
			{
				if(b.getSpettacolo().getDataInizio().getDay() == 0)
					return b.getPrezzo()-((b.getPrezzo() * 40) / 100);
				else
					return b.getPrezzo();
			}
		}
		
		class PoliticaPomeriggio extends PoliticaSconto
		{
			private static final long serialVersionUID = 2423014435575286864L;

			public double getSconto(Biglietto b)
			{
				if(b.getSpettacolo().getDataInizio().getHours() > 11)
					return b.getPrezzo()-((b.getPrezzo() * 20) / 100);
				else
					return b.getPrezzo();
			}
		}
				
		
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
		
		Date data1 = new Date(2018,11,8,12,0);
		Date data2 = new Date(2018,11,10,11,0);
		Date data3 = new Date(2018,11,10,12,0);
		
		Spettacolo spettacolo1 = new Spettacolo(sala1, film1, data1, 25.00);
		Spettacolo spettacolo2 = new Spettacolo(sala2, film2, data2, 25.00);
		Spettacolo spettacolo3 = new Spettacolo(sala2, film3, data3, 30.00);
	}

}
