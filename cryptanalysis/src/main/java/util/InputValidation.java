package util;

public class InputValidation {
	private static final String PUNCTUATION_REGEX = "\\p{Punct}";

	public String plainText(String string) {
		return string.replaceAll(PUNCTUATION_REGEX, "").toUpperCase();
	}

	public void validateShift(int shiftNumber) {
		if (shiftNumber >= 1 && shiftNumber <= 25) {
			return;
		}
		throw new IllegalArgumentException("Shift number should be between 1 and 25");
	}
}
