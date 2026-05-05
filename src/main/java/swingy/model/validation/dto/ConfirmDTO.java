package swingy.model.validation.dto;

import jakarta.validation.constraints.Pattern;

import swingy.model.validation.exception.DTOException;

public class ConfirmDTO extends DTO {
	@Pattern(
		regexp = "^(Y|YES|N|NO)$",
		flags = Pattern.Flag.CASE_INSENSITIVE,
		message = "Confirm ? (y/n)"
	)
	private String	data;

	private ConfirmDTO(String data) {
		this.data = data;
	}

	public String	getData() {
		return this.data;
	}

	public static ConfirmDTO	of(String data) throws DTOException {
		ConfirmDTO	dto = new ConfirmDTO(data);
		dto.validate();
		return dto;
	}
}