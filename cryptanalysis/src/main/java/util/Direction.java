package util;

public enum Direction {
	LEFT, RIGHT;

	public static Direction getDirection(int directionInput) {
		if (directionInput == 0) {
			return Direction.LEFT;
		}
		if (directionInput == 1) {
			return Direction.RIGHT;
		}
		throw new IllegalArgumentException("Invalid direction input. Direction values are 0 and 1. 0 for left 1 for right.");
	}
}
