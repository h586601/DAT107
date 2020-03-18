package no.hvl.dat107;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GreeterTest {

	@Test
	void sayHelloShouldSayHallo() {
		assertTrue(Greeter.sayHallo().contains("Hello"));
	}

}
