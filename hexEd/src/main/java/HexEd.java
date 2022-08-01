import java.util.List;

public final class HexEd {

	private static final List<String> PATHS = List.of("s,s,n,n,n", "se,sw,se,sw,sw", "ne,ne,ne", "ne,ne,sw,sw", "ne,ne,s,s");

	private HexEd() {
	}

	public static void main(String[] args) {
		Step stepsFirstAttempt = new StepsFirstAttempt();
		Step stepsSecondAttempt = new StepsSecondAttempt();
		for (String path : PATHS) {
			System.out.printf("Path: %s%nSteps: %s%n%n", path, stepsFirstAttempt.getSteps(path));
			System.out.printf("Path: %s%nSteps: %s%n%n", path, stepsSecondAttempt.getSteps(path));
		}
	}
}
