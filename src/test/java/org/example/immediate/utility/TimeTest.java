package org.example.immediate.utility;

import org.example.utility.Time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests unitaires de l'utilitaire de Temps (Time)")
class TimeTest {

	private Time time_null, time_zero, time_one, time_two, time_three;

	@BeforeEach
	void setUp() {
		time_zero = new Time(); // default constructor sets time to 0,0,0
		time_one = new Time(22, 42, 42);
		time_two = new Time(22, 42, 42);
		time_three = new Time(23, 59, 59);
	}

	@Test
	@DisplayName("Test de l'égalité entre objets Time")
	void testEquals() {
		assertNull(time_null, "The Time object is null.");
		assertNotEquals(time_null, time_zero, "The two Time objects are different.");
		assertEquals(time_one, time_one);
		assertNotSame(time_one, time_two, "The two Time objects are different.");
		assertEquals(time_one, time_two, "The two time values are equal");
	}

	@Test
	@DisplayName("Test de l'incrémentation (tickUp)")
	void testTickUp() {
		time_three.tickUp();
		assertEquals(time_three, time_zero);
	}

	@Test
	@DisplayName("Test de la décrémentation (tickDown)")
	void testTickDown() {
		assertTrue(time_zero.isZero());
		time_zero.tickDown();
		assertFalse(time_zero.isZero());
		assertEquals(time_three, time_zero);
	}

	@Test
	@DisplayName("Test de la vérification du temps à zéro")
	void testIsZero() {
		assertTrue(time_zero.isZero());
		time_zero.tickUp();
		assertFalse(time_zero.isZero());
	}
}
