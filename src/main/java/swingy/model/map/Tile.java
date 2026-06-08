package swingy.model.map;

import swingy.model.character.Character;

public class Tile {
	private final int	y;
	private final int	x;
	private final int	value;
	private boolean	visible = false;
	private Character	onThis = null;

	public Tile(int y, int x, int value) {
		this.y = y;
		this.x = x;
		this.value = value;
	}

	public Tile(int y, int x, int value, boolean visible) {
		this.y = y;
		this.x = x;
		this.value = value;
		this.visible = visible;
	}

	public void	setVisible(boolean visible) {
		this.visible = visible;
	}

	public void	setOnThis(Character character) {
		this.onThis = character;
	}

	public int	getX() {
		return this.x;
	}

	public int	getY() {
		return this.y;
	}

	public int	getValue() {
		return value;
	}

	public boolean	isVisible() {
		return visible;
	}

	public Character	getOnThis() {
		return this.onThis;
	}
}