package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InputValidationTest {

	private final InputValidation inputValidation = new InputValidation();

	@Test
	void plainText() {
		String text = inputValidation.plainText("loo,k at th?e :blue sky!!!");
		assertEquals("LOOK AT THE BLUE SKY", text);
	}

}