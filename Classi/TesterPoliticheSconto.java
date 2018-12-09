package classi;

import java.util.ArrayList;
import java.util.Date;

//PROVARE AD IMPLEMENTARE POLITICHE DI SCONTO SIA COME CLASSE ASTRATTA SIA COME INTERFACCIA. PENSACI!
public class TesterPoliticheSconto {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Cliente cliente = new Cliente("David","Ciaociao1.",19);
		
		class PoliticaEta implements PoliticaSconto
		{
			public double getSconto(Biglietto b)
			{
				if(cliente.getEta() > 18)
					return (b.getPrezzo() * 45) / 100;
				else
					return 0.0;
			}
		}
		
		class PoliticaDomenica implements PoliticaSconto
		{
			public double getSconto(Biglietto b)
			{
				if(b.getSpettacolo().getDataInizio().getDay() == 0)
					return (b.getPrezzo() * 40) / 100;
				else
					return 0.0;
			}
		}
		
		class PoliticaPomeriggio implements PoliticaSconto
		{
			public double getSconto(Biglietto b)
			{
				if(b.getSpettacolo().getDataInizio().getHours() >= 13)
					return (b.getPrezzo() * 20) / 100;
				else
					return 0.0;
			}
		}
		
		ArrayList<Posto> posti = new ArrayList<Posto>();
		posti.add(new Posto('a',1));
		posti.add(new Posto('a',2));
		posti.add(new Posto('a',3));
		posti.add(new Posto('b',1));
		posti.add(new Posto('b',2));
		posti.add(new Posto('b',3));
		posti.add(new Posto('c',1));
		posti.add(new Posto('c',2));
		posti.add(new Posto('c',3));
		posti.add(new Posto('c',4));
		
		Sala sala1 = new Sala(1, posti);
		
		Film film = new Film("L'uomo che visse due volte","Un uomo che praticamente visse due volte. Assurdo fra",120);
		
		Date data = new Date(2018,11,8,13,0);
		
		Spettacolo spettacolo = new Spettacolo(sala1, film, data, 20.00);
		
		Posto postobiglietto = new Posto('a', 3);
		Biglietto biglietto = new Biglietto(spettacolo,postobiglietto.getLet(),postobiglietto.getNum(),false,spettacolo.getPrezzo());
		spettacolo.occupa(postobiglietto);
		
		PoliticaSconto sconto1 = new PoliticaEta();
		PoliticaSconto sconto2 = new PoliticaDomenica();
		PoliticaSconto sconto3 = new PoliticaPomeriggio();
		ArrayList<PoliticaSconto> politicheAttive = new ArrayList<PoliticaSconto>();
		politicheAttive.add(sconto1);
		politicheAttive.add(sconto2);
		politicheAttive.add(sconto3);
		
		double prezzofinale = biglietto.getPrezzo();
		double temp;

		for(PoliticaSconto p : politicheAttive)
		{
			temp = p.getSconto(biglietto);
			if ((prezzofinale -= temp) < 0.0)
				prezzofinale = 0.0;
		}
		biglietto.setPrezzo(prezzofinale);
		
		System.out.println(biglietto.getSpettacolo().getFilm().getTitolo());
		System.out.println(biglietto.getSpettacolo().getDataInizio());
		System.out.println("Prezzo iniziale = "+biglietto.getSpettacolo().getPrezzo());
		System.out.println("Prezzo scontato = "+biglietto.getPrezzo());
		System.out.println("Sala dello spettacolo = " + biglietto.getSpettacolo().getSala().getNumSala());
		System.out.println("\nStato Sala 1 dopo la prenotazione/acquisto :\n"+biglietto.getSpettacolo().getSala().displayPosti());
	}

}
