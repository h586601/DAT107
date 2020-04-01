package no.hvl.dat107;

import static org.junit.Assert.*;

import org.junit.Test;

import no.hvl.dat107.Main3CompleteCrud;
import no.hvl.dat107.Person;

public class CrudTest {

	@Test
	public void person1SkalHeteAtle() {
		Main3CompleteCrud crud = new Main3CompleteCrud();
		Person p = crud.retrievePerson(1001);
		assertEquals("Per Viskeler", p.getNavn());
	}
	
	

}
