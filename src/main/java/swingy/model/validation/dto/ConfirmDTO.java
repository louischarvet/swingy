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
	private boolean	ok;

	private ConfirmDTO(String data) {
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

	public static ConfirmDTO	of(String data) throws DTOException {
		ConfirmDTO	dto = new ConfirmDTO(data);
		dto.validate();
		return dto;
	}
}