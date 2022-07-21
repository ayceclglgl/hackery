package smooshe;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import model.MorseCode;
import util.FileReader;

/**
 * All operations to convert words to smooshed morse codes
 */
public class Smooshed {
	private static final String MORSE_CODE    = ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..";
	private static final String WORD_LIST_TXT = "wordList.txt";
	private static final char   CHAR_DASH     = '-';
	private static final char   CHAR_DOT      = '.';
	private static final char   CHAR_LETTER_A = 'a';

	private final FileReader             fileReader;
	private final Map<String, String>    morseCodeWords;
	private final Map<Character, String> morseCodeAlphabet;

	public Smooshed(FileReader fileReader) {
		this.fileReader = fileReader;
		morseCodeWords = new HashMap<>();
		morseCodeAlphabet = new HashMap<>();
		createMorseCodeAlphabet();
		createMorseCodeWords();
	}

	/**
	 * Converts words to smooshed morse codes
	 *
	 * @param word - word to be converted
	 * @return - morse code representation of the given word
	 * @throws IllegalArgumentException - if word is null or blank
	 */
	public String smooshe(String word) {
		String wordTobeConverted = Optional.ofNullable(word).filter(s -> !s.isEmpty()).filter(s -> !s.isBlank())
				.orElseThrow(() -> new IllegalArgumentException("Word cannot be null or empty"));

		StringBuilder stringBuilder = new StringBuilder();
		for (char letter : wordTobeConverted.toCharArray()) {
			stringBuilder.append(morseCodeAlphabet.getOrDefault(letter, StringUtils.EMPTY));
		}
		return stringBuilder.toString();
	}

	/**
	 * Counts dashes in a given morse code word
	 *
	 * @param word - morse code word
	 * @return - dash count
	 */
	public int countDashes(String word) {
		return (int) word.chars().filter(c -> c == CHAR_DASH).count();
	}

	/**
	 * Counts dashes and dots  in a given morse code word
	 *
	 * @param word -  morse code word
	 * @return - MorseCode object which holds dash and dot counts
	 */
	public MorseCode countDashesAndDots(String word) {
		int dashCount = (int) word.chars().filter(c -> c == CHAR_DASH).count();
		int dotCount = (int) word.chars().filter(c -> c == CHAR_DOT).count();
		return new MorseCode(dotCount, dashCount);
	}

	public Map<Character, String> getMorseCodeAlphabet() {
		return morseCodeAlphabet;
	}

	public Map<String, String> getMorseCodeWords() {
		return morseCodeWords;
	}

	private void createMorseCodeAlphabet() {
		String[] splitMorseCode = MORSE_CODE.split(StringUtils.SPACE);
		char charLetter = CHAR_LETTER_A;
		for (String code : splitMorseCode) {
			morseCodeAlphabet.put(charLetter++, code);
		}
	}

	private void createMorseCodeWords() {
		try {
			fileReader.readFile(WORD_LIST_TXT).forEach(word -> morseCodeWords.put(word, smooshe(word)));
		} catch (URISyntaxException | IOException e) {
			throw new IllegalStateException("Could not found word list file to read", e);
		}
	}
}
