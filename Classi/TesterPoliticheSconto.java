package classi;

import java.util.ArrayList;
import java.util.Date;

//PROVARE AD IMPLEMENTARE POLITICHE DI SCONTO SIA COME CLASSE ASTRATTA SIA COME INTERFACCIA. PENSACI!
public class TesterPoliticheSconto {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
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
		
		Film film = new Film("L'uomo che visse due volte","Un uomo che praticamente visse due volte. Assurdo fra",120, 1);
		
		Date data = new Date(2018,11,8,12,0);
		
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
			if(temp < prezzofinale)
				prezzofinale = temp;
		}
		biglietto.setPrezzo(prezzofinale);
		
		System.out.println(biglietto.getSpettacolo().getFilm().getTitolo());
		System.out.println(biglietto.getSpettacolo().getDataInizio());
		System.out.println("Prezzo iniziale : "+biglietto.getSpettacolo().getPrezzo());
		System.out.println("Prezzo scontato : "+biglietto.getPrezzo());
		System.out.println("Sala dello spettacolo : " + biglietto.getSpettacolo().getSala().getNumSala());
		System.out.println("Stato Sala 1 dopo la prenotazione/acquisto :"
				+ "\n"+biglietto.getSpettacolo().getSala().displayPosti());
	}

}
