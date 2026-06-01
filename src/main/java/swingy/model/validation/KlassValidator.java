package swingy.model.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public class KlassValidator extends InputValidator {
	private static final String klassArray[] = {
		"BERSERKER",
		"TANK",
		"RESILIENT"
	};

	@NotNull
	@Pattern(
		regexp = "^(1|2|3|BERSERKER|TANK|RESILIENT)$",
		message = "Class must be (either digit or string):\n\t1: BERSERKER (+1 ATT)\n\t2: TANK (+1 DEF)\n\t3: RESILIENT (+1 HP)")
	public String	data;

	private KlassValidator(String data) {
		if (data.matches("^(1|2|3)$"))
			this.data = this.klassArray[Integer.parseInt(data) - 1];
		else
			this.data = data.toUpperCase();
	}

	public String	getData() {
		return this.data;
	}

	public static KlassValidator	of(String data) throws ValidationException {
		KlassValidator	validator = new KlassValidator(data);
		validator.validate();
		return validator;
	}
}