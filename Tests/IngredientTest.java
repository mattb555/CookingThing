package Tests;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import Classes.Ingredient;

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
}
