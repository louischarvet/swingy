package swingy.view;

import swingy.model.Model;

public final class ViewFactory {
	private ViewFactory() {
		super();
	}

	public static View	newView(String mode) {
		if (mode.equals("console"))
			return new ConsoleView();
		else if (mode.equals("gui"))
			return new GuiView();
		else
			return null;
	}

	// recycle ? gui -> console and console -> gui
}