package util;

import java.util.Random;
import java.util.stream.IntStream;

public class Generator {
	private static final String ALPHABET_WITH_SPACE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
	private final        Random random;

	public Generator(Random random) {
		this.random = random;
	}

	public char generateRandomCharacter() {
		return ALPHABET_WITH_SPACE.charAt(random.nextInt(ALPHABET_WITH_SPACE.length()));
	}

	public char generateRandomCharacterWithProbability(double probability, char defaultCharacter) {
		if (random.nextDouble() <= probability) {
			return generateRandomCharacter();
		}
		return defaultCharacter;
	}

	public String generateRandomString(int stringLength) {
		StringBuilder builder = new StringBuilder();
		IntStream.range(0, stringLength).forEach(i -> builder.append(generateRandomCharacter()));
		return builder.toString();
	}
}
