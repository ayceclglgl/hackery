import java.util.Scanner;

import cypher.CaesarCypher;
import cypher.Cypher;
import util.Direction;
import util.InputValidation;

public final class Cryptanalysis {

	private static final InputValidation inputValidation = new InputValidation();
	private static final Cypher          cypher          = new CaesarCypher(inputValidation);

	private Cryptanalysis() {
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter a text:");
		String plainText = scanner.nextLine();
		System.out.println("Please enter shift number between 1 - 25");
		int shiftNumber = scanner.nextInt();
		System.out.println("Please enter direction, 0 for shifting left or 1 for shifting right.");
		int directionInput = scanner.nextInt();
		Direction direction = Direction.getDirection(directionInput);

		String encryptedText = cypher.encrypt(plainText, direction, shiftNumber);
		String decryptText = cypher.decrypt(encryptedText, direction, shiftNumber);

		System.out.printf(String.format("Plain text:%n%s%nEncrypted text:%n%s%nDecrypted text:%n%s", plainText, encryptedText, decryptText));
	}

}
