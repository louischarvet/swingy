package swingy.model.validation;

import jakarta.validation.constraints.Pattern;

public class ConfirmValidator extends InputValidator {
	@Pattern(
		regexp = "^(Y|YES|N|NO)$",
		flags = Pattern.Flag.CASE_INSENSITIVE,
		message = "Confirm ? (y/n)"
	)
	private String	data;
	private boolean	ok;

	private ConfirmValidator(String data) {
		this.data = data;
		this.ok = this.data.equalsIgnoreCase("Y")
			|| this.data.equalsIgnoreCase("YES");
	}

	public String	getData() {
		return this.data;
	}

	public boolean	isOk() {
		return this.ok;
	}

	public static ConfirmValidator	of(String data) throws ValidationException {
		ConfirmValidator	validator = new ConfirmValidator(data);
		validator.validate();
		return validator;
	}
}