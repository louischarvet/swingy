package swingy.model.map;

import java.lang.Integer;

import swingy.model.character.Hero;

public final class SquareMapFactory {
	private	SquareMapFactory() {
		super();
	}

	public static SquareMap	create(int level, Hero hero) {
		SquareMap	map;
		Tile	center;

		do {
			map = new SquareMap(level, hero);
			center = map.getCenter();
		} while (floodFill(new SquareMap(map), center.getY(), center.getX()) == false); // copy map for test

		return map;
	}

	private	static boolean	floodFill(SquareMap map, int y, int x) {
		Tile	tile = map.getTile(y, x);

		if (tile == null)
			return true;
		else if (tile.getValue() == 1)
			return false;
		else {
			// tile.setValue(2); // test
			// map.print();
			return floodFill(map, y - 1, x)
				|| floodFill(map, y - 1, x)
				|| floodFill(map, y - 1, x)
				|| floodFill(map, y - 1, x);
		}
	}
}