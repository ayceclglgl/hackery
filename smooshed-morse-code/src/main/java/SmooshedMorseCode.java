import smooshe.Smooshed;
import smooshe.SmooshedFinder;
import util.FileReader;

public class SmooshedMorseCode {
	private static final FileReader     fileReader     = new FileReader();
	private static final Smooshed       smooshed       = new Smooshed(fileReader);
	private static final SmooshedFinder smooshedFinder = new SmooshedFinder(smooshed);

	public static void main(String[] args) {
		System.out.printf("Find the only sequence that's the code for 13 different words: %s%n",
				smooshedFinder.getWordsHavingSameSmooshedMorseCode(13));
		System.out.printf("Find the only word that has 15 dashes in a row: %s%n", smooshedFinder.getWords(15));
		System.out.printf("21 letter perfectly balanced words: %s%n", smooshedFinder.getPerfectlyBalancedWords(21));
		System.out.printf("13-letter palindrome word: %s%n", smooshedFinder.getPalindromeWord(13));
		System.out.printf("Find five 13-character sequences that does not appear in the encoding of any word: %s%n",
				smooshedFinder.getSequencesNotAppear(15));
	}
}