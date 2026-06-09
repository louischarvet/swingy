package swingy.model.map;

import java.lang.StringBuilder;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.lang.Math;
import java.lang.Integer;

import swingy.model.character.Character;
import swingy.model.character.Hero;

public class SquareMap {
	private static final Random	random = new Random();
	private final int	size;
	// private final int	grid[][];
	private final Tile	map[][];
	private final Tile	center;

	public SquareMap(int level, Hero hero) {
		this.size = (level - 1) * 5 + 10 - (level % 2);
		this.map = new Tile[this.size][this.size];
		
		for (int y = 0; y < this.size; y++) {
			for (int x = 0; x < this.size; x++) {
				int	randInt = random.nextInt();
				if (y == this.size / 2 && x == y)
					this.map[y][x] = new Tile(y, x, 0, true);
				else
					this.map[y][x] = new Tile(y, x, randInt % (level + (randInt % level)) == 0 ? 0 : 1);
			}
		}
		this.center = this.map[this.size / 2][this.size / 2];
		this.center.setOnThis(hero);
		int	centerX = center.getX();
		int	centerY = center.getY();

		this.map[centerY - 1][centerX].setVisible(true);
		this.map[centerY + 1][centerX].setVisible(true);
		this.map[centerY][centerX - 1].setVisible(true);
		this.map[centerY][centerX + 1].setVisible(true);

		// must check map validity
	}

	public int	getSize() {
		return this.size;
	}

	public Tile	getTile(int y, int x) {
		// check y and x
		// >= 0 && < size
		if (y < 0 || y >= size || x < 0 || x >= size)
			return null;
		else
			return this.map[y][x];
	}

	public Tile	getCenter() {
		return this.center;
	}

	public String	toString() {
		StringBuilder	sb = new StringBuilder();

		for (int y = 0; y < this.size; y++) {
			for (int x = 0; x < this.size; x++) {
				Tile	tile = this.map[y][x];
				if (tile.isVisible()) {
					Character	onTile = tile.getOnThis();
					if (onTile == null)
						sb.append(Integer.toString(tile.getValue()));
					else
						sb.append("H");
				}
				else
					sb.append("X");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public void	print() {
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				int	value = this.map[i][j].getValue();

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