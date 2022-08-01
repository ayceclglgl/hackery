import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StepsFirstAttempt implements Step {
	@Override
	public int getSteps(String path) {
		List<String> pathList = Arrays.asList(path.split(COMMA));
		return findStepsFromPath(pathList).size();
	}

	private List<String> findStepsFromPath(List<String> pathList) {
		List<String> steps = new ArrayList<>(pathList);

		long south = pathList.stream().filter(Direction.SOUTH.getName()::equals).count();
		long north = pathList.stream().filter(Direction.NORTH.getName()::equals).count();
		removeOpposite(Math.min(south, north), steps, Direction.SOUTH.getName(), Direction.NORTH.getName());

		long southWest = pathList.stream().filter(Direction.SOUTH_WEST.getName()::equals).count();
		long northEast = pathList.stream().filter(Direction.NORTH_EAST.getName()::equals).count();
		removeOpposite(Math.min(southWest, northEast), steps, Direction.SOUTH_WEST.getName(), Direction.NORTH_EAST.getName());

		long southEast = pathList.stream().filter(Direction.SOUTH_EAST.getName()::equals).count();
		long northWest = pathList.stream().filter(Direction.NORTH_WEST.getName()::equals).count();
		removeOpposite(Math.min(northWest, southEast), steps, Direction.SOUTH_EAST.getName(), Direction.NORTH_WEST.getName());

		combineSteps(Math.min(southWest, southEast), steps, Direction.SOUTH_WEST.getName(), Direction.SOUTH_EAST.getName(),
				Direction.SOUTH.getName());
		combineSteps(Math.min(south, northWest), steps, Direction.SOUTH.getName(), Direction.NORTH_WEST.getName(), Direction.SOUTH_WEST.getName());
		combineSteps(Math.min(southWest, north), steps, Direction.SOUTH_WEST.getName(), Direction.NORTH.getName(), Direction.NORTH_WEST.getName());
		combineSteps(Math.min(northWest, northEast), steps, Direction.NORTH_WEST.getName(), Direction.NORTH_EAST.getName(),
				Direction.NORTH.getName());
		combineSteps(Math.min(north, southEast), steps, Direction.NORTH.getName(), Direction.SOUTH_EAST.getName(), Direction.NORTH_EAST.getName());
		combineSteps(Math.min(northEast, south), steps, Direction.NORTH_EAST.getName(), Direction.SOUTH.getName(), Direction.SOUTH_EAST.getName());

		return steps;
	}

	private void combineSteps(long stepCount, List<String> steps, String step1, String step2, String combinedStep) {
		IntStream.range(0, (int) stepCount).forEach(i -> {
			steps.remove(step1);
			steps.remove(step2);
			steps.add(combinedStep);
		});
	}

	private void removeOpposite(long stepCount, List<String> steps, String step1, String step2) {
		IntStream.range(0, (int) stepCount).forEach(i -> {
			steps.remove(step1);
			steps.remove(step2);
		});
	}
}
