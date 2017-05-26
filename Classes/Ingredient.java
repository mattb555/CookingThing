package classes;

import java.util.Set;
import java.util.HashSet;
import java.util.Random;

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
	
	public static Ingredient makeRandom(String name, int lowQuality, int highQuality, String singular, String plural, double lowPrice, double highPrice) {
		if (lowQuality > highQuality || lowPrice > highPrice || lowQuality < 0 || lowPrice < 0) {
			throw new IllegalArgumentException("Bad Values");
		}
		Random r = new Random();
		int qual = r.nextInt(highQuality - lowQuality + 1) + lowQuality;
		double price = (r.nextInt((int)(highPrice * 100) - (int)(lowPrice * 100)) + lowPrice * 100 * 1.0) / 100.0;
		return new Ingredient(name, qual, singular, plural, price);
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
