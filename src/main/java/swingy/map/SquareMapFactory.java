package swingy.map;

import java.lang.Integer;

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