package swingy.view;

import swingy.model.Model;

public final class ViewFactory {
	private ViewFactory() {
		super();
	}

	public static View	newView(Model model, String mode) {
		if (mode.equals("console"))
			return new ConsoleView(model);
		else if (mode.equals("gui"))
			return new GuiView(model);
		else
			return null;
	}

	// recycle ? gui -> console and console -> gui
}