import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public final class HexEd {

	private static final String COMMA      = ",";
	private static final String SOUTH_WEST = "sw";
	private static final String SOUTH_EAST = "se";
	private static final String SOUTH      = "s";
	private static final String NORTH_WEST = "nw";
	private static final String NORTH_EAST = "ne";
	private static final String NORTH      = "n";

	public static void main(String[] args) {
		List<String> paths = List.of("s,s,n,n,n", "se,sw,se,sw,sw", "ne,ne,ne", "ne,ne,sw,sw", "ne,ne,s,s");
		for (String path : paths) {
			List<String> steps = getSteps(path);
			System.out.printf("Path: %s%nSteps: %s - Step count:%d%n%n", path, steps, steps.size());
		}
	}

	public static List<String> getSteps(String path) {
		List<String> pathList = Arrays.asList(path.split(COMMA));
		return findStepsFromPath(pathList);
	}

	private static List<String> findStepsFromPath(List<String> pathList) {
		List<String> steps = new ArrayList<>(pathList);

		long south = pathList.stream().filter(SOUTH::equals).count();
		long north = pathList.stream().filter(NORTH::equals).count();
		removeOpposite(Math.min(south, north), steps, SOUTH, NORTH);

		long southWest = pathList.stream().filter(SOUTH_WEST::equals).count();
		long northEast = pathList.stream().filter(NORTH_EAST::equals).count();
		removeOpposite(Math.min(southWest, northEast), steps, SOUTH_WEST, NORTH_EAST);

		long southEast = pathList.stream().filter(SOUTH_EAST::equals).count();
		long northWest = pathList.stream().filter(NORTH_WEST::equals).count();
		removeOpposite(Math.min(northWest, southEast), steps, SOUTH_EAST, NORTH_WEST);

		combineSteps(Math.min(southWest, southEast), steps, SOUTH_WEST, SOUTH_EAST, SOUTH);
		combineSteps(Math.min(south, northWest), steps, SOUTH, NORTH_WEST, SOUTH_WEST);
		combineSteps(Math.min(southWest, north), steps, SOUTH_WEST, NORTH, NORTH_WEST);
		combineSteps(Math.min(northWest, northEast), steps, NORTH_WEST, NORTH_EAST, NORTH);
		combineSteps(Math.min(north, southEast), steps, NORTH, SOUTH_EAST, NORTH_EAST);
		combineSteps(Math.min(northEast, south), steps, NORTH_EAST, SOUTH, SOUTH_EAST);

		return steps;
	}

	private static void combineSteps(long stepCount, List<String> steps, String step1, String step2, String combinedStep) {
		IntStream.range(0, (int) stepCount).forEach(i -> {
			steps.remove(step1);
			steps.remove(step2);
			steps.add(combinedStep);
		});
	}

	private static void removeOpposite(long stepCount, List<String> steps, String step1, String step2) {
		IntStream.range(0, (int) stepCount).forEach(i -> {
			steps.remove(step1);
			steps.remove(step2);
		});
	}
}
