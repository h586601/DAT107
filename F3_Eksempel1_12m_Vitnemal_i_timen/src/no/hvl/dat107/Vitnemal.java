package no.hvl.dat107;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "forelesning3")
public class Vitnemal {

	@Id private int studNr;
	private LocalDate studiestart;
	private LocalDate studieslutt;
	
	@OneToMany(mappedBy = "vitnemal", fetch = FetchType.EAGER)
	private List<Karakter> karakterer;
	
	public void addKarakter(Karakter k) {
		karakterer.add(k);
	}
	
	public void skrivUt() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "Vitnemal [studNr=" + studNr + ", studiestart=" + studiestart + ", studieslutt=" + studieslutt
				+ ", karakterer=" + karakterer + "]";
	}
	
	
}