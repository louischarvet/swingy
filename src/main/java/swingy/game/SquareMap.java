package swingy.game;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.lang.Math;
import java.lang.Integer;

public class SquareMap {
	private static final Random	random = new Random();

	private static enum Tile { // make class ?
		PATH	(0),
		WALL	(1);

		private final int	value;
		private boolean	villain;

		private int	getValue() {
			return this.value;
		}

		private boolean	getVillain() {
			return this.villain;
		}

		private void	setVillain(boolean v) {
			this.villain = v;
		}

		private	Tile(int p_value){
			this.value = p_value;
			this.villain = false;
		}
	}

	private final int	size;
	private final Tile	grid[][];

	// private final boolean	visited[][];
	// private final int	directions[][] = {
	// 	{ 0, 1 },
	// 	{ 1, 0 },
	// 	{ 0, -1 },
	// 	{ -1, 0 }
	// };


	public SquareMap(int level) {
		this.size = (level - 1) * 5 + 10 - (level % 2);
		this.grid = new Tile[this.size][this.size];
		// this.visited = new boolean[this.size][this.size];
		
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				this.grid[i][j] = Tile.PATH;
				int	randInt = random.nextInt();
				System.out.println("randInt = " + randInt
					+ " % " + (level + 1) + " = " + randInt % (level + 1));
				this.grid[i][j].setVillain(randInt % (level + 1) != 0);
				// if (randInt % (level + 1) != 0)
				// 	this.grid[i][j].villain = true;
				// else
				// 	this.grid[i][j].villain = false;
			//	this.visited[i][j] = false;
			}
		}
		// deptFirstSearch(this.size / 2, this.size / 2);
//		deptFirstSearch(2, 2);
	}

	// private void	deptFirstSearch(int y, int x) {
	// 	this.visited[y][x] = true;
	// 	this.grid[y][x] = Tile.PATH;
	// 	List< int[] >	neighbors = new ArrayList<>();

	// 	for (int[] direction : this.directions) {
	// 		int	ny = y + direction[0] * 2,
	// 			nx = x + direction[1] * 2;

	// 		if (nx >= 0 && nx < this.size
	// 			&& ny >= 0 && ny < this.size
	// 			&& this.visited[ny][nx] == false)
	// 			neighbors.add(new int[] { ny, nx });
	// 	}
	// 	while (!neighbors.isEmpty()) {
	//         int[] neighbor = neighbors.remove(random.nextInt(neighbors.size()));
    //     	int ny = neighbor[0];
    //     	int nx = neighbor[1];

	// 		this.grid[(y + ny) / 2][(x + nx) / 2] = Tile.PATH;
	// 		deptFirstSearch(ny, nx);
	// 	}
	// }

	public void	print() {
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				int	value = this.grid[i][j].getValue();

				if (i == this.size / 2 && j == i) // center
					System.out.print("\u001B[32m");
				else if (this.grid[i][j].getVillain() == true)
					System.out.print("\u001B[31m");
				System.out.print(value + " \u001B[0m");
			}
			System.out.println();
		}
	}
}