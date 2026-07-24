package swingy.model.character;

import java.lang.StringBuilder;
import java.lang.Integer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import swingy.model.FightResult;

import swingy.model.artifact.Artifact;
import swingy.model.artifact.Weapon;
import swingy.model.artifact.Armor;
import swingy.model.artifact.Helm;

@Entity
@Table(name = "hero")
public class Hero extends Character {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int	id;

	@Column(name = "experience")
	private int	experience;

	public Hero() {
		super();
		experience = 0;
	}

	private Hero(Builder builder) {
		super(builder);

		this.experience = 0;
	}

	public int	getId() {
		return id;
	}

	public int	getExperience() {
		return experience;
	}

	public void	setId(int id) {
		this.id = id;
	}

	public void	setExperience(int experience) {
		this.experience = experience;
	}

	public void	gainExperience(int experience) {
		this.experience += experience;
	}

	public FightResult	fight(Villain villain, boolean firstStrike) {
		FightResult	fr = new FightResult();

		int	heroA = this.attackOutput(),
			heroD = this.defenseOutput(),
			heroHP = this.hitPointsOutput(),
			heroMaxHP = this.getMaxHitPoints();

		int	villainA = villain.attackOutput(),
			villainD = villain.defenseOutput(),
			villainHP = villain.getHitPoints(),
			villainMaxHP = villainHP;

		fr.append(this.toString())
			.append("\n\tVERSUS\n")
			.append(villain.toString())
			.append("\n\n******* battle music playing *******\n\n");

		if (firstStrike == false) {
			heroHP -= villainA - heroD;
			fr.append(villain.getName())
				.append(" attacks !\n\t")
				.append(this.getName())
				.append(" HP: ")
				.append(heroHP)
				.append("/")
				.append(heroMaxHP)
				.append("\n");
		}

		while (heroHP > 0 && villainHP > 0) {
			villainHP -= heroA - villainD;
			fr.append(this.getName())
				.append(" attacks !\n\t")
				.append(villain.getName())
				.append(" HP: ")
				.append(villainHP)
				.append("/")
				.append(villainMaxHP)
				.append("\n");
			if (villainHP <= 0)
				break;

			heroHP -= villainA - heroD;
			fr.append(villain.getName())
				.append(" attacks !\n\t")
				.append(this.getName())
				.append(" HP: ")
				.append(heroHP)
				.append("/")
				.append(heroMaxHP)
				.append("\n");
		}

		fr.setWon(heroHP > 0 && villainHP <= 0);

		this.setHitPoints(heroHP);
		villain.setHitPoints(villainHP);

		return fr;
	}

	// @Override
	// public void	equip(Artifact artifact) {
	// 	String	artifactType = artifact.getClass().getSimpleName();

	// 	/*
	// 		If an artifact is already equipped
	// 		Ask player if he wants to replace ?
	// 	*/

	// 	switch (artifactType) {
	// 		case "Weapon":
	// 			this._weapon = (Weapon)artifact;
	// 		case "Armor":
	// 			this._armor = (Armor)artifact;
	// 		case "Helm":
	// 			this._helm = (Helm)artifact;
	// 		default:
	// 			;
	// 	}
	// }

	@Override
	public String	toString() {
		String	string = new StringBuilder(name)
			.append(", ").append(klass)
			.append(", level ").append(level)
			.append(", exp ").append(experience)
			.append(", attack ").append(attack)
			.append(", defense ").append(defense)
			.append(", hp ").append(hitPoints).append("/").append(maxHitPoints)
			.toString();
		return string;
	}

	// @Override
	public void	print() {
		// super.print();
		System.out.println(" | EXP " + this.experience);
	}

	public static class Builder extends Character.Builder< Hero > {
		@Override
		public Hero	build() {
			return new Hero(this);
		}
	}
}