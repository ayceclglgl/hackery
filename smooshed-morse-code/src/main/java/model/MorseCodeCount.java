package model;

/**
 * Represents dot and dash counts of a word
 */
public class MorseCodeCount {
	private final int dotCount;
	private final int dashCount;

	public MorseCodeCount(int dotCount, int dashCount) {
		this.dotCount = dotCount;
		this.dashCount = dashCount;
	}

	public int getDotCount() {
		return dotCount;
	}

	public int getDashCount() {
		return dashCount;
	}
}
