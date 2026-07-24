package swingy.model;

import java.lang.StringBuilder;
import java.lang.Integer;

import java.util.Observable;
import java.util.List;
import java.util.Random;

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
import swingy.model.validation.EncounterValidator;

import swingy.model.character.Hero;
import swingy.model.character.Villain;

import swingy.model.map.SquareMapFactory;
import swingy.model.map.SquareMap;
import swingy.model.map.Tile;

public class Model extends Observable {
	private Status	status = null;
	private static Menu	menu = null;
	private static Game	game = null;

	private	static final HeroSchema	heroSchema = new HeroSchema();
	private static HeroDAO	heroDAO = new HeroDAO();

	private static Hero	currentHero = null;
	private static SquareMap	currentMap = null;
	private static Villain	currentVillain = null;

	public Model() {
		status = Status.MAIN_MENU;
		menu = new Menu();
		game = new Game();
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
		String	name = NameValidator.of(input).getData();
		model.setSchemaName(name);
		model.change(Status.WAIT_KLASS);
	}

	public static void	registerKlass(Model model, String input) throws Exception {
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

			// startGame()
			model.game.start();
			// model.setMap(SquareMapFactory.create(1, model.getCurrentHero()));
			// model.currentHero.setPosition(model.getCurrentMap().getCenter()); //////
			// model.change(Status.GAME, currentMap.toString());
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
				model.game.start();
				// model.setMap(SquareMapFactory.create(model.currentHero.getLevel(), model.getCurrentHero()));
				// model.currentHero.setPosition(model.getCurrentMap().getCenter()); //////
				// model.change(Status.GAME, currentMap.toString());
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

	public static void	game(Model model, String input) throws Exception {
		String	command = GameCommandValidator.of(input).getData();

		switch (command) {
			case "MENU":
				game.backToMenu(); // confirm ? save ?
				break;
			case "MAP":
				game.showMap();
				break;
			case "HERO":
				game.showHero();
				break;
			case "HELP":
				game.help();
				break;
			default: // moves
				game.move(command);
				break;
		}
	}

	public static void	backToMenu(Model model, String input) throws Exception {
		if (ConfirmValidator.of(input).isOk())
			model.game.askSave();
		else
			model.change(Status.GAME);
	}

	public static void	save(Model model, String input) throws Exception {
		if (ConfirmValidator.of(input).isOk())
			heroDAO.update(currentHero);
		model.change(Status.MAIN_MENU);
	}

	public static void	finishLevel(Model model, String input) throws Exception {
		/// to do

	}

	public static void	encounter(Model model, String input) throws Exception {
		String	data = EncounterValidator.of(input).getData();

		if (data.equals("FIGHT"))
			model.fight(true);
		else
			model.run();
	}

	private void	fight(boolean firstStrike) {
		StringBuilder	sb = new StringBuilder();

		if (firstStrike == false)
			sb.append("You couldn't escape from the enemy...\n");

		FightResult	fr = currentHero.fight(currentVillain, firstStrike);
		if (fr.isWon() == true) {
			Tile	newPosition = currentVillain.getPosition();

			// artifact !

			newPosition.setOnThis(currentHero);
			currentMap.setVisibility(newPosition.getY(), newPosition.getX());
			currentHero.getPosition().setOnThis(null);
			currentHero.setPosition(newPosition);

			currentVillain = null;

			sb.append(fr.toString());
			change(Status.GAME, sb.toString());
		} else {
			sb.append(fr.toString());
			change(Status.GAME_OVER, sb.toString());
		}
	}

	private void	run() {
		Random	random = new Random();

		if (random.nextInt() % 2 == 1) {
			fight(false);
		} else {
			currentVillain = null;
			change(Status.GAME, "You escaped the enemy.");
		}
	}

	public static void	gameOver(Model model, String input) throws Exception {
		if (ConfirmValidator.of(input).isOk()) {
			// System.out.println("in gameOver: hero:");
			// System.out.println(model.currentHero.toString());
			model.currentHero = heroDAO.getById(model.currentHero.getId());
			model.currentVillain = null;
			model.game.start();
		} else {
			model.clear();
			model.change(Status.MAIN_MENU);
		}
	}

	private static void	clear() {
		currentHero = null;
		currentMap = null;
		currentVillain = null;
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
			// HibernateUtil.shutdown();
			change(Status.QUIT);
			System.exit(0);
		}
	}

	private class Game {
		private void	start() {
			// currentHero.gainExperience(42); ///// test OK
			setMap(SquareMapFactory.create(currentHero.getLevel(), currentHero));
			currentHero.setPosition(getCurrentMap().getCenter()); //////
			change(Status.GAME, currentMap.toString());
		}
		private void	backToMenu() {
			change(Status.CONFIRM_BACK_TO_MENU);
		}
		private void	askSave() {
			change(Status.WAIT_SAVE, currentHero.toString());
		}
		private void	showMap() {
			change(status, currentMap.toString());
		}
		private void	showHero() {
			change(status, currentHero.toString());
		}
		private void	help() {
			change(status, "N -> move North\nE -> move East\nS -> move South\nW -> move West\nMAP -> display map\nHERO -> display hero stats\nMENU -> back to menu\nHELP -> display this message");
		}
		private void	move(String command) {
			int	y = currentHero.getPosition().getY();
			int	x = currentHero.getPosition().getX();

			switch (command) {
				case "N":
					y--;
					break;
				case "E":
					x++;
					break;
				case "S":
					y++;
					break;
				case "W":
					x--;
					break;
				default:
					return;
			}

			Tile	newPosition = currentMap.getTile(y, x);
			currentVillain	= (Villain)newPosition.getOnThis();
			if (newPosition == null) // hero.position == null ?
				change(Status.LEVEL_FINISHED);
			else if (newPosition.getValue() == 1) // wall
				change(status, "Moving here is impossible: there is an obstacle !");
			else if (currentVillain != null) {
				change(Status.ENCOUNTER, currentVillain.toString());
			//	change(Status.FIGHT, newPosition.getOnThis().toString());
			//	System.out.println(newPosition.getOnThis().toString());



			// Fight ....later
/*				if (currentHero.fight((Villain)(newPosition.getOnThis())) == true) {
					newPosition.setOnThis(currentHero);
					currentMap.setVisibility(y, x);
					currentHero.getPosition().setOnThis(null);
					currentHero.setPosition(newPosition);

					// if Villain has artifact: drop

					StringBuilder	sb = new StringBuilder()
						.append("Enemy defeated !\nHero's HP = ")
						.append(currentHero.getHitPoints())
						.append("/")
						.append(currentHero.getMaxHitPoints())
						.append("\n")
						.append(currentMap.toString());

					change(status, sb.toString());
				} else {
					change(Status.GAME_OVER);
				}
*/
			}
			else {
				newPosition.setOnThis(currentHero);

				currentMap.setVisibility(y, x);
				currentHero.getPosition().setOnThis(null);
				currentHero.setPosition(newPosition);
				// if Villain on newPosition: Status.FIGHT
				change(status, currentMap.toString());
			}
		}
	}
}