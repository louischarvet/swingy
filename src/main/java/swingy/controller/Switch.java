package swingy.controller;

// import java.util.function.Consumer;
import java.util.Map;
import java.util.HashMap;

import swingy.model.Model;
import swingy.model.Status;
import swingy.model.ThrowingBiConsumer;

import swingy.model.validation.ValidationException;

public enum Switch {
	MAIN_MENU(Status.MAIN_MENU, (model, input) -> model.menu(model, input)),
	WAIT_NAME(Status.WAIT_NAME, (model, input) -> model.registerName(model, input)),
	WAIT_KLASS(Status.WAIT_KLASS, (model, input) -> model.registerKlass(model, input)),
	CONFIRM_CREATE(Status.CONFIRM_CREATE, (model, input) -> model.createHero(model, input)),
	WAIT_LOAD(Status.WAIT_LOAD, (model, input) -> model.chooseGame(model, input)),
	WAIT_ERASE(Status.WAIT_ERASE, (model,input) -> model.chooseGame(model, input)),
	GAME(Status.GAME, (model, input) -> model.game(model, input)),
	CONFIRM_BACK_TO_MENU(Status.CONFIRM_BACK_TO_MENU, (model, input) -> model.backToMenu(model, input)),
	WAIT_SAVE(Status.WAIT_SAVE, (model, input) -> model.save(model, input)),
	LEVEL_FINISHED(Status.LEVEL_FINISHED, (model, input) -> model.finishLevel(model, input)),
	GAME_OVER(Status.GAME_OVER, (model, input) -> model.gameOver(model, input))
	// QUIT
	;

	private final Status	status;
	private final ThrowingBiConsumer< Model, String, Exception >	function;
	private static final Map< Status, Switch >	BY_STATUS = new HashMap<>();

	static {
		for (Switch sw : values())
			BY_STATUS.put(sw.status, sw);
	}

	Switch(Status status, ThrowingBiConsumer< Model, String, Exception > function) {
		this.status = status;
		this.function = function;
	}

	private void	execute(Model model, String input) throws Exception {
		function.accept(model, input);
	}

	public static void	execute(Model model, Status status, String input) throws Exception {
		Switch	sw = BY_STATUS.get(status);
		if (sw != null)
			sw.execute(model, input);
	}
}