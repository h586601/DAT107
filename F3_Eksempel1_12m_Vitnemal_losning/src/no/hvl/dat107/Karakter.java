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
@Table(schema = "forelesning3", name = "karakter")
public class Karakter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int karNr;
	
	private String emnekode;
	private LocalDate eksDato;
	private String bokstav;
	
	@ManyToOne
	@JoinColumn(name = "studNr", referencedColumnName = "studNr")
	private Vitnemal vitnemal;
	
	

	@Override
	public String toString() {
		return "   Karakter [karNr=" + karNr + ", emnekode=" + emnekode 
				+ ", eksDato=" + eksDato + ", bokstav=" + bokstav
				+ ", studNr=" + vitnemal.getStudNr() + "]";
	}

	public int getKarNr() {
		return karNr;
	}

	public String getEmnekode() {
		return emnekode;
	}

	public void setEmnekode(String emnekode) {
		this.emnekode = emnekode;
	}

	public LocalDate getEksDato() {
		return eksDato;
	}

	public void setEksDato(LocalDate eksDato) {
		this.eksDato = eksDato;
	}

	public String getBokstav() {
		return bokstav;
	}

	public void setBokstav(String bokstav) {
		this.bokstav = bokstav;
	}

	public Vitnemal getVitnemal() {
		return vitnemal;
	}

	public void setVitnemal(Vitnemal vitnemal) {
		this.vitnemal = vitnemal;
	}

	
	
}
