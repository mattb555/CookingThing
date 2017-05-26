package tests;
import org.junit.Test;

import classes.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

public class IngredientTest {
	String name = "Bread";
	int quality = 5;
	String singular = "Slice";
	String plural = "Slices";
	double basePrice = 10;
	Ingredient ingredient = new Ingredient(name, quality, singular, plural, basePrice);
	
	@Test
	public void testName() {
		assertEquals("Bread", ingredient.getName());
	}
	
	@Test
	public void testQuality() {
		assertEquals(5, ingredient.getQuality());
	}
	
	@Test
	public void testID() {
		assertEquals("Bread5", ingredient.getID());
	}
	
	@Test
	public void testSuffixes() {
		assertEquals("Slice", ingredient.getSuffix(1));
		assertEquals("Slices", ingredient.getSuffix(2));
		try {
			ingredient.getSuffix(-1);
			fail("Should throw exception");
		} catch (IllegalArgumentException e) {
			// Proper behavior
		}
	}
	
	@Test
	public void testBasePrice() {
		assertEquals(10.0, ingredient.getBasePrice(), 0);
	}
	
	@Test
	public void testModifiedPrice() {
		assertEquals(30.0, ingredient.getModifiedPrice(), 0);
	}
	
	@Test
	public void testSetTags() {
		assertEquals(true, ingredient.addTag("Grain"));
		assertEquals(false, ingredient.addTag("Grain"));
	}
	
	@Test
	public void testHasTag() {
		ingredient.addTag("Grain");
		assertEquals(true, ingredient.hasTag("Grain"));
	}
	
	@Test
	public void testHasTags() {
		Set<String> tags = new HashSet<String>();
		tags.add("Grain");
		tags.add("Lunch");
		tags.add("Basic");
		ingredient.addTag("Grain");
		ingredient.addTag("Lunch");
		ingredient.addTag("Basic");
		assertEquals(true, ingredient.hasTags(tags));
		tags.add("Failure");
		assertEquals(false, ingredient.hasTags(tags));
	}
	
	@Test
	public void testGetTags() {
		Set<String> tags = new HashSet<String>();
		tags.add("Grain");
		tags.add("Lunch");
		tags.add("Basic");
		ingredient.addTag("Grain");
		ingredient.addTag("Lunch");
		ingredient.addTag("Basic");
		Set<String> tagsTwo = ingredient.getTags();
		assertEquals(tags, tagsTwo);
	}
	
	@Test
	public void testRandomQuality() {
		try {
			Ingredient.makeRandom(name, 0, 0, singular, plural, -1, 0);
			fail("Negative price not caught");
		} catch(IllegalArgumentException e) {}
		try {
			Ingredient.makeRandom(name, 0, 0, singular, plural, 1, 0);
			fail("Inverted price not caught");
		} catch(IllegalArgumentException e) {}
		try {
			Ingredient.makeRandom(name, -1, 0, singular, plural, 0, 0);
			fail("Negative quality not caught");
		} catch(IllegalArgumentException e) {}
		try {
			Ingredient.makeRandom(name, 1, 0, singular, plural, -1, 0);
			fail("Inverted quality not caught");
		} catch(IllegalArgumentException e) {}
		Ingredient random;
		double sumQuality = 0.0;
		double sumPrice = 0.0;
		for (int j = 0; j < 3; j++) {
			for (int i = 1; i < 50000; i++) {
				random = Ingredient.makeRandom(name, j, 5, singular, plural, (j + 2) * 1.00, 6.00 + (j * .5));
				sumQuality += random.getQuality();
				sumPrice += random.getBasePrice();
			}
			if (j == 0) {
				assertEquals(sumQuality / 50000, 2.5, .05);
				assertEquals(sumPrice / 50000, 4.0, .05);
			} else if (j == 1) {
				assertEquals(sumQuality / 50000, 3.0, .05);
				assertEquals(sumPrice / 50000, 4.75, .05);
			} else {
				assertEquals(sumQuality / 50000, 3.5, .05);
				assertEquals(sumPrice / 50000, 5.5, .05);
			}
			sumQuality = 0.0;
			sumPrice = 0.0;
		}
	}
}
