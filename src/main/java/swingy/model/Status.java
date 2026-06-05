package swingy.model;

public enum Status {
	MAIN_MENU(0),
	WAIT_NAME(1),
	WAIT_KLASS(2),
	CONFIRM_CREATE(3),
	WAIT_LOAD(4),
	WAIT_ERASE(5),
	GAME(6),
	QUIT(7);

	private final int	code;

	Status(int code) {
		this.code = code;
	}

	public int	getCode() {
		return code;
	}
}