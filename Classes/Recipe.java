package classes;

import java.util.*;

public class Recipe {
	
	Map<Ingredient, Integer> optimal; //It's worth thinking about maybe making an optimal range private class instead of Integer
	Map<Ingredient, Integer> priority;
	
	// A recipe has x number of ingredients
	// Each recipe takes some amount of each ingredient, using optimal/inoptimal
	//    amounts impacts overall quality
	// Using 0 of a required ingredient results in a minimum overall score
	// Each ingredient has a quality, operation of quality times distance from optimal amount?
	
	// Example: Grilled Cheese
	//    -Bread 5Q 1S
	//    -Cheese 5Q 2S
	//    -Butter 0Q 1Tbs
	
	

}
