package swingy.view;

import swingy.controller.Controller;
import swingy.model.Model;

public class ConsoleView extends View {
	// public GuiView(View view) {
	// 	// this = view
	// 	// copie des parametres en cas de changement de mode de view
	// }

	public ConsoleView(Model p_model) {
		super(p_model);
	}

	@Override
	public void	display() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println(this.OPENING_MESSAGE);
		// Welcome message + commands menu
		readInput();
	}

	@Override
	protected void	readInput() {
		String	input = new String("");
		int	readByte = 0;

		try {
			while (readByte != -1) { // readByte != '\n'
				readByte = System.in.read();
				input += readByte;		
			}
			Controller.transmit(input);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return ;
		}
	}

	@Override
	public void	error(String message) {
		System.out.println(message);
	}

	@Override
	public void	update() {
		switch (this.model.getCurrentState()) {
			case "MAIN_MENU":
				;
			case "HERO_CREATION":
				;
			case "IN_GAME":
				;
		}
	}
}