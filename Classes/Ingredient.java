package Classes;

import java.util.Set;
import java.util.HashSet;

public class Ingredient {
	protected String name;
	protected int quality;
	protected String[] suffix;
	protected double basePrice;
	protected Set<String> tags;
	
	public Ingredient(String name, int quality, String singular, String plural, double basePrice) {
		this.name = name;
		this.quality = quality;
		suffix = new String[2];
		suffix[0] = singular;
		suffix[1] = plural;
		this.basePrice = basePrice;
		tags = new HashSet<String>();
	}
	
	public String getName() {
		return name;
	}
	
	public int getQuality() {
		return quality;
	}
	
	public String getID() {
		return name + quality;
	}
	
	public String getSuffix(int amount) {
		if (amount == 1) {
			return suffix[0];
		} else if (amount > 1) {
			return suffix[1];
		} else {
			throw new IllegalArgumentException("amount must not be less than 0");
		}
	}
	
	public double getBasePrice() {
		return basePrice;
	}
	
	public double getModifiedPrice() {
		return basePrice + ((quality - 1) * (basePrice / 2));
	}
	
	public boolean addTag(String newTag) {
		if(tags.add(newTag)) {
			return true;
		}
		return false;
	}
	
	public boolean hasTag(String tag) {
		return tags.contains(tag);
	}
	
	public Set<String> getTags() {
		return tags;
	}
	
	public boolean hasTags(Set<String> tags) {
		return this.tags.containsAll(tags);
	}
}
