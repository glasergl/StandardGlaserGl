package standard.helper.enums;

public enum CelestialDirection {
	NORTH, EAST, SOUTH, WEST;

	public CelestialDirection getOpposite() {
		if (this == NORTH) {
			return SOUTH;
		} else if (this == EAST) {
			return WEST;
		} else if (this == SOUTH) {
			return NORTH;
		} else {
			return EAST;
		}
	}
}
