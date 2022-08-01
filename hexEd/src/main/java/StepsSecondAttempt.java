import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StepsSecondAttempt implements Step {
	private static final Map<String, Coordinates> DIRECTIONS = Map.ofEntries(
			Map.entry(Direction.NORTH_EAST.getName(), new Coordinates(1, -1, 0)),
			Map.entry(Direction.NORTH_WEST.getName(), new Coordinates(-1, 0, 1)),
			Map.entry(Direction.NORTH.getName(), new Coordinates(0, -1, 1)),
			Map.entry(Direction.SOUTH_EAST.getName(), new Coordinates(1, 0, -1)),
			Map.entry(Direction.SOUTH_WEST.getName(), new Coordinates(-1, 1, 0)),
			Map.entry(Direction.SOUTH.getName(), new Coordinates(0, 1, -1))
	);

	@Override
	public int getSteps(String path) {
		List<String> pathList = Arrays.asList(path.split(COMMA));
		Coordinates steps = new Coordinates(0, 0, 0);
		pathList.forEach(direction -> {
			steps.setQ(steps.getQ() + DIRECTIONS.get(direction).getQ());
			steps.setR(steps.getR() + DIRECTIONS.get(direction).getR());
			steps.setS(steps.getR() + DIRECTIONS.get(direction).getS());
		});
		return Math.max(Math.abs(steps.getS()), Math.max(Math.abs(steps.getQ()), Math.abs(steps.getR())));
	}
}
