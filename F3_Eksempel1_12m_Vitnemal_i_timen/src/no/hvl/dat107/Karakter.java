package no.hvl.dat107;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "forelesning3")
public class Karakter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int karNr;
	private String emnekode;
	private LocalDate eksDato;
	private String bokstav;
	
	@ManyToOne
	@JoinColumn(name = "StudNr", referencedColumnName = "StudNr")
	private Vitnemal vitnemal;
	
	public Karakter() {}

	public Karakter(String emnekode, LocalDate eksDato, String bokstav, Vitnemal vitnemal) {
		this.emnekode = emnekode;
		this.eksDato = eksDato;
		this.bokstav = bokstav;
		this.vitnemal = vitnemal;
	}

	@Override
	public String toString() {
		return "Karakter [karNr=" + karNr + ", emnekode=" + emnekode + ", eksDato=" + eksDato + ", bokstav=" + bokstav
				+ "]";
	}
	
	
}
