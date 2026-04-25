package swingy.model.validation.dto;

import java.lang.Integer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class HeroKlassDTO implements DTOInterface {
	private final String klassArray[] = {
		"BERSERKER",
		"TANK",
		"RESILIENT"
	};

	@NotNull
	@Pattern(
		regexp = "^(BERSERKER|TANK|RESILIENT)$",
		message = "Class must be (either digit or string): 1 BERSERKER, 2 TANK, 3 RESILIENT")
	public String	klass;

	public HeroKlassDTO(String klass) {
		if (klass.equals("^(1|2|3)$"))
			this.klass = this.klassArray[Integer.parseInt(klass) - 1];
		else
			this.klass = klass;
	}
}