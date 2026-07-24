package swingy.model.validation;

import jakarta.validation.constraints.Pattern;

public class EncounterValidator extends InputValidator {
	@Pattern(
		regexp = "^(FIGHT|RUN)$",
		flags = Pattern.Flag.CASE_INSENSITIVE,
		message = "FIGHT (F) or RUN (R) ?"
	)
	private String	data;

	private EncounterValidator(String data) {
		if (data.equalsIgnoreCase("F"))
			this.data = "FIGHT";
		else if (data.equalsIgnoreCase("R"))
			this.data = "RUN";
		else
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