package swingy.view;

import java.util.Observable;

import swingy.controller.Controller;
import swingy.model.Model;

public class ConsoleView extends View {
	// public GuiView(View view) {
	// 	// this = view
	// 	// copie des parametres en cas de changement de mode de view
	// }

	public ConsoleView() {
		super();
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
			while (readByte != -1 && readByte != '\n') { // readByte != '\n'
				readByte = System.in.read();
				input += (char)readByte;		
			}
			transmit(input);
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
	public void	update(Observable model, Object data) {
		System.out.println(data);
	}
}