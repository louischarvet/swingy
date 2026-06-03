package swingy.model;

import java.lang.StringBuilder;

import java.util.Observable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import swingy.database.HibernateUtil;
import swingy.database.dao.HeroDAO;

import swingy.model.NotificationArgument;

import swingy.model.validation.ValidationException;
import swingy.model.validation.MenuCommandValidator;

import swingy.model.validation.NameValidator;
import swingy.model.validation.KlassValidator;
import swingy.model.validation.ConfirmValidator;

import swingy.model.character.Hero;

public class Model extends Observable {
	private Status	status = null;
	private static Menu	menu;

	private	static final HeroSchema	heroSchema = new HeroSchema();
	private static HeroDAO	heroDAO = new HeroDAO();
	private static Hero	currentHero = null;

	public Model() {
		status = Status.MAIN_MENU;
		menu = new Menu();
		// heroSchema = new HeroSchema();
	}

	public Status	getStatus() {
		return status;
	}

	public Hero	getCurrentHero() {
		return currentHero;
	}

	private void	change(Status status) {
		this.status = status;
		setChanged();
		notifyObservers(new NotificationArgument(status.getCode()));
	}

	private void	change(Status status, String info) {
		this.status = status;
		setChanged();
		notifyObservers(new NotificationArgument(status.getCode(), info));
	}

	public static void	menu(Model model, String input) throws Exception {
		String	command = MenuCommandValidator.of(input).getData();
		// System.out.println("in menu:" + input);

		switch (command) {
			case "NEW":
				model.menu.newGame();
				break;
			case "LOAD":
				model.menu.loadGame();
				break;
		}
	}

	/**
	 * Hero Creation
	 */
	private void	setSchemaName(String name) {
		heroSchema.setName(name);
	}

	private void	setSchemaKlass(String klass) {
		heroSchema.setKlass(klass);
	}

	private void	setHero(Hero hero) {
		currentHero = hero;
	}

	public static void	registerName(Model model, String input) throws ValidationException {
		String	name = NameValidator.of(input).getData();
		model.setSchemaName(name);
		model.change(Status.WAIT_KLASS);
	}

	public static void	registerKlass(Model model, String input) throws ValidationException {
		String	klass = KlassValidator.of(input).getData();
		model.setSchemaKlass(klass);
		model.change(Status.CONFIRM_CREATE, heroSchema.toString());
	}

	public static void	createHero(Model model, String input) throws Exception {
		if (ConfirmValidator.of(input).isOk()) {
			model.setHero(
				new Hero.Builder()
					.withName(heroSchema.getName())
					.withKlass(heroSchema.getKlass())
					.withLevel(1)
					.build()
				);

			heroDAO.insert(model.getCurrentHero());

			model.change(Status.MAIN_MENU); // change to IN_GAME
		} else {
			model.setHero(null);
			model.change(Status.MAIN_MENU);
		}
	}

	private class Menu {
		private void	newGame() {
			change(Status.WAIT_NAME);
		}
		private void	loadGame() throws Exception {
			List< Hero >	heroes = heroDAO.getAll();
			StringBuilder	stringBuilder = new StringBuilder();

			for (Hero hero : heroes) {
				stringBuilder.append(hero.toString()).append("\n");
			}

			change(Status.WAIT_LOAD, stringBuilder.toString());
		}
	}
}