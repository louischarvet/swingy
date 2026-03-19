package swingy.view;

import java.util.Observer;
import java.util.Observable;

import java.awt.event.ActionEvent;

import swingy.controller.Controller;
import swingy.model.Model;

public abstract class View implements Observer {
	protected final String	OPENING_MESSAGE = "Welcome to Swingy -- A completely revolutionnary text-based game ONLY for true GAMERS.\nEnter one of the following commands:\n\tNEW -> Start the adventure with a new hero\n\tLOAD -> Select a previously created hero\n\tQUIT -> Quit the game (please don't)\n";
	protected final String	HELP_MESSAGE = "In game:\n\tN/E/S/W -> move North, East, South or West\n\tMAP -> displays map\n\tHERO -> shows your stats and inventory\n";

	protected static Controller	controller = null;

	public static void	registerController(Controller p_controller) {
		controller = p_controller;
	}

	protected static void	transmit(String input) {
		controller.transmit(input);
	}

	public abstract void	display();
	protected abstract void	readInput();
	public abstract void	error(String message);
//	public abstract void	update(Observable o, Object arg); // from Observer
}