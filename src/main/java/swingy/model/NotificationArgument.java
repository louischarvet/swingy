package swingy.model;

// dto ?
public class NotificationArgument {
	// validate code ?
	private int	statusCode = 0;
	private String	info = null;

	public NotificationArgument(int statusCode) {
		this.statusCode = statusCode;
	}

	public NotificationArgument(int statusCode, String info) {
		this.statusCode = statusCode;
		this.info = info;
	}

	public int	getStatusCode() {
		return this.statusCode;
	}

	public String	getInfo() {
		return this.info;
	}
}