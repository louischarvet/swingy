package swingy.model.map;

import java.lang.StringBuilder;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.lang.Math;
import java.lang.Integer;

import swingy.model.character.Character;
import swingy.model.character.Hero;
import swingy.model.character.Villain;

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
				Tile	tile;

				if (y == this.size / 2 && x == y)
					tile = new Tile(y, x, 0);
				else
					tile = new Tile(y, x, (randInt % (level + (randInt % level))) == 0 ? 0 : 1);
				this.map[y][x] = tile;
			}
		}
		this.center = this.map[this.size / 2][this.size / 2];
		this.center.setOnThis(hero);
		
		setVillains();
		setVisibility(center.getY(), center.getX());
	}

	public SquareMap(SquareMap rhs) {
		this.size = rhs.getSize();
		this.map = new Tile[this.size][this.size];
		for (int y = 0; y < this.size; y++) {
			for (int x = 0; x < this.size; x++) {
				this.map[y][x] = new Tile(rhs.getTile(y, x));
			}
		}
		this.center = rhs.getCenter();
	}

	public int	getSize() {
		return this.size;
	}

	public Tile	getTile(int y, int x) {
		if (y < 0 || y >= size || x < 0 || x >= size)
			return null;
		else
			return this.map[y][x];
	}

	public Tile	getCenter() {
		return this.center;
	}

	private void	setVillains() {
		List< Tile >	list = new ArrayList<>();

		for (int y = 0; y < this.size; y++)
			for (int x = 0; x < this.size; x++)
				if (this.map[y][x].getValue() == 0 && this.map[y][x].getOnThis() == null)
					list.add(this.map[y][x]);

		Collections.shuffle(list);

		for (int i = 0, n = list.size() / 5; i < n; i++)
			list.get(i).setOnThis(
				new Villain.Builder()
					.withName("Villain") // random name ?
					.withKlass("TANK") // random Klass ?
					.withLevel(1) /// hero.getLevel()
					.build());
	}

	public void	setVisibility(int y, int x) {
		this.map[y][x].setVisible(true);
		if (y - 1 >= 0)
			this.map[y - 1][x].setVisible(true);
		if (y + 1 < this.size)
			this.map[y + 1][x].setVisible(true);
		if (x - 1 >= 0)
			this.map[y][x - 1].setVisible(true);
		if (x + 1 < this.size)
			this.map[y][x + 1].setVisible(true);
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
					else if (onTile instanceof Hero)
						sb.append("H");
					else
						sb.append("V");
				}
				else
					sb.append("X");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public String	toStringAllVisible() {
		StringBuilder	sb = new StringBuilder();

		for (int y = 0; y < this.size; y++) {
			for (int x = 0; x < this.size; x++) {
				Tile	tile = this.map[y][x];
				Character	onTile = tile.getOnThis();
				if (onTile == null)
					sb.append("\u001B[0m").append(Integer.toString(tile.getValue()));
				else if (onTile instanceof Hero)
					sb.append("\u001B[32mH");
				else
					sb.append("\u001B[35mV");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public void	print() {
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				int	value = this.map[i][j].getValue();

				// if (i == this.size / 2 && j == i) // center
				// 	System.out.print("\u001B[32m");
				// else if (value == 0)
				// 	System.out.print("\u001B[32m");
				// System.out.print(value + " \u001B[0m");
				System.out.print(value);
			}
			System.out.println();
		}
		System.out.println("-----------------------");
	}
}