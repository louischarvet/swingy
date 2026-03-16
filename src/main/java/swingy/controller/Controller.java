package swingy.controller;

import swingy.view.View;
import swingy.model.*; /////////////////

/**
 * Réagir aux résultats (ex : afficher une erreur ou rediriger).
 */

public class Controller {
	private static View	view = null; // to notify in case of exception/error

	public Controller() {}

	public void	registerView(View p_view) {
		this.view = p_view;
	}

	public static void	process(String input) {
		try {
			// ...
		} catch (Exception e) {
			// envoyer l'exception entiere ?
			view.error(e.getMessage());
		}
	}
}