package swingy.view;

public enum Printable {
	MAIN_MENU("\tNEW -> Start the adventure with a new hero\n\tLOAD -> Select a previously created hero\n\tQUIT -> Quit the game (please don't)\n"),
	WAIT_NAME("\tWhat is your name, noble adventurer ?"),
	WAIT_KLASS("\tWhich class is yours ?\n\t1: BERSERKER (+1 ATT)\n\t2: TANK (+1 DEF)\n\t3: RESILIENT (+1 HP)"),
	CONFIRM_CREATE("\tCreate this hero ? (y/n)\n"),
	IN_GAME("Game started !\n");

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