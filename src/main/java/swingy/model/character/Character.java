package swingy.model.character;

import java.util.List;
import java.util.ArrayList;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;

import swingy.model.artifact.Artifact;
import swingy.model.artifact.Weapon;
import swingy.model.artifact.Helm;
import swingy.model.artifact.Armor;

import swingy.model.map.Tile;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Character {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int	id;

	@Column(name = "name")
	protected String	name;

	// @Pattern(regexp = "^(BERSERKER|TANK|RESILIENT)$", message = "Class must be (either digit or string): 1 BERSERKER, 2 TANK, 3 RESILIENT")
	@Column(name = "klass")
	protected	String	klass; // Hero only ?

	@Column(name = "level")
	protected int	level;

	@Column(name = "attack")
	protected int	attack;

	@Column(name = "defense")
	protected int	defense;

	@Column(name = "hitPoints")
	protected int	hitPoints;
	
	@Column(name = "maxHitPoints")
	protected int	maxHitPoints;

	@Transient
	protected Tile	position = null;

	// hero & villain: attribut private int damagePoints ?

//	@Column(name = "weapon")
	@Transient
	protected Weapon	weapon;

//	@Column(name = "armor")
	@Transient
	protected Armor	armor;

//	@Column(name = "helm")
	@Transient
	protected Helm	helm;

/**
 * CONSTRUCTORS
 */

	public Character() {}

	protected Character(Builder builder) {
		this.name = builder.name;
		this.klass = builder.klass;

		this.level = builder.level;
		this.attack = builder.attack;
		this.defense = builder.defense;
		this.hitPoints = builder.hitPoints;
		this.maxHitPoints = this.hitPoints;

		this.position = builder.position;

		this.weapon = null;
		this.armor = null;
		this.helm = null;
	}

/**
 * GETTERS
 */
	public int	getId() {
		return this.id;
	}

	public String	getName() {
		return this.name;
	}

	public String	getKlass() {
		return this.klass;
	}

	public int	getLevel() {
		return this.level;
	}

	public int	getAttack() {
		return this.attack;
	}

	public int	getDefense() {
		return this.defense;
	}

	public int	getHitPoints() {
		return this.hitPoints;
	}

	public int	getMaxHitPoints() {
		return this.maxHitPoints;
	}

	public Tile	getPosition() {
		return this.position;
	}

	public Weapon	getWeapon() {
		return this.weapon;
	}

	public Armor	getArmor() {
		return this.armor;
	}

	public Helm	getHelm() {
		return this.helm;
	}

/**
 * SETTERS
 */

	public void	setName(String name) {
		this.name = name;
	}

	public void	setKlass(String klass) {
		this.klass = klass;
	}

	public void	setLevel(int level) {
		this.level = level;
	}

	public void	setAttack(int attack) {
		this.attack = attack;
	}

	public void	setDefense(int defense) {
		this.defense = defense;
	}

	public void	setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public void	setMaxHitPoints(int maxHitPoints) {
		this.maxHitPoints = maxHitPoints;
	}

	public void	setPosition(Tile position) {
		this.position = position;
	}

	public abstract String	toString();

	public void	setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void	setArmor(Armor armor) {
		this.armor = armor;
	}

	public void	setHelm(Helm helm) {
		this.helm = helm;
	}

/**
 * METHODS
 */

	public int	attackOutput() {
		int	bonus = this.weapon != null ? this.weapon.getLevel() : 0;
		
		return this.attack + bonus;
	}

	public int	defenseOutput() {
		int	bonus = this.armor != null ? this.armor.getLevel() : 0;
		
		return this.defense + bonus;
	}

	public int	hitPointsOutput() {
		int	bonus = this.helm != null ? this.helm.getLevel() : 0;
		
		return this.hitPoints + bonus;
	}

	// public void	equip(Artifact artifact) {
	// 	String	artifactType = artifact.getClass().getSimpleName();
	// 	System.out.println("artifactType: " + artifactType);

	// 	/*
	// 		If an artifact is already equipped
	// 		Ask player if he wants to replace ?
	// 	*/

	// 	switch (artifactType) {
	// 		case "Weapon":
	// 			this.weapon = (Weapon)artifact;
	// 			break;
	// 		case "Armor":
	// 			this.armor = (Armor)artifact;
	// 			break;
	// 		case "Helm":
	// 			this.helm = (Helm)artifact;
	// 			break;
	// 		default:
	// 			break;
	// 	}
	// }

	// public void	print() { // abstract
	// 	String	attackBonus = this.weapon != null ?
	// 		(" \u001B[31m+" + this.weapon.getLevel()) + "\u001B[0m" : "";
	// 	String	defenseBonus = this.armor != null ?
	// 		(" \u001B[34m+" + this.armor.getLevel()) + "\u001B[0m" : "";
	// 	String	hitPointsBonus = this.helm != null ?
	// 		(" \u001B[32m+" + this.helm.getLevel()) + "\u001B[0m" : "";

	// 	System.out.print(
	// 		this.getName() + " | " + this.getKlass()
	// 		+ " | LVL " + this.level
	// 		+ " | ATT " + this.attack + attackBonus
	// 		+ " | DEF " + this.defense + defenseBonus
	// 		+ " | HPS " + this.hitPoints + hitPointsBonus
	// 	);
	// }

	public static abstract class Builder< T extends Character > {
		private final static List< String >	klassList = new ArrayList<>();
		
		static { 
			klassList.add("BERSERKER");
			klassList.add("TANK");
			klassList.add("RESILIENT");
		}

		private String	name;
		private String	klass;

		private int	level = 1;

		private int	attack = 3;
		private int	defense = 1;
		private int	hitPoints = 5;

		private Tile	position = null;

		private Weapon	weapon = null;
		private Armor	armor = null;
		private Helm	helm = null;

		public Builder< T >	withName(String p_name) {
			this.name = p_name;
			return this;
		}

		public Builder< T >	withKlass(String p_class) {
			this.klass = p_class;
			switch (this.klass) {
				case "BERSERKER":
					this.attack++;
					break;
				case "TANK":
					this.defense++;
					break;
				case "RESILIENT":
					this.hitPoints += 3;
					break;
				default:
					break;
			}
			return this;
		}

		public Builder< T >	withKlass(int index) {
			return this.withKlass(klassList.get(index));
		}

		public Builder< T >	withLevel(int p_level) {
			this.level = p_level;
			return this;
		}

		public Builder< T >	withPosition(Tile p) {
			this.position = p;
			return this;
		}

		public Builder	withWeapon(Weapon p_weapon) {
			this.weapon = p_weapon;
			return this;
		}

		public Builder	withArmor(Armor p_armor) {
			this.armor = p_armor;
			return this;
		}

		public Builder	withHelm(Helm p_helm) {
			this.helm = p_helm;
			return this;
		}

		// public Character	build() {
		// 	return new Character(this);
		// }

		public abstract T	build();
	}
}