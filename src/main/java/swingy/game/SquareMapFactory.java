package swingy.game;

import java.lang.Integer;
// import swingy.game.SquareMap;

public final class SquareMapFactory {
	public static void	main(String args[]) {
		if (args.length != 1)
			return ;
		SquareMap	grid = newSquareMap(Integer.parseInt(args[0]));
		grid.print();
	}

	private	SquareMapFactory() {
		super();
	}

	public static SquareMap	newSquareMap(int level) {
		return new SquareMap(level);
	}
}