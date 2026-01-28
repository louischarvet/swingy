package swingy;

public abstract class Character {
	private final String	_name;
	private final String	_class;

	private int	_level;
	private int	_attack;
	private int	_defense;
	private int	_hitPoints;

//	private Weapon	_weapon;
//	private Armor	_armor;
//	private Helm	_helm;

	protected Character(Builder builder) {
		this._name = builder._name;
		this._class = builder._class;

		this._level = builder._level;
		this._attack = builder._attack;
		this._defense = builder._defense;
		this._hitPoints = builder._hitPoints;

		// this._weapon = builder._weapon;
		// this._armor = builder._armor;
		// this._helm = builder._helm;
	}

	public String	getName() {
		return this._name;
	}

	public String	getCharacterClass() {
		return this._class;
	}

	public void	print() { // abstract
		System.out.println(
			this.getName()
			+ " " +
			this.getCharacterClass()
		);
	}

	public static abstract class Builder {
		private String	_name;
		private String	_class;

		private int	_level = 1;
		private int	_attack = 1;
		private int	_defense = 1;
		private int	_hitPoints = 1;

//		private Weapon	_weapon = null;
//		private Armor	_armor = null;
//		private Helm	_helm = null;

		public Builder	withName(String p_name) {
			this._name = p_name;
			return this;
		}

		public Builder	withClass(String p_class) {
			this._class = p_class;
			return this;
		}

		public Builder	withLevel(int p_level) {
			this._level = p_level;
			return this;
		}

		// public Builder	withWeapon(Weapon p_weapon) {
		// 	this._weapon = p_weapon;
		// 	return this;
		// }

		// public Builder	withArmor(Armor p_armor) {
		// 	this._armor = p_armor;
		// 	return this;
		// }

		// public Builder	withHelm(Helm p_helm) {
		// 	this._helm = p_helm;
		// 	return this;
		// }

		// public Character	build() {
		// 	return new Character(this);
		// }

		public abstract Character	build();
	}
}