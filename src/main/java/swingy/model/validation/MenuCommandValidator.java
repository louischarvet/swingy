package swingy.model.validation;

import jakarta.validation.constraints.Pattern;

// import swingy.model.validation.exception.ValidationException;

public class MenuCommandValidator extends InputValidator {
	@Pattern(
		regexp = "^(NEW|LOAD|ERASE|QUIT|HELP)$",
		flags = Pattern.Flag.CASE_INSENSITIVE,
		message = "Invalid command.")
	private String	data;

	private MenuCommandValidator(String data) {
		this.data = data.toUpperCase();
	}

	@Override
	public String	getData() {
		return data;
	}

	public static MenuCommandValidator	of(String data) throws ValidationException {
		MenuCommandValidator	validator = new MenuCommandValidator(data);
		validator.validate();
		return validator;
	}
}