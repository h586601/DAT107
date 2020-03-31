package no.hvl.dat107;

import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		
		VitnemalDAO vitnemalDAO = new VitnemalDAO();
		
		Vitnemal vitnemal = vitnemalDAO.finnVitnemalForStudent(123456);
		
		System.out.println("\n### Vitnemål for student 123456:");
		vitnemal.skrivUt();
		
		//Denne kan kun kjøres en gang. Deretter stopper den på
		//UNIQUE-contrainten. Burde vært håndtert. ...
		//For testing kan emnekode endres for hver kjøring.
		Karakter nyKarakter = vitnemalDAO.registrerKarakterForStudent(
				123456, "DAT115", LocalDate.of(2019, 03, 26), "A");
		
		System.out.println("\n### Legger inn en ny karakter:");
		System.out.println(nyKarakter);
		
		vitnemal = vitnemalDAO.finnVitnemalForStudent(123456);
		
		System.out.println("\n### Vitnemål for student 123456:");
		vitnemal.skrivUt();
	}

}
