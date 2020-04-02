package no.hvl.dat107;

import java.math.BigDecimal;
import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(schema = "firma")
public class Ansatt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ansattID;
	
	@Id private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansDato;
	private String stilling;
	private BigDecimal mndsL�nn;
	
	
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
	public BigDecimal getMndsL�nn() {
		return mndsL�nn;
	}
	public void setMndsL�nn(BigDecimal mndsL�nn) {
		this.mndsL�nn = mndsL�nn;
	}
	
	public void skrivUt() {
		System.out.println(this);
	}
}
