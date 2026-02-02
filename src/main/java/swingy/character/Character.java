package swingy.character;

import swingy.artifact.Artifact;
import swingy.artifact.Weapon;
import swingy.artifact.Helm;
import swingy.artifact.Armor;

public abstract class Character {
	private final String	_name;
	private final String	_class; // Hero only ?

	private int	_level;

	private int	_attack;
	private int	_defense;
	private int	_hitPoints;

	protected Weapon	_weapon;
	protected Armor	_armor;
	protected Helm	_helm;

	protected Character(Builder builder) {
		this._name = builder._name;
		this._class = builder._class;

		this._level = builder._level;
		this._attack = builder._attack;
		this._defense = builder._defense;
		this._hitPoints = builder._hitPoints;

		this._weapon = null;
		this._armor = null;
		this._helm = null;
	}

	public String	getName() {
		return this._name;
	}

	public String	getCharacterClass() {
		return this._class;
	}

	public int	attackOutput() {
		int	bonus = this._weapon != null ? this._weapon.getLevel() : 0;
		
		return this._attack + bonus;
	}

	public int	defenseOutput() {
		int	bonus = this._armor != null ? this._armor.getLevel() : 0;
		
		return this._defense + bonus;
	}

	public int	hitPointsOutput() {
		int	bonus = this._helm != null ? this._helm.getLevel() : 0;
		
		return this._hitPoints + bonus;
	}

	public void	equip(Artifact artifact) {
		String	artifactType = artifact.getClass().getSimpleName();
		System.out.println("artifactType: " + artifactType);

		/*
			If an artifact is already equipped
			Ask player if he wants to replace ?
		*/

		switch (artifactType) {
			case "Weapon":
				this._weapon = (Weapon)artifact;
				break;
			case "Armor":
				this._armor = (Armor)artifact;
				break;
			case "Helm":
				this._helm = (Helm)artifact;
				break;
			default:
				break;
		}
	}

	public void	print() { // abstract
		String	attackBonus = this._weapon != null ?
			(" \u001B[31m+" + this._weapon.getLevel()) + "\u001B[0m" : "";
		String	defenseBonus = this._armor != null ?
			(" \u001B[34m+" + this._armor.getLevel()) + "\u001B[0m" : "";
		String	hitPointsBonus = this._helm != null ?
			(" \u001B[32m+" + this._helm.getLevel()) + "\u001B[0m" : "";

		System.out.print(
			this.getName() + " | " + this.getCharacterClass()
			+ " | LVL " + this._level
			+ " | ATT " + this._attack + attackBonus
			+ " | DEF " + this._defense + defenseBonus
			+ " | HPS " + this._hitPoints + hitPointsBonus
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

		public Builder	setName(String p_name) {
			this._name = p_name;
			return this;
		}

		public Builder	setClass(String p_class) {
			this._class = p_class;
			return this;
		}

		public Builder	setLevel(int p_level) {
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