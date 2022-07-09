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

	private final FileReader fileReader;

	private final Map<Character, String> morseCodeMap;
	private final Map<String, String>    wordMorseCodeMap;

	public Smooshed(FileReader fileReader) {
		this.fileReader = fileReader;
		morseCodeMap = new HashMap<>();
		wordMorseCodeMap = new HashMap<>();
		createMorseCodeMap();
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
			stringBuilder.append(morseCodeMap.getOrDefault(letter, StringUtils.EMPTY));
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
		int count = 0;
		for (char letter : word.toCharArray()) {
			if (letter == '-') {
				count++;
			}
		}
		return count;
	}

	/**
	 * Counts dashes and dots  in a given morse code word
	 *
	 * @param word -  morse code word
	 * @return - MorseCode object which holds dash and dot counts
	 */
	public MorseCode countDashesAndDots(String word) {
		int dashCount = 0;
		int dotCount = 0;
		for (char letter : word.toCharArray()) {
			if (letter == '-') {
				dashCount++;
			} else if (letter == '.') {
				dotCount++;
			}
		}
		return new MorseCode(dotCount, dashCount);
	}

	private void createMorseCodeMap() {
		String[] splitMorseCode = MORSE_CODE.split(StringUtils.SPACE);
		char charLetter = 'a';
		for (String code : splitMorseCode) {
			morseCodeMap.put(charLetter++, code);
		}
	}

	private void createMorseCodeWords() {
		try {
			fileReader.readFile(WORD_LIST_TXT).forEach(word -> wordMorseCodeMap.put(word, smooshe(word)));
		} catch (URISyntaxException | IOException e) {
			throw new IllegalStateException("Could not found word list file to read", e);
		}
	}

	public Map<Character, String> getMorseCodeMap() {
		return morseCodeMap;
	}

	public Map<String, String> getWordMorseCodeMap() {
		return wordMorseCodeMap;
	}
}
