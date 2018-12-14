package grafica;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classi.Biglietto;
import classi.Multisala;
import classi.PoliticaSconto;
import listeners.CloseListener;
import moduli.ModuloSconto;

public class FrameSconto extends JFrame{

	private static final long serialVersionUID = -646944021273498249L;
	
	Multisala m;
	
	public FrameSconto(Multisala m)
	{
		this.m = m;
		setSize(300, 300);
		createCheckBox();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new CloseListener(m));
	}
	
	private void createCheckBox()
	{
		JPanel pannello = new JPanel();
		pannello.setLayout(new BorderLayout());
		JPanel sconti = new JPanel();
		sconti.setLayout(new GridLayout(1, 3));
		ModuloSconto modSco = new ModuloSconto(m);
		Checkbox c1 = new Checkbox(PoliticaSpiderman.class.getSimpleName(), modSco.getPoliticaSconto(new PoliticaSpiderman()) != null);
		Checkbox c2 = new Checkbox(PoliticaDomenica.class.getSimpleName(), modSco.getPoliticaSconto(new PoliticaDomenica()) != null);
		Checkbox c3 = new Checkbox(PoliticaPomeriggio.class.getSimpleName(), modSco.getPoliticaSconto(new PoliticaPomeriggio()) != null);
		sconti.add(c1);
		sconti.add(c2);
		sconti.add(c3);
		
		pannello.add(sconti, BorderLayout.CENTER);
		
		JButton aggiungiSconto = new JButton("Aggiungi sconto");
		
		class aggiungiScontoListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				
				ModuloSconto modSco = new ModuloSconto(m);
				if (c1.getState() == true)
					modSco.addPoliticaSconto(new PoliticaSpiderman());
				else
					modSco.removePoliticaSconto(new PoliticaSpiderman());
				
				if (c2.getState() == true)
					modSco.addPoliticaSconto(new PoliticaDomenica());
				else
					modSco.removePoliticaSconto(new PoliticaDomenica());
				
				if (c3.getState() == true)
					modSco.addPoliticaSconto(new PoliticaPomeriggio());
				else
					modSco.removePoliticaSconto(new PoliticaPomeriggio());
				
				FrameGestore f = new FrameGestore(m);
				f.setVisible(true);
				dispose();
			}
		}
		
		aggiungiScontoListener a = new aggiungiScontoListener();
		aggiungiSconto.addActionListener(a);
		pannello.add(aggiungiSconto, BorderLayout.SOUTH);
		
		add(pannello);
		pack();
	}
	
	private class PoliticaSpiderman extends PoliticaSconto
	{
		private static final long serialVersionUID = 5537778735811244143L;

		public double getSconto(Biglietto b)
		{
			if(b.getSpettacolo().getFilm().getTitolo().equals("Spiderman"))
				return b.getPrezzo()-((b.getPrezzo() * 45) / 100);
			else
				return b.getPrezzo();
		}
	}
	
	private class PoliticaDomenica extends PoliticaSconto
	{

		private static final long serialVersionUID = -295664013413977913L;

		public double getSconto(Biglietto b)
		{
			if(b.getSpettacolo().getDataInizio().get(Calendar.DAY_OF_WEEK) == 0)
				return b.getPrezzo()-((b.getPrezzo() * 40) / 100);
			else
				return b.getPrezzo();
		}
	}
	
	private class PoliticaPomeriggio extends PoliticaSconto
	{
		private static final long serialVersionUID = 2423014435575286864L;

		public double getSconto(Biglietto b)
		{
			if(b.getSpettacolo().getDataInizio().get(Calendar.HOUR) > 11)
				return b.getPrezzo()-((b.getPrezzo() * 20) / 100);
			else
				return b.getPrezzo();
		}
	}

}
