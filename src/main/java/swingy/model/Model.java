package swingy.model;

import java.lang.StringBuilder;
import java.lang.Integer;

import java.util.Observable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import swingy.database.HibernateUtil;
import swingy.database.dao.HeroDAO;

import swingy.model.NotificationArgument;

import swingy.model.validation.ValidationException;
import swingy.model.validation.CancelException;

import swingy.model.validation.MenuCommandValidator;
import swingy.model.validation.NameValidator;
import swingy.model.validation.KlassValidator;
import swingy.model.validation.ConfirmValidator;
import swingy.model.validation.ChooseHeroValidator;
import swingy.model.validation.GameCommandValidator;

import swingy.model.character.Hero;

import swingy.model.map.SquareMapFactory;
import swingy.model.map.SquareMap;

public class Model extends Observable {
	private Status	status = null;
	private static Menu	menu = null;

	private	static final HeroSchema	heroSchema = new HeroSchema();
	private static HeroDAO	heroDAO = new HeroDAO();
	private static Hero	currentHero = null;
	private static SquareMap	currentMap = null;

	public Model() {
		status = Status.MAIN_MENU;
		menu = new Menu();
	}

	public Status	getStatus() {
		return status;
	}

	public Hero	getCurrentHero() {
		return currentHero;
	}

	public SquareMap	getCurrentMap() {
		return currentMap;
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

		switch (command) {
			case "NEW":
				model.menu.newGame();
				break;
			case "LOAD":
				model.menu.load(Status.WAIT_LOAD);
				break;
			case "ERASE":
				model.menu.load(Status.WAIT_ERASE);
				break;
			case "QUIT":
				model.menu.quit();
		}
	}

	public static void	game(Model model, String input) throws Exception {
		String	command = GameCommandValidator.of(input).getData();
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

	private void	setMap(SquareMap map) {
		currentMap = map;
	}

	public static void	registerName(Model model, String input) throws Exception {
		try {
			String	name = NameValidator.of(input).getData();
			model.setSchemaName(name);
			model.change(Status.WAIT_KLASS);
		} catch (CancelException e) {
			model.change(Status.MAIN_MENU, e.getMessage());
		} catch (Exception e) {
			throw e;
		}
	}

	public static void	registerKlass(Model model, String input) throws Exception {
		try {
			String	klass = KlassValidator.of(input).getData();
			model.setSchemaKlass(klass);
			model.change(Status.CONFIRM_CREATE, heroSchema.toString());
		} catch (CancelException e) {
			model.change(Status.MAIN_MENU, e.getMessage());
		} catch (Exception e) {
			throw e;
		}
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

			// startGame()
			model.setMap(SquareMapFactory.create(1, model.getCurrentHero()));
			model.currentHero.setPosition(model.getCurrentMap().getCenter()); //////
			model.change(Status.GAME, currentMap.toString());
		} else {
			model.setHero(null);
			model.change(Status.MAIN_MENU, "Hero creation cancelled.");
		}
	}

/**
 * Choose hero / game file
 * Load or Erase (check Status)
 */
	public static void	chooseGame(Model model, String input) throws Exception {
		try {
			int	index = ChooseHeroValidator.of(input, heroDAO.getListSize()).getIndex();

			if (model.getStatus() == Status.WAIT_LOAD) {
				model.currentHero = heroDAO.get(index - 1);

				// startGame()
				model.setMap(SquareMapFactory.create(model.currentHero.getLevel(), model.getCurrentHero()));
				model.currentHero.setPosition(model.getCurrentMap().getCenter()); //////
				model.change(Status.GAME, currentMap.toString());
			} else { // WAIT_ERASE
				heroDAO.erase(index - 1);
				model.change(Status.MAIN_MENU, "Hero has been deleted.");
			}
		} catch (CancelException e) {
			model.change(Status.MAIN_MENU, e.getMessage());
		} catch (Exception e) {
			throw e;
		}
	}

	private class Menu {
		private void	newGame() {
			change(Status.WAIT_NAME);
		}
		private void	load(Status status) throws Exception {
			List< Hero >	heroes = heroDAO.getAll();
			if (heroes == null || heroes.size() < 1)
				change(Status.MAIN_MENU, new String("There is no saved game."));
			else {
				StringBuilder	stringBuilder = new StringBuilder();

				int	i = 0;
				for (Hero hero : heroes)
					stringBuilder
						.append(Integer.toString(++i))
						.append(" : ")
						.append(hero.toString())
						.append("\n");

				change(status, stringBuilder.toString());
			}
		}
		private void	quit() {
			HibernateUtil.shutdown();
			change(Status.QUIT);
			System.exit(0);
		}
	}
}