package swingy.model.map;

import java.lang.Integer;

public final class SquareMapFactory {
	private	SquareMapFactory() {
		super();
	}

	public static SquareMap	create(int level) {
		return new SquareMap(level);
	}
}