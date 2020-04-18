package no.hvl.dat107.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import no.hvl.dat107.dao.AnsattDAO;

@Entity
@Table(name = "Avdeling", schema = "Firma") 
public class Avdeling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int avdelingID;
	
	String avdNavn;
	int avdelingsleder;
	

	@OneToMany(mappedBy = "avdeling", fetch = FetchType.EAGER)
	private List<Ansatt> ansatte;

	
	public List<Ansatt> getAnsatte() {
		return ansatte;
	}

	public int getAvdelingID() {
		return avdelingID;
	}

	public void setAvdelingID(int avdelingID) {
		this.avdelingID = avdelingID;
	}

	public String getAvdNavn() {
		return avdNavn;
	}

	public void setAvdNavn(String avdNavn) {
		this.avdNavn = avdNavn;
	}
	
	public void setAvdelingsleder(int avdelingsleder) {
		this.avdelingsleder = avdelingsleder;
	}
	
	public String avdelingslederNavn(int id) {
		AnsattDAO ansdao = new AnsattDAO();
		Ansatt ans = ansdao.finnAnsattMedId(id);
	
		return ans.getFornavn() + " " + ans.getEtternavn();
	}
	
	public void skrivUt() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "Avdeling:\t" + avdNavn + "\nAvd.leder: \t" + avdelingslederNavn(avdelingsleder);
	}
	
	
}
