package swingy.model.validation;

import java.lang.Exception;

public class CancelException extends Exception {
	private final String	message = "Action cancelled: back to menu.\n";

	@Override
	public String	getMessage() {
		return message;
	}
}