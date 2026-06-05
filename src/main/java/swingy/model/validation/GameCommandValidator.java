package swingy.model.validation;

import jakarta.validation.constraints.Pattern;

public class GameCommandValidator extends InputValidator {
	@Pattern(
		regexp = "^(N|E|S|W|MENU|QUIT|HELP)$",
		flags = Pattern.Flag.CASE_INSENSITIVE,
		message = "Unrecognized game command.")
	private String	data;

	private GameCommandValidator(String data) {
		this.data = data.toUpperCase();
	}

	@Override
	public String	getData() {
		return data;
	}

	public static GameCommandValidator	of(String data) throws ValidationException {
		GameCommandValidator	validator = new GameCommandValidator(data);
		validator.validate();
		return validator;
	}
}