package swingy.model.validation;

import jakarta.validation.constraints.Pattern;

public class EncounterValidator extends InputValidator {
	@Pattern(
		regexp = "^(FIGHT|RUN)$",
		flags = Pattern.Flag.CASE_INSENSITIVE,
		message = "FIGHT or RUN ?"
	)
	private String	data;

	private EncounterValidator(String data) {
		this.data = data.toUpperCase();
	}

	@Override
	public String	getData() {
		return data;
	}

	public static EncounterValidator	of(String data) throws ValidationException {
		EncounterValidator	validator = new EncounterValidator(data);
		validator.validate();
		return validator;
	}
}