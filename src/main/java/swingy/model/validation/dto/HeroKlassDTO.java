package swingy.model.validation.dto;

import java.lang.Integer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import swingy.model.validation.exception.DTOException;

public class HeroKlassDTO extends DTO {
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

	private HeroKlassDTO(String data) {
		if (data.matches("^(1|2|3)$"))
			this.data = this.klassArray[Integer.parseInt(data) - 1];
		else
			this.data = data.toUpperCase();
	}

	public String	getData() {
		return this.data;
	}

	public static HeroKlassDTO	of(String data) throws DTOException {
		HeroKlassDTO	dto = new HeroKlassDTO(data);
		dto.validate();
		return dto;
	}
}