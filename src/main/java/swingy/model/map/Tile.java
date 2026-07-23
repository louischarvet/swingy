package swingy.model.map;

import swingy.model.character.Character;

public class Tile {
	private final int	y;
	private final int	x;
	private int	value;	// not final for tests // 1 == wall
	private boolean	visible = false;
	private Character	onThis = null;

	public Tile(int y, int x, int value) {
		this.y = y;
		this.x = x;
		this.value = value;
	}

	public Tile(Tile rhs) {
		this.y = rhs.getY();
		this.x = rhs.getX();
		this.value = rhs.getValue();
		this.visible = rhs.isVisible();
		this.onThis = rhs.getOnThis();
	}

	public void	setValue(int value) {
		this.value = value;
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