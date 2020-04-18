package no.hvl.dat107.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prosjekt", schema = "firma")
public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektID;
	
	String prosjektnavn;
	String beskrivelse;
	
	@OneToMany(mappedBy = "prosjekt")
	private List<Prosjektdeltagelse> deltagelser;
	
	public Prosjekt() {}
	
	public Prosjekt(String prosjektnavn, String beskrivelse) {
		this.prosjektnavn = prosjektnavn;
		this.beskrivelse = beskrivelse;
	}
	
	public void leggTilProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
		deltagelser.add(prosjektdeltagelse);
	}
	
	public void fjernProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
        deltagelser.remove(prosjektdeltagelse);
    }
	
	public int getProsjektID() {
		return prosjektID;
	}

	public String getProsjektnavn() {
		return prosjektnavn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public List<Prosjektdeltagelse> getDeltagelse() {
		return deltagelser;
	}

	public void skrivUt(String innrykk) {
        System.out.printf("%sProsjekt nr %d: %s", innrykk, prosjektID, prosjektnavn);
    }
    
    public void skrivUtMedAnsatte() {
        System.out.println();
        skrivUt("");
        deltagelser.forEach(a -> a.skrivUt("\n   "));
    }
}
