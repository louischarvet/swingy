package swingy.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import swingy.view.View;
import swingy.model.Model; /////////////////

/**
 * Réagir aux résultats (ex : afficher une erreur ou rediriger).
 */

/**
 * INPUT TYPES
 * in game: N S E W MAP MENU(?)
 * menu: NEW LOAD QUIT
 * hero creation: <name> <class>
 */
public class Controller {
	// private static final List< String >	COMMANDS = new ArrayList<>(Arrays.asList(
	// 	"NEW",
	// 	"LOAD",
	// 	"QUIT",
	// 	"N",
	// 	"E",
	// 	"S",
	// 	"W",
	// 	"MAP",
	// 	"HELP"
	// ));
	private View	view = null; // to notify in case of exception/error
	private Model	model = null;
//	private Runnable	stateModel[3];

	public Controller(Model p_model, View p_view) {
		this.view = p_view;
		this.model = p_model;
	}

	public void	transmit(String input) {
//		System.out.println("in Controller: " + input);
		try {
			String	currentState = model.getCurrentState();
			String	upperInput = input.toUpperCase().trim();

			System.out.println("currentState: " + currentState);

			switch (currentState) {
				case "MAIN_MENU":
					System.out.println("Controller: in MAIN MENU");
					model.menu(upperInput);
					break;
				case "WAIT_NAME":
					System.out.println("Controller: in WAIT NAME");
					model.registerName(upperInput);
					break;
//					this.model.createHero(upperInput);
				case "WAIT_CLASS":
					model.registerClass(upperInput);
					break;
				case "CREATE_HERO":
					model.createHero(upperInput);
					break;
				case "IN_GAME":
					break;
//					this.model.game(upperInput);
			}
		} catch (Exception e) {
			view.error(e.getMessage());
		}
	}
}