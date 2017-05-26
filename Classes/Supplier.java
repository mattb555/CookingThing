package classes;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Supplier {
	
	protected String name;
	protected Set<String> tags;
	protected Set<Ingredient> ingredients;
	protected Map<Ingredient, Integer> storeFront;
	
	public Supplier(String name) {
		this.name = name;
		tags = new HashSet<String>();
		ingredients = new HashSet<Ingredient>();
		storeFront = new HashMap<Ingredient, Integer>();
	}
	
	public String getName() {
		return name;
	}
	
	public boolean addTag(String newTag) {
		if(tags.add(newTag)) {
			return true;
		}
		return false;
	}
	
	public boolean addIngredient(Ingredient ingredient) {
		Set<String> ingredientTags = ingredient.getTags();
		for (String tag : tags) {
			if(ingredientTags.contains(tag)) {
				ingredients.add(ingredient);
				return true;
			}
		}
		return false;
	}
	
	public int getItemTotal(Ingredient i) {
		return storeFront.get(i);
	}
	
	public Set<Ingredient> getItemMenu() {
		return storeFront.keySet();
	}
	
	public boolean populateStoreTotalItems(int x) {
		if (x <= 0) {
			return false;
		}
		Random r = new Random();
		Object[] iArray = ingredients.toArray();
		for (int i = 0; i < x; i++) {
			int item = r.nextInt(iArray.length);
			if (!storeFront.containsKey(iArray[item])) {
				storeFront.put((Ingredient)iArray[item], 1);
			} else {
				storeFront.put((Ingredient)iArray[item], storeFront.get((Ingredient)iArray[item]) + 1);
			}
		}
		return true;
	}
	
	public boolean populateStoreSpecificItem(Ingredient i, int x) {
		if (!ingredients.contains(i) || x <= 0) {
			return false;
		}
		if (!storeFront.containsKey(i)) {
			storeFront.put(i, x);
		} else {
			storeFront.put(i, storeFront.get(i) + x);
		}
		return true;
	}
	
	public boolean depleteStore(Ingredient i, int x) {
		if (!storeFront.containsKey(i) || x <= 0 || x > storeFront.get(i)) {
			return false;
		}
		storeFront.put(i, storeFront.get(i) - x);
		return true;
	}
}
