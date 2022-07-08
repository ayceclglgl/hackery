package smooshe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.MorseCodeCount;

/**
 * Calculates and serves five use cases
 */
public class SmooshedFinder {
	private static final String EMPTY_STRING = "";

	private final Smooshed smooshed;

	public SmooshedFinder(Smooshed smooshed){
		this.smooshed = smooshed;
	}

	/**
	 * Find the only sequence that's the code for 13 different words.
	 * @return - the word which its morse code is same for 13 different word, empty string if the word is not found
	 */
	public String codeThatIsSameFor13DifferentWords(){
		Map<String, String> wordMap = smooshed.getWordMorseCodeMap();
		Map<String, List<String>> resultMap = new HashMap<>();
		for (Map.Entry<String, String> entry : wordMap.entrySet()) {
			if(resultMap.containsKey(entry.getValue())){
				resultMap.get(entry.getValue()).add(entry.getKey());
			}else{
				List<String> list = new ArrayList<>();
				list.add(entry.getKey());
				resultMap.put(entry.getValue(), list);
			}
		}

		for (Map.Entry<String, List<String>> entry : resultMap.entrySet()) {
			if(entry.getValue().size() == 13){
				return entry.getKey();
			}
		}
		return EMPTY_STRING;
	}

	/**
	 * Find the only word that has 15 dashes in a row.
	 * @return - the first word which has 15 dashes in the list, empty string if the word is not found
	 */
	public String wordHas15Dashes(){
		Map<String, String> wordMap = smooshed.getWordMorseCodeMap();
		for (Map.Entry<String, String> entry : wordMap.entrySet()) {
			int dashNumber = smooshed.countDashes(entry.getValue());
			if(dashNumber == 15){
				return entry.getKey();
			}
		}
		return EMPTY_STRING;
	}


	/**
	* Call a word perfectly balanced if its code has the same number of dots as dashes.
	* counterdemonstrations is one of two 21-letter words that's perfectly balanced.
	 * Find the other one.
	 * @return - perfectly balanced words, empty list if there is no balanced word
	 */
	public List<String> getPerfectlyBalancedWords(){
		Map<String, String> wordMap = smooshed.getWordMorseCodeMap();
		List<String> perfectlyBalancedWord = new ArrayList<>();
		for (Map.Entry<String, String> entry : wordMap.entrySet()){
			if(entry.getKey().length() == 21){
				MorseCodeCount morseCodeCount = smooshed.countDashesAndDots(entry.getValue());
				if(morseCodeCount.getDotCount() == morseCodeCount.getDashCount()){
					perfectlyBalancedWord.add(entry.getKey());
				}
			}
		}
		return perfectlyBalancedWord;
	}

	/*
	protectorate is 12 letters long and encodes to .--..-.----.-.-.----.-..--.,
	which is a palindrome (i.e. the string is the same when reversed). Find the only 13-letter word that encodes to a palindrome.
	 */

	/*
	--.---.---.-- is one of five 13-character sequences that does not appear in the encoding of any word. Find the other four.
	 */
}
