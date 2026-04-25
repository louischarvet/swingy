package swingy.model.validation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public class HeroNameDTO implements DTOInterface {
	@NotNull
	@Size(
		min = 1, 
		max = 15,
		message = "Name must be 1 to 10 characters long.")
	@Pattern(
		regexp = "^[A-Za-z0-9]+$",
		flags = Pattern.Flag.CASE_INSENSITIVE,
		message = "Name must be alphanumeric.")
	public String	name;

	public HeroNameDTO(String name) {
		this.name = name;
	}
}