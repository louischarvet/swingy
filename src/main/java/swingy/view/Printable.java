package swingy.view;

public enum Printable {
	MAIN_MENU("\tNEW -> Start the adventure with a new hero\n\tLOAD -> Select a previously created hero\n\tERASE -> Delete a saved hero\n\tQUIT -> Quit the game (please don't)\n"),
	WAIT_NAME("\tWhat is your name, noble adventurer ?"),
	WAIT_KLASS("\tWhich class is yours ?\n\t1: BERSERKER (+1 ATT)\n\t2: TANK (+1 DEF)\n\t3: RESILIENT (+3 HP)"),
	CONFIRM_CREATE("\tCreate this hero ? (y/n)\n"),
	WAIT_LOAD("Choose a game to load (\"CANCEL\" to go back to menu):\n"),
	WAIT_ERASE("Choose a game to erase (\"CANCEL\" to go back to menu):\n"),
	GAME(""), // map 
	CONFIRM_BACK_TO_MENU("Do you really want to go back to menu ? (y/n)\n"),
	WAIT_SAVE("Do you want to save your progression ? (y/n)\n"),
	LEVEL_FINISHED("You made your way out of the maze !\n"),
	GAME_OVER("GAME OVER\nRetry ? (y/n)\n"),
	QUIT("See you soon !\n");

	private final String	message;

	Printable(String message) {
		this.message = message;
	}

	public String	getMessage() {
		return message;
	}

	// check code ? validate ?
	public static String	getMessage(int code) {
		return values()[code].getMessage();
	}
}