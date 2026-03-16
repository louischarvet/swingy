package swingy.view;

import swingy.controller.Controller;

public class ConsoleView extends View {
	// public GuiView(View view) {
	// 	// this = view
	// 	// copie des parametres en cas de changement de mode de view
	// }

	@Override
	public void	display() {
		System.out.println("\f" + this.OPENING_MESSAGE);
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
			Controller.process(input);
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
		// JLabel	response = new JLabel();
		// panel.add(response);
	}
}