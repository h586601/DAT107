package no.hvl.dat107.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ansatt", schema = "firma", uniqueConstraints = { @UniqueConstraint(columnNames = { "brukernavn" }) })
public class Ansatt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ansattID;

	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansDato;
	private String stilling;
	private BigDecimal mndsLonn;

	@ManyToOne
	@JoinColumn(name = "avdelingID", referencedColumnName = "avdelingID")
	private Avdeling avdeling;
	
	@OneToMany(mappedBy="ansatt")
	private List<Prosjektdeltagelse> deltagelser;
	
	public Ansatt() {}
	
	public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate ansDato, String stilling,
			BigDecimal mndsLonn, Avdeling avdeling) {
		super();
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansDato = ansDato;
		this.stilling = stilling;
		this.mndsLonn = mndsLonn;
		this.avdeling = avdeling;
	}



	public void leggTilProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
		deltagelser.add(prosjektdeltagelse);
	}
	
	public void fjernFraProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
		deltagelser.remove(prosjektdeltagelse);
	}
	
	public Avdeling getAvdeling() {
		return avdeling;
	}

	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public LocalDate getAnsDato() {
		return ansDato;
	}

	public void setAnsDato(LocalDate ansDato) {
		this.ansDato = ansDato;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public BigDecimal getMndsLønn() {
		return mndsLonn;
	}

	public void setMndsLønn(BigDecimal mndsLonn) {
		this.mndsLonn = mndsLonn;
	}
	
	

	public void skrivUt() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "Ansatt ID: \t" + ansattID + "\nBrukernavn: \t" + brukernavn + "\nFornavn: \t" + fornavn
				+ "\nEtternavn: \t" + etternavn + "\nAnsattdato: \t" + ansDato + "\nStilling: \t" + stilling
				+ "\nMånedslønn: \t" + mndsLonn + "\n" + avdeling;
	}

	
	public void skrivUt(String innrykk) {
        System.out.printf("%sAnsatt nr %d: %s %s", innrykk, ansattID, fornavn, etternavn);
    }
	
	public void skrivUtMedProsjekter() {
        System.out.println();
        skrivUt("");
        deltagelser.forEach(p -> p.skrivUt("\n   "));
    }
	
	

}
