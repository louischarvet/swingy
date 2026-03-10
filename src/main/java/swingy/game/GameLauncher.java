package swingy.game;

public class GameLauncher {
//	private String	mode;
	public Runnable	launch;

	// validation: "console" or "gui"
	public GameLauncher(String mode) {
	//	this.mode = p_mode;
		if (mode == "console")
			this.launch = this::consoleLaunch;
		else if (mode == "gui")
			this.launch = this::guiLaunch;
	}

	public void	launch() {
		this.launch.run();
	}

	private void consoleLaunch() {
		
	}
}