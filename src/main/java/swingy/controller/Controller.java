package swingy.controller;

import swingy.view.View;
import swingy.model.Model;
import swingy.model.Status;

public class Controller {
	private View	view = null;
	private Model	model = null;

	public Controller(Model p_model, View p_view) {
		this.view = p_view;
		this.model = p_model;
	}

	public void	transmit(String input) {
		Status	status = model.getStatus();
		Switch.execute(status, input);
	}

	public void	registerView(View view) {
		this.view = view;
	}
}