package swingy;

import java.util.Set;
import jakarta.validation.ConstraintViolation;

<<<<<<< HEAD
import swingy.view.View;
import swingy.view.ViewFactory;
=======
import swingy.database.DatabaseManager;

import swingy.map.SquareMapFactory;
import swingy.map.SquareMap;

import swingy.artifact.Artifact;
import swingy.artifact.Weapon;
import swingy.artifact.Armor;
import swingy.artifact.Helm;
>>>>>>> database

import swingy.model.Model;

import swingy.model.artifact.Artifact;
import swingy.model.artifact.Weapon;
import swingy.model.artifact.Armor;
import swingy.model.artifact.Helm;

import swingy.model.character.Character;
import swingy.model.character.Hero;

public class Main {
	public static void	main(String args[]) {
<<<<<<< HEAD
	// 	if (args.length != 2) {
	// 		System.out.println("Arguments needed: <name> <class>");
	// 		return;
	// 	}

	// //	ValidatorUtil	validator = new ValidatorUtil();

	// 	Character	hero = new Hero.Builder()
	// 		.setName(args[0])
	// 		.setClass(args[1])
	// 		.build();
		
	// 	Set< ConstraintViolation< Character > >	violations = ValidatorUtil.validate(hero);
	// 	if (!violations.isEmpty()) {
	// 		for (ConstraintViolation< Character > violation : violations) {
	// 			System.out.println("Error: " + violation.getMessage());
	// 		}
	// 		return ;
	// 	}
		
	// 	Artifact	weapon = new Weapon.Builder()
	// 		.setName("Dragonslayer")
	// 		.setLevel(1)
	// 		.build();

	// 	Artifact	armor = new Armor.Builder()
	// 		.setName("Dragonshield")
	// 		.setLevel(1)
	// 		.build();
	// 	Artifact	helm = new Helm.Builder()
	// 		.setName("Dragonhelm")
	// 		.setLevel(1)
	// 		.build();

	// 	hero.equip(weapon);
	// 	hero.equip(armor);
	// 	hero.equip(helm);
	// 	hero.print();

	// 	weapon.print();
	// 	armor.print();
	// 	helm.print();

		if (args.length != 1) {
			System.out.println("Argument needed: \"console\" or \"gui\"");
=======
		if (args.length != 3) {
			System.out.println("Arguments needed: <name> <class> <level>");
>>>>>>> database
			return;
		}

		Model	model = new Model();
		View	view = ViewFactory.newView(model, args[0]);
		if (view == null) {
			System.out.println("Argument needed: \"console\" or \"gui\"");
			return;
		}
		view.display();

		System.out.println("test");

<<<<<<< HEAD
		// while (true) {
		// 	// take input -- controller
		// 	// calculate -- model
		// 	// update -- view
		// }
=======
		System.out.println();

		DatabaseManager	databaseManager = new DatabaseManager();

		SquareMap	squareMap = SquareMapFactory.newSquareMap(Integer.parseInt(args[2]));
		squareMap.print();
>>>>>>> database
	}
}