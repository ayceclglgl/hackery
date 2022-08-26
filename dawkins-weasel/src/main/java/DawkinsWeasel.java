import java.util.Map;
import java.util.Optional;
import java.util.Random;

import service.Simulation;
import util.Generator;

public final class DawkinsWeasel {
	private static final String TARGET_STRING    = "METHINKS IT IS LIKE A WEASEL";
	private static final int    NUMBER_OF_COPIES = 100;
	private static final int    STRING_LENGTH    = 28;

	private static final Random     random     = new Random();
	private static final Generator  generator  = new Generator(random);
	private static final Simulation simulation = new Simulation(generator);

	private DawkinsWeasel() {
	}

	public static void main(String[] args) {
		run(generator.generateRandomString(STRING_LENGTH));
	}

	private static void run(String randomString) {
		System.out.printf("Random string:%s. Target string:%s\n", randomString, TARGET_STRING);
		Optional<Map.Entry<String, Integer>> entryOptional = simulation.simulate(randomString, TARGET_STRING,
				NUMBER_OF_COPIES);
		if (entryOptional.isEmpty()) {
			return;
		}

		Map.Entry<String, Integer> highestScoringEntry = entryOptional.get();
		if (highestScoringEntry.getValue() == TARGET_STRING.length()) {
			System.out.printf("Matched! Random string:%s, target string:%s\n", highestScoringEntry.getKey(), TARGET_STRING);
		} else {
			run(highestScoringEntry.getKey());
		}

	}
}
