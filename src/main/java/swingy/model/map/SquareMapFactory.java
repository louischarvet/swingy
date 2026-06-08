package swingy.model.map;

import java.lang.Integer;

import swingy.model.character.Hero;

public final class SquareMapFactory {
	private	SquareMapFactory() {
		super();
	}

	public static SquareMap	create(int level, Hero hero) {
		return new SquareMap(level, hero);
	}
}