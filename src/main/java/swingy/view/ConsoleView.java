package swingy.view;

public class ConsoleView {
	public void	launch() {


		/**
		 * In controller ? InputManager
		 */
		String	input = new String("");
		int	readByte = -1;

		while (1) {
			readByte = System.in.read();
			if (readByte == -1 || readByte == '\n')
				break;
			else
				input += readByte;		
		}
	}
}