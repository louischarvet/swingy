package swingy.controller;

import swingy.view.View;
import swingy.model.Model;
import swingy.model.Status;

import swingy.model.validation.ValidationException;

public class Controller {
	private View	view = null;
	private Model	model = null;

	public Controller(Model p_model, View p_view) {
		this.view = p_view;
		this.model = p_model;
	}

	public void	transmit(String input) {
		Status	status = model.getStatus();
		try {
			Switch.execute(model, status, input.trim());
		} catch (Exception e) {
			view.error(e.getMessage());
		}
	}

	public void	registerView(View view) {
		this.view = view;
	}
}