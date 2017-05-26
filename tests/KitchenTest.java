package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import classes.Kitchen;

public class KitchenTest {
	
	@Test
	public void testCreation() {
		@SuppressWarnings("unused")
		Kitchen testKitchen;
		try {
			testKitchen = new Kitchen(-1, 1);
			fail("Length should not be negative");
		} catch (IllegalArgumentException e) {}
		try {
			testKitchen = new Kitchen(0, 1);
			fail("Length should not be 0");
		} catch (IllegalArgumentException e) {}
		try {
			testKitchen = new Kitchen(1, -1);
			fail("Height should not be negative");
		} catch (IllegalArgumentException e) {}
		try {
			testKitchen = new Kitchen(1, 0);
			fail("Height should not be 0");
		} catch (IllegalArgumentException e) {}
		try {
			testKitchen = new Kitchen(1, 1);
		} catch (IllegalArgumentException e) {
			fail("1,1 is a legal kitchen dimension");
		}

	}
	Kitchen testKitchen = new Kitchen(10, 10);
	
	@Test
	public void testBlocking() {
		try {
			testKitchen.blockFloor(-1, 5, 5, 5);
			fail("Low x should not be negative");
		} catch (IllegalArgumentException e) {}
		try {
			testKitchen.blockFloor(6, 5, 5, 5);
			fail("X-Values should not be inverted");
		} catch (IllegalArgumentException e) {}
		try {
			testKitchen.blockFloor(5, 5, -1, 5);
			fail("Low y should not be negative");
		} catch (IllegalArgumentException e) {}
		try {
			testKitchen.blockFloor(5, 5, 6, 5);
			fail("Y-Values should not be inverted");
		} catch (IllegalArgumentException e) {}
		try {
			testKitchen.blockFloor(5, 10, 5, 5);
			fail("High x should not exceed max length - 1");
		} catch (IllegalArgumentException e) {}
		try {
			testKitchen.blockFloor(5, 5, 5, 10);
			fail("High y should not exceed max height - 1");
		} catch (IllegalArgumentException e) {}
		assertTrue(testKitchen.blockFloor(5, 5, 5, 5));
		assertFalse(testKitchen.blockFloor(3, 7, 3, 7));
		assertTrue(testKitchen.blockFloor(3, 4, 3, 4));
	}

}
