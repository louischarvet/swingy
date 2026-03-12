package swingy.view;

public class GUIView {
	public void	launch() {
		JFrame	frame = new JFrame("Swingy");
		// fermeture de la fenetre = fermeture du programme
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLayout(new FlowLayout());

		WindowListener	windowListener = new WindowAdapter() {
			public void	windowClosing(WindowEvent windowEvent) {
				System.out.println("Window Closed");
				System.exit(0);
			}
		};
		// in Window, in Frame, in JFrame
		frame.addWindowListener(windowListener);

		JPanel	panel = new JPanel();

		/**
		 * JTextField: permet la saisie de texte
		 */
		JTextField	textField = new JTextField(10);
		panel.add(textField);

		JLabel	response = new JLabel();
		panel.add(response);

		textField.addActionListener(new ActionListener() {
			@Override
			public void	actionPerformed(ActionEvent event) {

				// validation needed here
				String	input = textField.getText();
				// go to controller: InputManager
				response.setText(input.toUpperCase());

				// reset textField
				textField.setText("");
				textField.setColumns(10);
			}
		});

		frame.getContentPane().add(panel);


// start tests menu

		// JMenuBar	menuBar = new JMenuBar();
		// frame.setJMenuBar(menuBar);

		// JMenu	menu = new JMenu("File");

		// JMenuItem	menuItem = new JMenuItem("Test");
		// menu.add(menuItem);

		// menuBar.add(menu);

// end tests menu

		frame.setVisible(true);
	}
}