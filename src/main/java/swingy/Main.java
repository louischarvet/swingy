package swingy;

import swingy.model.Model;

import swingy.view.View;
import swingy.view.ViewFactory;
import swingy.view.ConsoleView;
// import swingy.view.GuiView;
import swingy.view.ViewChangeListener;

import swingy.controller.Controller;

import swingy.database.HibernateUtil;

import swingy.model.map.SquareMapFactory;
import swingy.model.character.Hero;

public class Main implements ViewChangeListener {
	private static Model	model = null;
	private static View	view = null;
	private static Controller	controller = null;

	public static void	main(String args[]) {
		if (args.length != 1
			|| (!args[0].equals("console") && !args[0].equals("gui"))) {
			System.out.println("Usage: java -jar target/Swingy(...) <console or gui>");
			return;
		}

		//// test for map construction with villains
		// for (int i = 1; i <= 5; i++) {
		// 	Hero	hero = new Hero.Builder()
		// 		.withName("locharve")
		// 		.withKlass("TANK")
		// 		.withLevel(i)
		// 		.build();
		// 	System.out.println(SquareMapFactory.create(i, hero).toStringAllVisible());
		// }

		Main	main = new Main();

		main.model = new Model();
		main.view = ViewFactory.newView(args[0]);
		main.view.setListener(main);
		main.controller = new Controller(model, view);

		main.model.addObserver(view);
		main.view.registerController(controller);

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			HibernateUtil.shutdown();
        }));

		main.launch();
	}

	private static void launch() {
		view.launch();
	}

	@Override
	public void	onViewChange() {
		model.deleteObserver(view);

		if (view instanceof ConsoleView)
			view = ViewFactory.newView("gui");
		else
			view = ViewFactory.newView("console");

		model.addObserver(view);
		view.setListener(this);
		controller.registerView(view);
	}
}