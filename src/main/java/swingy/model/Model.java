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

import swingy.model.validation.exception.DTOException;
import swingy.model.validation.exception.ValidationException;
import swingy.model.validation.exception.CharacterValidationException;

import swingy.model.validation.dto.DTOInterface;
import swingy.model.validation.dto.MenuCommandDTO;
import swingy.model.validation.dto.HeroNameDTO;
import swingy.model.validation.dto.HeroKlassDTO;
import swingy.model.validation.dto.ConfirmDTO;

public class Model extends Observable {
	private static final Validator validator = Validation
		.buildDefaultValidatorFactory()
		.getValidator();
	private DatabaseManager	databaseManager;

	private Status	status = null;

	// private final List< String >	STATES = new ArrayList<>(Arrays.asList(
	// 	"MAIN_MENU",		// expecting NEW LOAD ERASE QUIT HELP
	// 		"WAIT_NAME",	// expecting <name> then <class>
	// 		"WAIT_KLASS",
	// 		"CONFIRM_CREATE",
	// 		"GAME_MANAGEMENT",	// LOAD or ERASE existing games // expecting game id

	// 	"START_GAME"			// expecting N E S W MAP HERO MENU
	// ));
	// private String	currentState = null;
	private Hero	currentHero = null;

	/**
	 * DTOs
	 */
	private MenuCommandDTO	menuCommand = null;
	private HeroNameDTO	heroName = null;
	private HeroKlassDTO	heroKlass = null;
	private ConfirmDTO	confirm = null;

	public Status	getStatus() {
		return status;
	}

	// public String	getCurrentState() {
	// 	return this.currentState;
	// }

	public Model() {
		this.databaseManager = new DatabaseManager();

		this.status = Status.MAIN_MENU;
	//	this.currentState = STATES.get(0);
		// ...
	}

	private void	validate(DTOInterface dto) throws Exception {
		Set< ConstraintViolation< Character > >	violations = validator.validate(this.currentHero);
		if (!violations.isEmpty())
			throw new CharacterValidationException(violations);
	}

	// Validate MAIN_MENU commands
	public void	menu(String input) throws Exception {
		// this.menuCommand = new MenuCommandDTO(input);
		// validate(this.menuCommand);
		this.menuCommand = MenuCommandDTO.of(input);
	//	String	data = "popo";
	//	System.out.println("In Model: " + input);

	// validate command ? private attribute ?

		switch (input) {
			case "NEW":
				this.change(Status.WAIT_NAME);
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

	private void	change(Status status) {
		this.status = status;
		setChanged();
		notifyObservers(new NotificationArgument(status.getCode()));
	}

	private void	change(Status status, String info) {
		this.status = status;
		setChanged();
		notifyObservers(new NotificationArgument(status.getCode(), info));
	}

	// WAIT_NAME
	public void	registerName(String input) throws DTOException {
		this.heroName = HeroNameDTO.of(input);
		this.change(Status.WAIT_KLASS);
	}

	// WAIT_CLASS
	public void	registerKlass(String input) throws DTOException {
		this.heroKlass = HeroKlassDTO.of(input);
		this.change(Status.CONFIRM_CREATE, new String(
			this.heroName.getData() + " " + this.heroKlass.getData()));
	}

	// CREATE_HERO
	// validation: YES Y NO N
	public void	createHero(String input) throws DTOException {
		this.confirm = ConfirmDTO.of(input);
		System.out.println("in createHero: " + input);
		
		if (this.confirm.isOk()) {
			this.currentHero = new Hero.Builder()
				.withName(this.heroName.getData())
				.withKlass(this.heroKlass.getData())
				.withLevel(1)
				.build();

			databaseManager.insert(this.currentHero);

			this.change(Status.IN_GAME); 
			// notifyObservers("The adventure begins !\nMove commands: N E S W\nOther commands:\n  SAVE (save your progression)\n  MAP (display level map)\n  HELP (display this message)");
		} else {
			this.currentHero = null;
			this.change(Status.MAIN_MENU);
		}
	}
}