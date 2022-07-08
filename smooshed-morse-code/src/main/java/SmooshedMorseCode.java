import java.util.List;

import smooshe.Smooshed;
import smooshe.SmooshedFinder;
import util.FileReader;

public class SmooshedMorseCode {
	private static final FileReader fileReader = new FileReader();
	private static final Smooshed       smooshed       = new Smooshed(fileReader);
	private static final SmooshedFinder smooshedFinder = new SmooshedFinder(smooshed);

	public static void main(String[] args) {
		String codeThatIsSameFor13DifferentWords = smooshedFinder.codeThatIsSameFor13DifferentWords();
		System.out.printf("The only only sequence that's the code for 13 different words: %s%n", codeThatIsSameFor13DifferentWords);

		String wordWith15Dashes = smooshedFinder.wordHas15Dashes();
		System.out.printf("The only word that has 15 dashes in a row: %s%n", wordWith15Dashes);

		List<String> perfectlyBalancedWords = smooshedFinder.getPerfectlyBalancedWords();
		System.out.printf("A word is perfectly balanced if its code has the same number of dots as dashes, perfectly balanced words: %s%n", perfectlyBalancedWords);
	}
}