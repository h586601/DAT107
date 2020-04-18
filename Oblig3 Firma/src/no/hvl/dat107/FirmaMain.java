package no.hvl.dat107;

import java.util.Scanner;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.dao.AvdelingDAO;
import no.hvl.dat107.dao.ProsjektDAO;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.Prosjektdeltagelse;

import static java.lang.Integer.parseInt;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FirmaMain {

	private static final String TYPE_BRUKERNAVN = "(ABnr)";

	public static void main(String[] args) {
		
		AvdelingDAO avd = new AvdelingDAO();
		Avdeling avdeling = avd.finnAvdelingMedId(1);
		System.out.println(avdeling);
		
		System.out.println("MENY\n");
//		start();
		
		
	}

	public static void start() {

		AnsattDAO ansattdao = new AnsattDAO();

		// User interface
		Scanner leser = new Scanner(System.in);
		System.out.println("Hva �nsker du? \n1: Hente ut \n2: Legge inn \n3: Oppdatere");
		int hovedvalg = parseInt(leser.nextLine());

		switch (hovedvalg) {
		case 1:
			System.out.println("Hente ut alle? JA/NEI");
			if (leser.nextLine().toUpperCase().equals("JA")) {
				ansattdao.utskrivAlleAnsatte();

			} else {
				System.out.println("Hva vil du s�ke med? \n1: AnsattID \n2: Brukernavn");
				int nestevalg = parseInt(leser.nextLine());
				if (nestevalg == 1) {
					System.out.println("Skriv inn AnsattID");
					int id = parseInt(leser.nextLine());
					Ansatt ansattMedId = ansattdao.finnAnsattMedId(id);
					System.out.println("S�KERESULTAT");
					ansattMedId.skrivUt();
					System.out.println();
				} else if (nestevalg == 2) {
					System.out.println("Skriv inn brukernavn " + TYPE_BRUKERNAVN);
					String brukernavn = leser.nextLine().toUpperCase();
					Ansatt ansatt = ansattdao.finnAnsattMedBrukernavn(brukernavn);
					System.out.println("S�KERESULTAT");
					ansatt.skrivUt();
					System.out.println();
				}
			}
			break;
		case 2:
			System.out.println("Skriv inn f�lgende om ny ansatt");
			System.out.println("\nBrukernavn " + TYPE_BRUKERNAVN);
			String bruker = leser.nextLine().toUpperCase();
			System.out.println("\nFornavn");
			String fnavn = leser.nextLine();
			System.out.println("\nEtternavn");
			String enavn = leser.nextLine();
			System.out.println("\nAnsettelsesdato (dd/MM/yyyy)");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			String date = leser.nextLine();
			LocalDate adato = LocalDate.parse(date, formatter);
			System.out.println("\nStilling");
			String startstilling = leser.nextLine();
			System.out.println("\nM�nedsl�nn");
			BigDecimal startl�nn = new BigDecimal(leser.nextLine());
			System.out.println("\nAvdID");
			int avdID = parseInt(leser.nextLine());

			ansattdao.leggInnNy(bruker, fnavn, enavn, adato, startstilling, startl�nn, avdID);
//
			break;
		case 3:
			System.out.println("Hva vil du oppdatere? \n1: Ansatt sin stilling \n2: Ansatt sin l�nn");
			int oppdatering = parseInt(leser.nextLine());
			System.out.println("Skriv inn ansattID");
			Ansatt ansatt = ansattdao.finnAnsattMedId(parseInt(leser.nextLine()));
			if (oppdatering == 1) {
				System.out.println("Skriv inn ny stilling");
				String stilling = leser.nextLine();
				ansatt.setStilling(stilling);
				ansattdao.oppdater(ansatt);
			} else if (oppdatering == 2) {
				System.out.println("Skriv inn ny l�nn");
				BigDecimal l�nn = new BigDecimal(leser.nextLine());
				ansatt.setMndsL�nn(l�nn);
				ansattdao.oppdater(ansatt);
			}
		}

		leser.close();
	}
}
