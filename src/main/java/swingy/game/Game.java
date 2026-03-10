package swingy.game;

import javax.swing.JFrame;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game {
	public Runnable	launch;

	public Game() {
		this.launch = this::consoleLaunch;
	}

	// validation: "console" or "gui"
	public Game(String mode) {
		if (mode.equals("console"))
			this.launch = this::consoleLaunch;
		else if (mode.equals("gui"))
			this.launch = this::guiLaunch;
	}

	public void	launch() {
		this.launch.run();
	}

	private void	consoleLaunch() {
		guiLaunch();
	}

	private void	guiLaunch() {
		JFrame	jFrame = new JFrame("Swingy");
		WindowListener	windowListener = new WindowAdapter() {
			public void	windowClosing(WindowEvent windowEvent) {
				System.out.println("Window Closing");
				System.exit(0);
			}
		};
		// in Window, in Frame, in JFrame
		jFrame.addWindowListener(windowListener);
		jFrame.setSize(200, 100);
		jFrame.setVisible(true);
	}
}