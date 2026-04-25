package swingy.model.validation.dto;

import jakarta.validation.constraints.Pattern;

public class MenuCommandDTO extends DTO {
	@Pattern(
		regexp = "^(NEW|LOAD|ERASE|QUIT|HELP)$",
		flags = Pattern.Flag.CASE_INSENSITIVE,
		message = "Invalid command.")
	public String	data;

	private MenuCommandDTO(String data) {
		this.data = data;
	}

	public String	getData() {
		return this.data;
	}

	public static MenuCommandDTO	of(String data) throws Exception {
		System.out.println("data in MenuCommandDTO: " + data);

		MenuCommandDTO	dto = new MenuCommandDTO(data);
		dto.validate();
		return dto;
	}
}