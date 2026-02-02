package swingy;

import swingy.artifact.Artifact;
import swingy.artifact.Weapon;
import swingy.artifact.Armor;
import swingy.artifact.Helm;

import swingy.character.Character;
import swingy.character.Hero;

public class Main {
	public static void	main(String args[]) {
		if (args.length != 2) {
			System.out.println("Arguments needed: <name> <class>");
			return;
		}

		Character	hero = new Hero.Builder()
			.setName(args[0])
			.setClass(args[1])
			.build();
		
		Artifact	weapon = new Weapon.Builder()
			.setName("Dragonslayer")
			.setLevel(1)
			.build();

		Artifact	armor = new Armor.Builder()
			.setName("Dragonshield")
			.setLevel(1)
			.build();
		Artifact	helm = new Helm.Builder()
			.setName("Dragonhelm")
			.setLevel(1)
			.build();

		hero.equip(weapon);
		hero.equip(armor);
		hero.equip(helm);
		hero.print();

		weapon.print();
		armor.print();
		helm.print();

	}
}