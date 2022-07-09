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
	private final Smooshed smooshed;

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
		Map<String, List<String>> resultMap = groupMorseCodeMap(smooshed.getWordMorseCodeMap());
		for (Map.Entry<String, List<String>> entry : resultMap.entrySet()) {
			if (entry.getValue().size() == count) {
				wordList.add(entry.getKey());
			}
		}
		return wordList;
	}

	/**
	 * Finds words with a given dash count.
	 *
	 * @param dashCount - count of dash in a word
	 * @return - words which has {@param dashCount} dashes, empty list if words is not found
	 */
	public List<String> getWords(int dashCount) {
		List<String> wordList = new ArrayList<>();
		for (Map.Entry<String, String> entry : smooshed.getWordMorseCodeMap().entrySet()) {
			int dashNumber = smooshed.countDashes(entry.getValue());
			if (dashNumber == dashCount) {
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
		for (Map.Entry<String, String> entry : smooshed.getWordMorseCodeMap().entrySet()) {
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
		for (Map.Entry<String, String> entry : smooshed.getWordMorseCodeMap().entrySet()) {
			if (entry.getValue().length() == letterCount) {
				String reverse = new StringBuilder(entry.getValue()).reverse().toString();
				if (entry.getValue().equals(reverse)) {
					return entry.getValue();
				}
			}
		}
		return StringUtils.EMPTY;
	}

	/*
	--.---.---.-- is one of five 13-character sequences that does not appear in the encoding of any word.
	 Find the other four.
	 */
	public List<String> getSequencesNotAppear(int characterCount) {
		return List.of();
	}

	private Map<String, List<String>> groupMorseCodeMap(Map<String, String> wordMap) {
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
