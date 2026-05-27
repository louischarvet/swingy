package swingy.model;

import java.util.Observable;

public class Model extends Observable {
	private Status	status = null;

	public Model() {
		status = Status.MAIN_MENU;
	}

	public Status	getStatus() {
		return status;
	}

	public static void	menu(String input) {
		System.out.println("in menu:" + input);
	}

	public static void	registerName(String input) {
		System.out.println("in registerName:" + input);
	}

	public static void	registerKlass(String input) {
		System.out.println("in registerKlass:" + input);
	}
}