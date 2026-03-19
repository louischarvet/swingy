package swingy;

import java.util.Set;
import jakarta.validation.ConstraintViolation;

import swingy.controller.Controller;

import swingy.view.View;
import swingy.view.ViewFactory;

import swingy.model.Model;

import swingy.model.artifact.Artifact;
import swingy.model.artifact.Weapon;
import swingy.model.artifact.Armor;
import swingy.model.artifact.Helm;

import swingy.model.character.Character;
import swingy.model.character.Hero;

public class Main {
	public static void	main(String args[]) {
		if (args.length != 1) {
			System.out.println("Argument needed: \"console\" or \"gui\"");
			return;
		}

		Model	model = new Model();
		View	view = ViewFactory.newView(args[0]);
		if (view == null) {
			System.out.println("Argument needed: \"console\" or \"gui\"");
			return;
		}
		model.addObserver(view);
		view.registerController(new Controller(model, view));

		view.display();

//		System.out.println("test");

		// while (true) {
		// 	// take input -- controller
		// 	// calculate -- model
		// 	// update -- view
		// }
//		System.out.println();

		// SquareMap	squareMap = SquareMapFactory.newSquareMap(Integer.parseInt(args[2]));
		// squareMap.print();
	}
}