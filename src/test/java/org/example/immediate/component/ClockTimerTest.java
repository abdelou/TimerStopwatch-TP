package org.example.immediate.component;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.component.ClockTimer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tests unitaires isolés du Chronomètre (ClockTimer)")
class ClockTimerTest {

	private ClockTimer ct;

	@BeforeEach
	void setUp() {
		ct = new ClockTimer();
	}

	@Test
	@DisplayName("Test de démarrage avec un temps à zéro")
	void startInZeroTest() {
		ct.start();
		assertTrue(ct.getTime().isZero());
	}
}
