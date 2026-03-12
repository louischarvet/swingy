package swingy.controller;

public class InputManager {
	// console Read -> Runnable ?
	private String	ConsoleRead() {
		String	input = new String("");
		int	readByte = -1;

		while (1) {
			readByte = System.in.read();
			if (readByte == -1 || readByte == '\n')
				break;
			else
				input += readByte;		
		}
		process(input);
	}

	private String	GUIRead() {
		
	}

	/**
	 * Validation needed here
	 */
	private void	process(String input) {

	}
}