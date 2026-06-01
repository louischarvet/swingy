package swingy.model;

import java.util.Observable;

import swingy.model.NotificationArgument;

import swingy.model.validation.ValidationException;
import swingy.model.validation.MenuCommandValidator;
import swingy.model.validation.NameValidator;

public class Model extends Observable {
	private Status	status = null;
	private static Menu	menu = null;

	public Model() {
		status = Status.MAIN_MENU;
		menu = new Menu();
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

	public static void	menu(String input) throws ValidationException {
		String	command = MenuCommandValidator.of(input).getData();
		// System.out.println("in menu:" + input);

		switch (command) {
			case "NEW":
				menu.newGame();
			// case "LOAD":
			// 	menu.loadGame();
		}

	}

	public static void	registerName(String input) throws ValidationException {
		String	name = NameValidator.of(input).getData();
		System.out.println("in registerName:" + input);
	}

	public static void	registerKlass(String input) {
		System.out.println("in registerKlass:" + input);
	}

	private class Menu {
		private void	newGame() {
			change(Status.WAIT_NAME);
		}
	}
}