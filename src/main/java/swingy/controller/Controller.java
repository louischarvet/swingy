package swingy.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import swingy.view.View;
import swingy.model.Model; /////////////////
import swingy.model.validation.exception.ValidationException;

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
	// SAVE ?????
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
			String	upperInput = input.trim();

		//	System.out.println("currentState: " + currentState);

			switch (currentState) {
				case "MAIN_MENU":
					// System.out.println("Controller: in MAIN MENU");
					// move toUpperCase() in Model
					// check in Model DTOs
					model.menu(input.toUpperCase().trim());
					break;
				case "WAIT_NAME":
					// System.out.println("Controller: in WAIT NAME");
					model.registerName(input.trim());
					break;
//					this.model.createHero(upperInput);
				case "WAIT_CLASS":
					model.registerClass(input.toUpperCase().trim());
					break;
				case "CREATE_HERO":
					model.createHero(input.toUpperCase().trim());
					break;
				case "IN_GAME":
					break;
//					this.model.game(upperInput);
			}
		} catch (Exception e) {
			view.error(e.getMessage());
			// view.error(e.getMessages());
		}
	}
}