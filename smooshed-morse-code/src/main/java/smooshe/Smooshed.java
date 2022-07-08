package smooshe;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import model.MorseCodeCount;
import util.FileReader;

/**
 * Converts words to smooshed morse codes
 */
public class Smooshed {
	private static final String MORSE_CODE = ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..";
	private static final String                 EMPTY_STRING = "";
	private static final String                 BLANK_STRING = " ";
	private static final String WORD_LIST_TXT = "wordList.txt";

	private final Map<Character, String> morseCodeMap = new HashMap<>();
	private final Map<String, String> wordMorseCodeMap = new HashMap<>();
	private final FileReader fileReader;

	public Smooshed( FileReader fileReader){
		this.fileReader = fileReader;
		createMorseCodeMapAndWords();
	}
	public String smoothe(String word) {
		String wordTobeConverted = Optional.ofNullable(word).filter(s-> !s.isEmpty()).filter(s -> !s.isBlank())
				.orElseThrow(() -> new IllegalArgumentException("Word cannot be null or empty"));

		StringBuilder stringBuilder = new StringBuilder();
		for (char letter : wordTobeConverted.toCharArray()){
			stringBuilder.append(morseCodeMap.getOrDefault(letter, EMPTY_STRING));
		}
		return stringBuilder.toString();
	}

	private void createMorseCodeMapAndWords(){
		String[] splitMorseCode = MORSE_CODE.split(BLANK_STRING);
		char charLetter = 'a';
		for (String code : splitMorseCode){
			morseCodeMap.put(charLetter++, code);
		}

		try {
			fileReader.readFile(WORD_LIST_TXT).forEach(word -> wordMorseCodeMap.put(word, smoothe(word)));
		} catch (URISyntaxException | IOException e) {
			throw new IllegalStateException("Could not found word list file to read", e);
		}
	}
	public int countDashes(String word) {
		int count = 0;
		for (char letter : word.toCharArray()){
			if(letter == '-'){
				count ++;
			}
		}
		return count;
	}
	public MorseCodeCount countDashesAndDots(String word) {
		int dashCountCount = 0;
		int dotCountCount = 0;
		for (char letter : word.toCharArray()){
			if(letter == '-'){
				dashCountCount ++;
			}else if(letter == '.'){
				dotCountCount ++;
			}
		}
		return new MorseCodeCount(dotCountCount, dashCountCount);
	}
	public Map<Character, String> getMorseCodeMap() {
		return morseCodeMap;
	}
	public Map<String, String> getWordMorseCodeMap() {
		return wordMorseCodeMap;
	}
}
