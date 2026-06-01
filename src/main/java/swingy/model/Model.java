package swingy.model;

import java.util.Observable;

import swingy.model.NotificationArgument;

import swingy.model.validation.ValidationException;
import swingy.model.validation.MenuCommandValidator;
import swingy.model.validation.NameValidator;

public class Model extends Observable {
	private Status	status = null;
	private static Menu	menu;

	private	static final HeroSchema	heroSchema = new HeroSchema();

	public Model() {
		status = Status.MAIN_MENU;
		menu = new Menu();
		// heroSchema = new HeroSchema();
	}

	public Status	getStatus() {
		return status;
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

	public static void	menu(Model model, String input) throws ValidationException {
		String	command = MenuCommandValidator.of(input).getData();
		// System.out.println("in menu:" + input);

		switch (command) {
			case "NEW":
				model.menu.newGame();
			// case "LOAD":
			// 	menu.loadGame();
		}
	}

	public static void	registerName(Model model, String input) throws ValidationException {
		String	name = NameValidator.of(input).getData();
		heroSchema.setName(name);
		model.change(Status.WAIT_KLASS);
	}

	public static void	registerKlass(Model model, String input) {
		System.out.println("in registerKlass:" + input);
	}

	private class Menu {
		private void	newGame() {
			change(Status.WAIT_NAME);
		}
	}
}