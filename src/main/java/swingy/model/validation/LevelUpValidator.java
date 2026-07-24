package swingy.model.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public class LevelUpValidator extends InputValidator {
	@NotNull
	@Pattern(
		regexp = "^(1|2|3)$",
		message = "\t1: ATTACK +1\n\t2: DEFENSE +1\n\t3: HP +1\n")
	public String	data;

	private LevelUpValidator(String data) {
		this.data = data;
	}

	public String	getData() {
		return this.data;
	}

	public static LevelUpValidator	of(String data) throws Exception {
		LevelUpValidator	validator = new LevelUpValidator(data);
		validator.validate();
		return validator;
	}
}