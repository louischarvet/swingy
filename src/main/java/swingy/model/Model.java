package swingy.model;

import java.util.Observable;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.ConstraintViolation;

import swingy.model.DatabaseManager;
import swingy.model.character.Character;
import swingy.model.character.Hero;

import swingy.model.validation.exception.ValidationException;
import swingy.model.validation.exception.CharacterValidationException;

import swingy.model.validation.dto.DTOInterface;
import swingy.model.validation.dto.MenuCommandDTO;
import swingy.model.validation.dto.HeroNameDTO;
import swingy.model.validation.dto.HeroKlassDTO;

public class Model extends Observable {
	private static final Validator validator = Validation
		.buildDefaultValidatorFactory()
		.getValidator();
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

	/**
	 * DTOs
	 */
	private MenuCommandDTO	menuCommand = null;
	private HeroNameDTO	heroName = null;
	private HeroKlassDTO	heroKlass = null;

	public String	getCurrentState() {
		return this.currentState;
	}

	public Model() {
		this.databaseManager = new DatabaseManager();
		this.currentState = STATES.get(0);
		// ...
	}

	private void	validate(DTOInterface dto) throws Exception {
		Set< ConstraintViolation< Character > >	violations = validator.validate(this.currentHero);
		if (!violations.isEmpty())
			throw new CharacterValidationException(violations);
	}

	// Validate MAIN_MENU commands
	public void	menu(String input) throws Exception {
		this.menuCommand = new MenuCommandDTO(input);
		validate(this.menuCommand);
	//	String	data = "popo";
	//	System.out.println("In Model: " + input);

	// validate command ? private attribute ?

		switch (input) {
			case "NEW":
			//	System.out.println("Model: in NEW");
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
	public void	registerName(String input) throws Exception {
		// Dto ? illogique de le build ici
		this.heroName = new HeroNameDTO(input);
		validate(this.heroName);

		// this.heroName = input;
		Set< ConstraintViolation< Character > >	violations = validator.validate(this.currentHero);
		if (!violations.isEmpty())
			throw new CharacterValidationException(violations);

		currentState = "WAIT_CLASS";
		setChanged();

		// notify: currentState ?
		notifyObservers("Choose your class:\n\t1: BERSERKER (+1 ATT)\n\t2: TANK (+1 DEF)\n\t3: RESILIENT (+1 HP)");
	}

	// WAIT_CLASS
	public void	registerClass(String input) throws Exception {
		this.heroKlass = new HeroKlassDTO(input);
		validate(this.heroKlass);

		currentState = "CREATE_HERO";
		setChanged();

		// notify: currentState ?
		notifyObservers("Create this hero ? (y/n)\n"
			+ this.currentHero.getName() + " "
			+ this.currentHero.getKlass());
	}

	// CREATE_HERO
	// validation: YES Y NO N
	public void	createHero(String input) {
		if (input.equals("Y") || input.equals("YES")) {
			// Character	hero = new Hero.Builder()
			// 	.withName(this.heroName)
			// 	.withKlass(this.heroClass)
			// 	.withLevel(1)
			// 	.build();

			databaseManager.insert(this.currentHero);
			currentState = "IN_GAME";
			setChanged();

			// notify: currentState ?
			notifyObservers("The adventure begins !\nMove commands: N E S W\nOther commands:\n  SAVE (save your progression)\n  MAP (display level map)\n  HELP (display this message)");
		} else if (input.equals("N") || input.equals("NO")) {
			this.currentHero = null;

			currentState = "MAIN_MENU";
			setChanged();

			// notify: currentState ?
			notifyObservers("menu_message // tmp");
		} else {
			setChanged();

			// notify: currentState ?
			notifyObservers("Unrecognized command.\nCreate this hero ? (y/n)\n"); /// ......
		}
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