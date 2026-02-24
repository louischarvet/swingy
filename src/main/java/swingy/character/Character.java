package swingy.character;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import swingy.artifact.Artifact;
import swingy.artifact.Weapon;
import swingy.artifact.Helm;
import swingy.artifact.Armor;

public abstract class Character {
	@NotNull
	@Size(min = 1, max = 10, message = "Name must be 1 to 10 characters long.")
	private final String	name;

//	@NotNull
//	@Pattern()
	private final String	klass; // Hero only ?

	private int	level;

	private int	attack;
	private int	defense;
	private int	hitPoints;
	// hero & villain: attribut private int damagePoints ?

	protected Weapon	weapon;
	protected Armor	armor;
	protected Helm	helm;

	protected Character(Builder builder) {
		this.name = builder.name;
		this.klass = builder.klass;

		this.level = builder.level;
		this.attack = builder.attack;
		this.defense = builder.defense;
		this.hitPoints = builder.hitPoints;

		this.weapon = null;
		this.armor = null;
		this.helm = null;
	}

	public String	getName() {
		return this.name;
	}

	public String	getKlass() {
		return this.klass;
	}

	public int	attackOutput() {
		int	bonus = this.weapon != null ? this.weapon.getLevel() : 0;
		
		return this._attack + bonus;
	}

	public int	defenseOutput() {
		int	bonus = this.armor != null ? this.armor.getLevel() : 0;
		
		return this._defense + bonus;
	}

	public int	hitPointsOutput() {
		int	bonus = this.helm != null ? this.helm.getLevel() : 0;
		
		return this.hitPoints + bonus;
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
				this.weapon = (Weapon)artifact;
				break;
			case "Armor":
				this.armor = (Armor)artifact;
				break;
			case "Helm":
				this.helm = (Helm)artifact;
				break;
			default:
				break;
		}
	}

	public void	print() { // abstract
		String	attackBonus = this.weapon != null ?
			(" \u001B[31m+" + this.weapon.getLevel()) + "\u001B[0m" : "";
		String	defenseBonus = this.armor != null ?
			(" \u001B[34m+" + this.armor.getLevel()) + "\u001B[0m" : "";
		String	hitPointsBonus = this.helm != null ?
			(" \u001B[32m+" + this.helm.getLevel()) + "\u001B[0m" : "";

		System.out.print(
			this.getName() + " | " + this.getCharacterClass()
			+ " | LVL " + this.level
			+ " | ATT " + this.attack + attackBonus
			+ " | DEF " + this.defense + defenseBonus
			+ " | HPS " + this.hitPoints + hitPointsBonus
		);
	}

	public static abstract class Builder {
		private String	name;
		private String	klass;

		private int	level = 1;
		private int	attack = 1;
		private int	defense = 1;
		private int	hitPoints = 1;

//		private Weapon	weapon = null;
//		private Armor	armor = null;
//		private Helm	helm = null;

		public Builder	setName(String p_name) {
			this.name = p_name;
			return this;
		}

		public Builder	setClass(String p_class) {
			this.klass = p_class;
			return this;
		}

		public Builder	setLevel(int p_level) {
			this.level = p_level;
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