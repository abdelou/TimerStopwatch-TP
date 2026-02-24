package org.example.immediate.utility;

import org.example.utility.PointedArray;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tests du Tableau Pointé générique (PointedArray)")
class PointedArrayTest {

	private PointedArray<Integer> array;

	@BeforeEach
	void setUp() {
		Integer[] intArray = { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2) };
		array = new PointedArray<Integer>(intArray);
	}

	@Test
	@DisplayName("Exception : Index négatif pour le pointeur")
	void setPointerException1() {
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> array.setPointer(-1));
	}

	@Test
	@DisplayName("Exception : Index hors limites pour le pointeur")
	void setPointerException2() {
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> array.setPointer(3));
	}

	@Test
	@DisplayName("Exception : Récupération à un index négatif")
	void getException1() {
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
	}

	@Test
	@DisplayName("Exception : Récupération à un index hors limites")
	void getException2() {
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> array.get(3));
	}

	@Test
	@DisplayName("Test de récupération de l'élément pointé")
	void getPointedItemTest() {
		array.setPointer(2);
		assertEquals(Integer.valueOf(2), array.getPointedItem());
		array.setPointer(1);
		assertEquals(Integer.valueOf(1), array.getPointedItem());
	}

}
