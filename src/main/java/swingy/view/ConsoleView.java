package swingy.view;

import java.util.Observable;

import swingy.controller.Controller;
import swingy.model.Model;

import swingy.model.NotificationArgument;

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
		System.out.println(Printable.getMessage(0));
		// Welcome message + commands menu
		while (true) {
			System.out.print("\n\u001B[38;5;220m•\u001B[0m\u001B[38;5;130m-\u001B[0m\u001B[38;5;220mI\u001B[0m\u001B[38;5;255m==>\u001B[0m ");
			readInput();
		}
	//	update();
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
		System.out.print(message);
	}

	@Override
	public void	update(Observable model, Object data) {
		NotificationArgument	arg = (NotificationArgument)data;
		int code = arg.getStatusCode();
		String	info = arg.getInfo();
		String	message = Printable.getMessage(code);
		if (info != null)
			message = message.concat(info);

		System.out.println(message);
	}
}