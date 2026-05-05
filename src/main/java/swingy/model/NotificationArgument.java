package swingy.model;

public class NotificationArgument {
	private String	state = null;
	private String	info = null;

	public NotificationArgument(String state) {
		this.state = state;
	}

	public NotificationArgument(String state, String info) {
		this.state = state;
		this.info = info;
	}

	public String	getState() {
		return this.state;
	}

	public String	getInfo() {
		return this.info;
	}
}