package swingy.model.character;

import java.lang.StringBuilder;
import java.lang.Integer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

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

	public String	toString() {
		String	string = new StringBuilder(name)
			.append(", ").append(klass)
			.append(", level ").append(level)
			.append(", exp ").append(experience)
			.append(", attack ").append(attack)
			.append(", defense ").append(defense)
			.append(", hp ").append(hitPoints)
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