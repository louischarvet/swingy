package swingy.model.validation.dto;

import jakarta.validation.constraints.Pattern;

public class MenuCommandDTO implements DTOInterface {
	@Pattern(
		regexp = "^(NEW|LOAD|ERASE|QUIT|HELP)$",
		flags = Pattern.Flag.CASE_INSENSITIVE,
		message = "Invalid command.")
	public String	command;

	public MenuCommandDTO(String command) {
		this.command = command;
	}
}