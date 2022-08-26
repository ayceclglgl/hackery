package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GeneratorTest {

	@Mock
	private Random    random;
	@InjectMocks
	private Generator generator;

	@Test
	void generateRandomCharacter() {
		when(random.nextInt(anyInt())).thenReturn(23);

		char character = generator.generateRandomCharacter();

		assertTrue(character == ' ' || character >= (int) 'A' && character <= (int) 'Z');
		verify(random).nextInt(anyInt());
	}

	@Test
	void generateRandomCharacterWithProbability_whenProbabilityHolds() {
		when(random.nextDouble()).thenReturn(0.0349877);
		when(random.nextInt(anyInt())).thenReturn(9);

		char character = generator.generateRandomCharacterWithProbability(0.05, 'c');

		assertTrue(character == ' ' || character == 'c' || character >= (int) ('A') && character <= (int) ('Z'));
		verify(random).nextInt(anyInt());
		verify(random, atLeast(1)).nextDouble();
	}

	@Test
	void generateRandomCharacterWithProbability_whenProbabilityNotHold() {
		when(random.nextDouble()).thenReturn(0.9877);

		char character = generator.generateRandomCharacterWithProbability(0.05, 'c');

		assertEquals('c', character);
		verify(random).nextDouble();
	}

	@Test
	void generateRandomString() {
		when(random.nextInt(anyInt())).thenReturn(9);

		String randomString = generator.generateRandomString(10);

		assertEquals(10, randomString.length());
		assertFalse(StringUtils.isNumeric(randomString));
		verify(random, atLeast(1)).nextInt(anyInt());
	}
}