/**
 * Represent steps in hexagonal grid
 */
public interface Step {
	String COMMA = ",";

	/**
	 * Returns step count for a given path
	 *
	 * @param path - path in hexagonal grid
	 * @return - number of steps
	 */
	int getSteps(String path);
}
