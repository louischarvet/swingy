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
	private static View	view = null; // to notify in case of exception/error
	private static Model	model = null;
//	private Runnable	stateModel[3];

	public Controller(Model p_model, View p_view) {
		this.view = p_view;
		this.model = p_model;
	}

	public static void	transmit(String input) {
		try {
			String	currentState = model.getCurrentState();
			String	upperInput = input.toUpperCase();

			switch (currentState) {
				case "MAIN_MENU":
					model.menu(upperInput);
				case "HERO_CREATION":
					;
//					this.model.createHero(upperInput);
				case "IN_GAME":
					;
//					this.model.game(upperInput);
			}
		} catch (Exception e) {
			view.error(e.getMessage());
		}
	}
}