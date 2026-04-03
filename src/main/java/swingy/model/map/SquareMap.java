package swingy.model.map;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.lang.Math;
import java.lang.Integer;

public class SquareMap {
	private static final Random	random = new Random();
	private final int	size;
	private final int	grid[][];

	public SquareMap(int level) {
		this.size = (level - 1) * 5 + 10 - (level % 2);
		this.grid = new int[this.size][this.size];
		
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				int	randInt = random.nextInt();
				if (i == this.size / 2 && j == i)
					this.grid[i][j] = 0;
				else
					this.grid[i][j] = randInt % (level + (randInt % level)) == 0 ? 0 : 1;
			}
		}
		// must check map validity
	}

	public void	print() {
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				int	value = this.grid[i][j];

				if (i == this.size / 2 && j == i) // center
					System.out.print("\u001B[32m");
				else if (value == 0)
					System.out.print("\u001B[32m");
				System.out.print(value + " \u001B[0m");
			}
			System.out.println();
		}
	}
}