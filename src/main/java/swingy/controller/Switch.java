package swingy.controller;

// import java.util.function.Consumer;
import java.util.Map;
import java.util.HashMap;

import swingy.model.Model;
import swingy.model.Status;
import swingy.model.ThrowingConsumer;

import swingy.model.validation.ValidationException;

public enum Switch {
	MAIN_MENU(Status.MAIN_MENU, Model::menu),
	WAIT_NAME(Status.WAIT_NAME, Model::registerName),
	WAIT_KLASS(Status.WAIT_KLASS, Model::registerKlass),
	// CONFIRM_CREATE(Status.CONFIRM_CREATE, Model::createHero),
	// IN_GAME(Status.IN_GAME, Model::game),
	// WAIT_LOAD(Status.WAIT_LOAD, Model::load)
	;

	private final Status	status;
	private final ThrowingConsumer< String, ValidationException >	function;
	private static final Map< Status, Switch >	BY_STATUS = new HashMap<>();

	static {
		for (Switch sw : values())
			BY_STATUS.put(sw.status, sw);
	}

	Switch(Status status, ThrowingConsumer< String, ValidationException > function) {
		this.status = status;
		this.function = function;
	}

	private void	execute(String input) throws ValidationException {
		function.accept(input);
	}

	public static void	execute(Status status, String input) throws ValidationException {
		Switch	sw = BY_STATUS.get(status);
		if (sw != null)
			sw.execute(input);
	}
}