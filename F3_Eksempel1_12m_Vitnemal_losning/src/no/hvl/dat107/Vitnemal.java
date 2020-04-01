package no.hvl.dat107;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "forelesning3", name = "vitnemal")
public class Vitnemal {
	
	@Id private int studNr;
	private LocalDate studiestart;
	private LocalDate studieslutt;
	
	@OneToMany(mappedBy = "vitnemal", fetch = FetchType.EAGER)
	private List<Karakter> karakterer;

	public void leggTilKarakter(Karakter kar) {
		karakterer.add(kar);
	}
	
	public void skrivUt() {
		System.out.println(this);
		karakterer.forEach(System.out::println);
	}
	
	@Override
	public String toString() {
		return "Vitnemal [studNr=" + studNr + ", studiestart=" + studiestart 
				+ ", studieslutt=" + studieslutt
				+ ", #karakterer=" + karakterer.size() + "]";
	}

	public int getStudNr() {
		return studNr;
	}
	
	

	

}
