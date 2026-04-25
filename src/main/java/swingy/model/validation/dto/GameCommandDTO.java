package swingy.model.validation.dto;

import jakarta.validation.constraints.Pattern;

public class GameCommandDTO implements DTOInterface {
	@Pattern(
		regexp = "^(N|E|S|W|MAP|HELP)$",
		flags = Pattern.Flag.CASE_INSENSITIVE,
		message = "Invalid command.")
	private String	command = null;

	public GameCommandDTO(String command) {
		this.command = command;
	}

	public String	getCommand() {
		return this.command;
	}
}