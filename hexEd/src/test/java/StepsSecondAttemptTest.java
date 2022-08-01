import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StepsSecondAttemptTest {

	private final Step stepsSecondAttempt = new StepsSecondAttempt();

	@ParameterizedTest
	@MethodSource("paths")
	void getSteps(String input, int expected) {
		int steps = stepsSecondAttempt.getSteps(input);
		assertEquals(expected, steps);
	}

	private static Stream<Arguments> paths() {
		return Stream.of(
				Arguments.of("s,s,n,n,n", 1),
				Arguments.of("se,sw,se,sw,sw", 3),
				Arguments.of("ne,ne,ne", 3),
				Arguments.of("ne,ne,sw,sw", 0),
				Arguments.of("ne,ne,s,s", 2)
		);
	}
}