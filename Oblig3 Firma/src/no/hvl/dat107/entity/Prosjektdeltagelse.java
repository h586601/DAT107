package no.hvl.dat107.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prosjektdeltagelse", schema = "firma")
public class Prosjektdeltagelse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektdeltaID;
	
	private String rolle;
	private int timer;
	
	@ManyToOne
	@JoinColumn(name="ansattID")
	private Ansatt ansatt;
	
	@ManyToOne
	@JoinColumn(name="prosjektID")
	private Prosjekt prosjekt;
	
	public Prosjektdeltagelse() {}
	
	public Prosjektdeltagelse(Ansatt ansatt, Prosjekt prosjekt, String rolle, int timer) {
		this.ansatt = ansatt;
		this.prosjekt = prosjekt;
		this.rolle = rolle;
		this.timer = timer;
		
		ansatt.leggTilProsjektdeltagelse(this);
        prosjekt.leggTilProsjektdeltagelse(this);
	}
	

	public void skrivUt() {
		System.out.println("\n" + rolle + "\n" + timer);
	}
	
	public void skrivUt(String innrykk) {
        System.out.printf("%sDeltagelse: %s %s, %s, %d timer", innrykk, 
                ansatt.getFornavn(), ansatt.getEtternavn(), prosjekt.getProsjektnavn(), timer);
    }
	
}
