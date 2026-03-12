package swingy.game;

import javax.swing.SwingConstants;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.FlowLayout;

import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;

public class Game {
	public Runnable	launch;

	public Game() {
		this.launch = this::consoleLaunch;
	}

	// validation: "console" or "gui"
	public Game(String mode) {
		if (mode.equals("console"))
			this.launch = this::consoleLaunch;
		else if (mode.equals("gui"))
			this.launch = this::guiLaunch;
	}

	public void	launch() {
		this.launch.run();
	}

	private void	consoleLaunch() {
		guiLaunch();
	}

	private void	guiLaunch() {
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
				// go to controller
				response.setText(input.toUpperCase());

				// reset textField
				textField.setText("");
				textField.setColumns(10);
			}
		});

		frame.getContentPane().add(panel);


// start tests menu

		JMenuBar	menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu	menu = new JMenu("File");

		JMenuItem	menuItem = new JMenuItem("Test");
		menu.add(menuItem);

		menuBar.add(menu);

// end tests menu

		frame.setVisible(true);
	}
}