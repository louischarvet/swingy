package swingy.character;

import swingy.artifact.Artifact;
import swingy.artifact.Weapon;
import swingy.artifact.Armor;
import swingy.artifact.Helm;

public class Hero extends Character {
	private int	_experience;

	private Hero(Builder builder) {
		super(builder);

		this._experience = 0;
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
		System.out.println(" | EXP " + this._experience);
	}

	public static class Builder extends Character.Builder {
		@Override
		public Hero	build() {
			return new Hero(this);
		}
	}
}