// package swingy.model;

// // import java.lang.Runnable;
// import java.util.function.Supplier;
// import java.util.function.Consumer;

// import java.util.Map;
// import java.util.HashMap;

// import swingy.model.Model;

// // MenuCommandSwitch
// public enum Menu {
// 	NEW("NEW", () -> return Model::change);

// 	private final String	value;
// 	private final Supplier< Consumer< Status > >	function;
// 	private static final Map< Status, Switch >	BY_VALUE = new HashMap<>();
// 	static {
// 		for (Menu m : values())
// 			BY_VALUE.put(m.value, m);
// 	}

// 	public Menu(String value, Supplier< Consumer< Status > > function) {
// 		this.value = value;
// 		this.function = function;
// 	}

// 	private void	execute() {
// 		this.function.run();
// 	}

// 	public void execute(String value) {
// 		Menu	menu = BY_VALUE.get(value);
// 		if (menu != null)
// 			menu.execute();
// 	}
// }