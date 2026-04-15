package swingy.model;

import java.util.Observable;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import swingy.model.DatabaseManager;
import swingy.model.character.Character;
import swingy.model.character.Hero;

public class Model extends Observable {
	private DatabaseManager	databaseManager;
	private final List< String >	STATES = new ArrayList<>(Arrays.asList(
		"MAIN_MENU",		// expecting NEW LOAD ERASE QUIT HELP
			"WAIT_NAME",	// expecting <name> then <class>
			"WAIT_CLASS",
			"CREATE_HERO",
			"GAME_MANAGEMENT",	// LOAD or ERASE existing games // expecting game id

		"IN_GAME"			// expecting N E S W MAP HERO MENU
	));
	private String	currentState = null;
	private Hero	currentHero = null;
//	private String	toPrint = null; // resultat de la commande
	private String	heroName = null;
	private String	heroClass = null;

	public String	getCurrentState() {
		return this.currentState;
	}

	public Model() {
		this.databaseManager = new DatabaseManager();
		this.currentState = STATES.get(0);
		// ...
	}

	// Validate MAIN_MENU commands
	public void	menu(String input) {
		String	data = "popo";
		System.out.println("In Model: " + input);

		switch (input) {
			case "NEW":
				System.out.println("Model: in NEW");
				this.currentState = "WAIT_NAME";
				setChanged();
				notifyObservers("Creating new hero. Enter your name:");
				break;
			case "LOAD":
				System.out.println("Model: in LOAD");
				break;
			case "ERASE":
				break;
			case "QUIT":
				break;
			case "HELP":
				break;
		}
	//	notifyObservers(data);
	}

	// WAIT_NAME
	// validation in hero creation (instanciation)
	public void	registerName(String input) {
		this.heroName = input;
		currentState = "WAIT_CLASS";
		setChanged();
		notifyObservers("Choose your class:\n\t1: BERSERKER (+1 ATT)\n\t2: TANK (+1 DEF)\n\t3: RESILIENT (+1 HP)");
	}

	// WAIT_CLASS
	public void	registerClass(String input) {
		this.heroClass = input;
		currentState = "CREATE_HERO";
		setChanged();
		notifyObservers("Create this hero ?\n" + this.heroName + " " + this.heroClass);
	}

	// CREATE_HERO
	// validation: YES Y NO N
	public void	createHero(String input) {
		Character	hero = new Hero.Builder()
			.withName(this.heroName)
			.withKlass(this.heroClass)
			.withLevel(1)
			.build();
		databaseManager.insert((Hero)hero);
		this.currentHero = (Hero)hero;
		currentState = "IN_GAME";
	}

	// private static class mainMenu {
	// 	private String	heroName;

	// 	private mainMenu() {
	// 		super();
	// 	}

	// 	private void	newGame() {
	// 		currentState = "WAIT_NAME";
	// 		notifyObservers("Enter your name: ");
	// 		// wait for Name
	// 	}

	// 	private void	setHeroName(String heroName) {
	// 		this.heroName = heroName;
	// 		currentState = "NEW_CLASS";
	// 		notifyObservers("Choose your class:"); /////////
	// 	}

	// 	private class heroCreation {
	// 		private heroCreation() {
	// 			super();
	// 		}

	// 		public void	createHero(String name, String klass) {
	// 			Character	hero = new Hero.Builder()
	// 				.withName(name)
	// 				.withKlass(klass)
	// 				.withLevel(1)
	// 				.build();
	// 			databaseManager.insert((Hero)hero);
	// 		}
	// 	}
	// }
}