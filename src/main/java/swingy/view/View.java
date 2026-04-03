package swingy.view;

import java.awt.event.ActionEvent;

import swingy.model.Model;

public abstract class View {
	protected final String	OPENING_MESSAGE = "Welcome to Swingy -- A completely revolutionnary text-based game ONLY for true GAMERS.\nEnter one of the following commands:\n\tNEW -> Start the adventure with a new hero\n\tLOAD -> Select a previously created hero\n\tQUIT -> Quit the game (please don't)\n";
	protected final String	HELP_MESSAGE = "In game:\n\tN/E/S/W -> move North, East, South or West\n\tMAP -> displays map\n\tHERO -> shows your stats and inventory\n";
	protected Model	model = null;

	protected View(Model p_model) {
		this.model = p_model;
	}

	public abstract void	display();
	protected abstract void	readInput();
	public abstract void	error(String message);
	public abstract void	update();
}