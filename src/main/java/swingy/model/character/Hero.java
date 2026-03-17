package swingy.model.character;

import swingy.model.artifact.Artifact;
import swingy.model.artifact.Weapon;
import swingy.model.artifact.Armor;
import swingy.model.artifact.Helm;

public class Hero extends Character {
	private int	experience;

	private Hero(Builder builder) {
		super(builder);

		this.experience = 0;
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
	public void	print() {
		super.print();
		System.out.println(" | EXP " + this.experience);
	}

	public static class Builder extends Character.Builder {
		@Override
		public Hero	build() {
			return new Hero(this);
		}
	}
}