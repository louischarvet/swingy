package swingy.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.FlowLayout;

import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;

import swingy.controller.Controller;
import swingy.model.Model;

public class GuiView extends View {
	private JFrame	frame = null;
	private JTextField	textField = null;
	// public GuiView(View view) {
	// 	// this = view
	// 	// copie des parametres en cas de changement de mode de view
	// }

	public GuiView(Model p_model) {
		super(p_model);

		this.frame = new JFrame("Swingy");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(500, 500);
		// disposition gauche a droite
		this.frame.setLayout(new FlowLayout());

		frame.addWindowListener(new WindowAdapter() {
			public void	windowClosing(WindowEvent windowEvent) {
				System.out.println("Window Closed");
				System.exit(0);
			}
		});

		JPanel	panel = new JPanel(); // attribute ?

		this.textField = new JTextField(10);
		this.textField.addActionListener(e -> readInput());

		panel.add(this.textField);
		this.frame.getContentPane().add(panel);
	}

	@Override
	public void	display() {
		this.frame.setVisible(true);
	}

	@Override
	protected void	readInput() { // ActionEvent needed ?
		String	input = this.textField.getText();

		Controller.transmit(input);
		
		this.textField.setText("");
		this.textField.setColumns(10);
	}

	@Override
	public void	error(String message) {
		// display message
	}

	@Override
	public void	update() {
		// JLabel	response = new JLabel();
		// panel.add(response);
	}
}