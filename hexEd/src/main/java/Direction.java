public enum Direction {
	SOUTH_WEST("sw"),
	SOUTH_EAST("se"),
	SOUTH("s"),
	NORTH_WEST("nw"),
	NORTH_EAST("ne"),
	NORTH("n");

	private final String name;

	Direction(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
