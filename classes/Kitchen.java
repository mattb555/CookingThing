package classes;

public class Kitchen {
	char[][] floor; //characters will likely reflect equipment taking up the kitchen floor
	
	public Kitchen(int length, int height) {
		if (length <= 0 || height <= 0) {
			throw new IllegalArgumentException("Bad Values");
		}
		floor = new char[length][height];
	}
	
	/**
	 * Sculpting method to create kitchens that aren't perfect rectangles
	 * Takes a rectangle of points and blocks all points inside that rectangle
	 * Coordinates are equivalent to array coordinates
	 * If there is some failure, then none of the floor should end up blocked
	 * 	
	 * @param lowX	The left-most x value
	 * @param highX	The right-most x value
	 * @param lowY	The top-most y value
	 * @param highY	The bottom-most y value
	 * @return	False if the block failed due to existing blockages, True if area successfully blocked
	 * @throws	IllegalArgumentException is low inputs are negative or if high inputs are less than lows or if highs are higher than actual max values
	 */
	public boolean blockFloor(int lowX, int highX, int lowY, int highY) {
		if (lowX < 0 || lowY < 0 || lowY > highY || lowX > highX || highX > floor.length - 1 || highY > floor[0].length - 1) {
			throw new IllegalArgumentException("Bad Values");
		}
		for (int x = lowX; x <= highX; x++) {
			for (int y = lowY; y <= highY; y++) {
				if (floor[x][y] != 0) {
					return false;
				}
			}
		}
		for (int x = lowX; x <= highX; x++) {
			for (int y = lowY; y <= highY; y++) {
				floor[x][y] = 'x';
			}
		}
		return true;
	}

}
