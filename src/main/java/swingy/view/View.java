package swingy.view;

import java.util.Observer;

import swingy.controller.Controller;

public abstract class View implements Observer {
	protected static ViewChangeListener	listener = null;
	protected static Controller	controller = null;

	public static void setListener(ViewChangeListener p_listener) {
		listener = p_listener;
	}
	public static void	registerController(Controller p_controller) {
		controller = p_controller;
	}
	protected static void	transmit(String input) {
		controller.transmit(input);
	}

	public abstract void	launch();
	protected abstract void	display();
	protected abstract void	readInput();
	public abstract void	error(String message);
}