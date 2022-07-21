package smooshe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import model.MorseCode;

/**
 * Calculates and serves five use cases
 */
public class SmooshedFinder {
	private static final List<Character> CHARACTER_LIST = List.of('.', '-');
	private static final String          DASH           = "-";
	private final        Smooshed        smooshed;

	public SmooshedFinder(Smooshed smooshed) {
		this.smooshed = smooshed;
	}

	/**
	 * Finds different words which their smooshed morse is the same.
	 *
	 * @param count - count of words
	 * @return - words which its morse code is same for {@param count} different words , empty string if the word is not found
	 */
	public List<String> getWordsHavingSameSmooshedMorseCode(int count) {
		List<String> wordList = new ArrayList<>();
		Map<String, List<String>> resultMap = groupMorseCodeWords(smooshed.getMorseCodeWords());
		for (Map.Entry<String, List<String>> entry : resultMap.entrySet()) {
			if (entry.getValue().size() == count) {
				wordList.add(entry.getKey());
			}
		}
		return wordList;
	}

	/**
	 * Finds words with a given dash count in a row.
	 *
	 * @param dashCount - count of dash in a word
	 * @return - words which has {@param dashCount} dashes, empty list if words is not found
	 */
	public List<String> getMorseCodeWords(int dashCount) {
		List<String> wordList = new ArrayList<>();
		String dashes = DASH.repeat(Math.max(0, dashCount));
		for (Map.Entry<String, String> entry : smooshed.getMorseCodeWords().entrySet()) {
			if (entry.getValue().contains(dashes)) {
				wordList.add(entry.getKey());
			}
		}
		return wordList;
	}

	/**
	 * Finds perfectly balanced words based on letter count.
	 * Call a word perfectly balanced if its code has the same number of dots as dashes.
	 * counterdemonstrations is one of two 21-letter words that's perfectly balanced.
	 *
	 * @param letterCount - count of letter in a word
	 * @return - perfectly balanced words, empty list if there is no balanced word
	 */
	public List<String> getPerfectlyBalancedWords(int letterCount) {
		List<String> perfectlyBalancedWord = new ArrayList<>();
		for (Map.Entry<String, String> entry : smooshed.getMorseCodeWords().entrySet()) {
			if (entry.getKey().length() == letterCount) {
				MorseCode morseCode = smooshed.countDashesAndDots(entry.getValue());
				if (morseCode.getDotCount() == morseCode.getDashCount()) {
					perfectlyBalancedWord.add(entry.getKey());
				}
			}
		}
		return perfectlyBalancedWord;
	}

	/**
	 * Protectorate is 12 letters long and encodes to .--..-.----.-.-.----.-..--.,which is a palindrome (i.e. the string is the same when reversed).
	 * Find the only 13-letter word that encodes to a palindrome.
	 *
	 * @param letterCount - count of letter in a word
	 * @return a palindrome word in a given letter count. If there is no word is find, returns empty string
	 */
	public String getPalindromeWord(int letterCount) {
		for (Map.Entry<String, String> entry : smooshed.getMorseCodeWords().entrySet()) {
			if (entry.getKey().length() == letterCount) {
				String reverse = new StringBuilder(entry.getValue()).reverse().toString();
				if (entry.getValue().equals(reverse)) {
					return entry.getKey();
				}
			}
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Finds {@param characterCount}-letter words and subtracts words given in the list.
	 *
	 * @param characterCount - letter count
	 * @return - a list of difference
	 */
	public List<String> getSequencesNotAppear(int characterCount) {
		List<String> morseCodeCombinations = getAllMorseCodeCombination(characterCount);
		List<String> morseCodeWords = new ArrayList<>();
		smooshed.getMorseCodeWords().forEach((k, v) -> morseCodeWords.add(v));

		List<String> result = new ArrayList<>();
		for (String combination : morseCodeCombinations) {
			boolean isInTheList = false;
			for (String word : morseCodeWords) {
				if (word.contains(combination)) {
					isInTheList = true;
					break;
				}
			}
			if (!isInTheList) {
				result.add(combination);
			}
		}
		return result;
	}

	private List<String> getAllMorseCodeCombination(int length) {
		List<String> words = new ArrayList<>();
		getMorseCodeWords(StringUtils.EMPTY, length, words);
		return words;
	}

	private void getMorseCodeWords(String base, int length, List<String> list) {
		CHARACTER_LIST.forEach(element -> {
			if (length == 1) {
				list.add(base + element);
			} else {
				getMorseCodeWords(base + element, length - 1, list);
			}
		});
	}

	private Map<String, List<String>> groupMorseCodeWords(Map<String, String> wordMap) {
		Map<String, List<String>> resultMap = new HashMap<>();
		for (Map.Entry<String, String> entry : wordMap.entrySet()) {
			if (resultMap.containsKey(entry.getValue())) {
				resultMap.get(entry.getValue()).add(entry.getKey());
			} else {
				List<String> list = new ArrayList<>();
				list.add(entry.getKey());
				resultMap.put(entry.getValue(), list);
			}
		}
		return resultMap;
	}
}
