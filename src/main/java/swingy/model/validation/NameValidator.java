package swingy.model.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public class NameValidator extends InputValidator {
	@NotNull
	@Size(
		min = 1, 
		max = 15,
		message = "Name must be 1 to 10 characters long.")
	@Pattern(
		regexp = "^[A-Za-z0-9]+$",
		flags = Pattern.Flag.CASE_INSENSITIVE,
		message = "Name must be alphanumeric.")
	public String	data;

	public NameValidator(String data) {
		this.data = data;
	}

	@Override
	public String	getData() {
		return this.data;
	}

	public static InputValidator	of(String data) throws ValidationException {
		InputValidator	validator = new NameValidator(data);
		validator.validate();
		return validator;
	}
}