package swingy.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import swingy.model.character.Hero;

public class Model {
	private final List< String >	STATES = new ArrayList<>(Arrays.asList(
		"MAIN_MENU",		// expecting NEW LOAD ERASE QUIT HELP
		"HERO_CREATION",	// expecting <name> then <class>
		"IN_GAME"			// expecting N E S W MAP HERO MENU
	));
	private String	currentState = null;
	private Hero	currentHero = null;
	private String	toPrint = null; // resultat de la commande

	public String	getCurrentState() {
		return this.currentState;
	}

	public Model() {
		this.currentState = STATES.get(0);
		// ...
	}

	// Validate MAIN_MENU commands
	public void	menu(String input) {
		switch (input) {
			case "NEW":
				this.currentState = "HERO_CREATION";
				this.toPrint = "Enter your hero's name:";
			case "LOAD":
				;
			case "ERASE":
				;
			case "QUIT":
				;
			case "HELP":
				;
		}
	}
}