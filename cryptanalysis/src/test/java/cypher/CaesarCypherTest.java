package cypher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import util.Direction;
import util.InputValidation;

@ExtendWith(MockitoExtension.class)
class CaesarCypherTest {

	private static final String          PLAIN_STRING           = "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG";
	private static final String          LEFT_DECRYPTED_STRING  = "QEB NRFZH YOLTK CLU GRJMP LSBO QEB IXWV ALD";
	private static final String          RIGHT_DECRYPTED_STRING = "WKH TXLFN EURZQ IRA MXPSV RYHU WKH ODCB GRJ";
	@Mock
	private              InputValidation inputValidation;
	@InjectMocks
	private              CaesarCypher    caesarCypher;

	private static final int SHIFT_NUMBER = 3;

	@ParameterizedTest
	@NullAndEmptySource
	void encrypt_whenInputIsNullAndEmpty_thenReturnEmptyString(String input) {
		assertEquals("", caesarCypher.encrypt(input, Direction.LEFT, SHIFT_NUMBER));
	}

	@Test
	void encrypt_whenDirectionIsLeft_whenShiftIs3_thenReturnEncryptedString() {
		when(inputValidation.plainText(anyString())).thenReturn(PLAIN_STRING);

		assertEquals(LEFT_DECRYPTED_STRING,
				caesarCypher.encrypt("THE QUICK BRO/WN ??FOX JUM--PS OVER TH?E LAZ-Y DO)G", Direction.LEFT, SHIFT_NUMBER));
	}

	@Test
	void decrypt_whenDirectionIsLeft_whenShiftIs3_thenReturnDecryptedString() {
		assertEquals(PLAIN_STRING, caesarCypher.decrypt(LEFT_DECRYPTED_STRING, Direction.LEFT, SHIFT_NUMBER));
	}

	@Test
	void encrypt_whenDirectionIsRight_whenShiftIs3_thenReturnEncryptedString() {
		when(inputValidation.plainText(anyString())).thenReturn(PLAIN_STRING);

		assertEquals(RIGHT_DECRYPTED_STRING,
				caesarCypher.encrypt("THE QUICK BRO/WN ??FOX JUM--PS OVER TH?E LAZ-Y DO)G", Direction.RIGHT, SHIFT_NUMBER));
	}

	@Test
	void decrypt_whenDirectionIsRight_whenShiftIs3_thenReturnDecryptedString() {
		assertEquals(PLAIN_STRING, caesarCypher.decrypt(RIGHT_DECRYPTED_STRING, Direction.RIGHT, SHIFT_NUMBER));
	}

}