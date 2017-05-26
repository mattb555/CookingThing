package tests;
import org.junit.Test;

import classes.Ingredient;
import classes.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SupplierTests {
	
	String name = "Bread Mart";
	
	Supplier supplier = new Supplier(name);
	
	@Test
	public void testName() {
		assertEquals("Bread Mart", supplier.getName());
	}
	
	@Test
	public void testAddTags() {
		assertTrue(supplier.addTag("Oil"));
		assertFalse(supplier.addTag("Oil"));
	}
	
	Ingredient ingredient = new Ingredient("Bread", 5, "Slice", "Slices", 10);
	Ingredient ingredient2 = new Ingredient("Rice", 5, "Grain", "Grains", 10);
	
	@Test
	public void testAddIngredient() {
		ingredient.addTag("Grain");
		supplier.addTag("Condiment");
		assertFalse(supplier.addIngredient(ingredient));
		supplier.addTag("Grain");
		assertTrue(supplier.addIngredient(ingredient));
	}
	
	@Test
	public void testPopulateStoreTotalItemsSingleItem() {
		ingredient.addTag("Grain");
		supplier.addTag("Grain");
		supplier.addIngredient(ingredient);
		supplier.populateStoreTotalItems(20);
		assertEquals(20, supplier.getItemTotal(ingredient));
	}
	
	@Test
	public void testPopulateStoreTotalItemsMultipleItem() {
		ingredient.addTag("Grain");
		ingredient2.addTag("Grain");
		supplier.addTag("Grain");
		supplier.addIngredient(ingredient);
		supplier.addIngredient(ingredient2);
		supplier.populateStoreTotalItems(20);
		assertTrue(supplier.getItemTotal(ingredient) <= 20);
		assertTrue(supplier.getItemTotal(ingredient2) <= 20);
		assertTrue((supplier.getItemTotal(ingredient) + supplier.getItemTotal(ingredient2)) == 20);
	}
	
	@Test
	public void testPopulateStoreSpecificItem() {
		ingredient.addTag("Grain");
		supplier.addTag("Grain");
		supplier.addIngredient(ingredient);
		assertTrue(supplier.populateStoreSpecificItem(ingredient, 5));
		assertEquals(5, supplier.getItemTotal(ingredient));
		assertFalse(supplier.populateStoreSpecificItem(ingredient, -5));
		assertTrue(supplier.populateStoreSpecificItem(ingredient, 5));
		assertEquals(10, supplier.getItemTotal(ingredient));
	}
	
	@Test
	public void testDepleteStore() {
		ingredient.addTag("Grain");
		supplier.addTag("Grain");
		supplier.addIngredient(ingredient);
		supplier.populateStoreSpecificItem(ingredient, 10);
		assertTrue(supplier.depleteStore(ingredient, 5));
		assertEquals(5, supplier.getItemTotal(ingredient));
		assertFalse(supplier.depleteStore(ingredient, 6));
	}
}
