package swingy.model;

import java.util.Observable;

import swingy.model.validation.ValidationException;
import swingy.model.validation.MenuCommandValidator;

public class Model extends Observable {
	private Status	status = null;

	public Model() {
		status = Status.MAIN_MENU;
	}

	public Status	getStatus() {
		return status;
	}

	public static void	menu(String input) throws ValidationException {
		String	command = MenuCommandValidator.of(input).getData();
		System.out.println("in menu:" + command);
	}

	public static void	registerName(String input) {
		System.out.println("in registerName:" + input);
	}

	public static void	registerKlass(String input) {
		System.out.println("in registerKlass:" + input);
	}
}