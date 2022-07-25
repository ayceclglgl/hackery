import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HexEdTest {
	private final HexEd hexEd = new HexEd();

	@ParameterizedTest
	@MethodSource("paths")
	void getSteps(String input, List<String> expected) {
		List<String> steps = HexEd.getSteps(input);
		assertEquals(expected, steps);
	}

	private static Stream<Arguments> paths() {
		return Stream.of(
				Arguments.of("s,s,n,n,n", List.of("n")),
				Arguments.of("se,sw,se,sw,sw", List.of("sw", "s", "s")),
				Arguments.of("ne,ne,ne", List.of("ne", "ne", "ne")),
				Arguments.of("ne,ne,sw,sw", List.of()),
				Arguments.of("ne,ne,s,s", List.of("se", "se"))
		);
	}
}