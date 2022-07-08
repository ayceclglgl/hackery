package cypher;

import java.util.Optional;

import util.Direction;
import util.InputValidation;

public class CaesarCypher implements Cypher {

	private static final String EMPTY_STRING        = "";
	private static final int    ALPHABET_MOD_FACTOR = 26;
	private static final char   EMPTY_CHAR_LETTER   = ' ';
	private static final char   CHAR_A              = 'A';

	private final InputValidation inputValidator;

	public CaesarCypher(InputValidation inputValidator) {
		this.inputValidator = inputValidator;
	}

	@Override
	public String encrypt(String input, Direction direction, int shift) {
		inputValidator.validateShift(shift);
		String text = Optional.ofNullable(input).map(inputValidator::plainText).orElse(EMPTY_STRING);
		if (text.isEmpty()) {
			return text;
		}

		if (direction == Direction.LEFT) {
			return encryptLeft(text, shift);

		}
		if (direction == Direction.RIGHT) {
			return encryptRight(text, shift);
		}
		throw new IllegalArgumentException("Could not encrypt given string, please check direction for shifting");
	}

	private String encryptRight(String input, int shift) {
		StringBuilder stringBuilder = new StringBuilder();
		for (char character : input.toCharArray()) {
			if (character == EMPTY_CHAR_LETTER) {
				stringBuilder.append(EMPTY_CHAR_LETTER);
			} else {
				int originalAlphabetPosition = character - CHAR_A;
				int newAlphabetPosition = (originalAlphabetPosition + shift) % ALPHABET_MOD_FACTOR;
				char newCharacter = (char) (newAlphabetPosition + CHAR_A);
				stringBuilder.append(newCharacter);
			}
		}
		return stringBuilder.toString();
	}

	private String encryptLeft(String input, int shift) {
		StringBuilder stringBuilder = new StringBuilder();
		for (char charLetter : input.toCharArray()) {
			if (charLetter == EMPTY_CHAR_LETTER) {
				stringBuilder.append(EMPTY_CHAR_LETTER);
			} else {
				int alphabetPosition = charLetter - CHAR_A;
				// Can be negative number, add mod factor until get it positive
				int position = alphabetPosition - shift;
				int positivePosition = position;
				if (position < 0) {
					positivePosition = getPositiveByModFactor(position);
				}
				int newAlphabetPosition = positivePosition % ALPHABET_MOD_FACTOR;
				char newCharLetter = (char) (newAlphabetPosition + CHAR_A);
				stringBuilder.append(newCharLetter);
			}
		}
		return stringBuilder.toString();
	}

	@Override
	public String decrypt(String input, Direction direction, int shift) {
		inputValidator.validateShift(shift);
		String text = Optional.ofNullable(input).orElse(EMPTY_STRING);
		if (text.isEmpty()) {
			return text;
		}

		if (direction == Direction.LEFT) {
			return decryptLeft(text, shift);

		}
		if (direction == Direction.RIGHT) {
			return decryptRight(text, shift);
		}
		throw new IllegalArgumentException("Could not encrypt given string, please check direction for shifting");
	}

	private String decryptLeft(String input, int shift) {
		StringBuilder stringBuilder = new StringBuilder();
		for (char charLetter : input.toCharArray()) {
			if (charLetter == EMPTY_CHAR_LETTER) {
				stringBuilder.append(EMPTY_CHAR_LETTER);
			} else {
				int alphabetPosition = charLetter - CHAR_A;
				int newAlphabetPosition = (alphabetPosition + shift) % ALPHABET_MOD_FACTOR;
				char newCharLetter = (char) (newAlphabetPosition + CHAR_A);
				stringBuilder.append(newCharLetter);
			}
		}
		return stringBuilder.toString();
	}

	private String decryptRight(String input, int shift) {
		StringBuilder stringBuilder = new StringBuilder();
		for (char charLetter : input.toCharArray()) {
			if (charLetter == EMPTY_CHAR_LETTER) {
				stringBuilder.append(EMPTY_CHAR_LETTER);
			} else {
				// can be negative, add mod factor until get it positive
				int alphabetPosition = charLetter - CHAR_A;
				int newAlphabetPosition = alphabetPosition - shift;
				int realAlphabetPosition = getPositiveByModFactor(newAlphabetPosition) % 26;
				char newCharLetter = (char) (realAlphabetPosition + CHAR_A);
				stringBuilder.append(newCharLetter);
			}
		}
		return stringBuilder.toString();
	}

	private int getPositiveByModFactor(int position) {
		while (position < 0) {
			position += CaesarCypher.ALPHABET_MOD_FACTOR;
		}
		return position;
	}

}
