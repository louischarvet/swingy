package swingy.map;

import java.lang.Integer;

public final class SquareMapFactory {
	private	SquareMapFactory() {
		super();
	}

	public static SquareMap	newSquareMap(int level) {
		return new SquareMap(level);
	}
}