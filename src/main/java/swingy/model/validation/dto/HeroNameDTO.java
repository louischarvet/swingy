package swingy.model.validation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import swingy.model.validation.exception.DTOException;

public class HeroNameDTO extends DTO {
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

	public HeroNameDTO(String data) {
		this.data = data;
	}

	public String	getData() {
		return this.data;
	}

	public static HeroNameDTO	of(String data) throws DTOException {
		System.out.println("data in HeroNameDTO: " + data);

		HeroNameDTO	dto = new HeroNameDTO(data);
		dto.validate();
		return dto;
	}
}