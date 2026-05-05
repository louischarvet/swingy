package swingy.view;

import java.util.Observer;
import java.util.Observable;
import java.util.Map;

import java.awt.event.ActionEvent;

import swingy.controller.Controller;
import swingy.model.Model;

public abstract class View implements Observer {
	protected final Map< String, String >	stateMessages = Map.of(
		"MAIN_MENU", "\tNEW -> Start the adventure with a new hero\n\tLOAD -> Select a previously created hero\n\tQUIT -> Quit the game (please don't)\n",
		"WAIT_NAME", "\tCreating new hero. Enter your name:\n",
		"WAIT_KLASS", "\tChoose your class:\n\t1: BERSERKER (+1 ATT)\n\t2: TANK (+1 DEF)\n\t3: RESILIENT (+1 HP)",
		"CONFIRM_CREATE", "\tCreate this hero ? (y/n)\n",
		"START_GAME", "\tGame started !\n"
	);
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