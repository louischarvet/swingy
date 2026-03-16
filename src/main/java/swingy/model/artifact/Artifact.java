package swingy.artifact;

public abstract class Artifact {
	private final String	_name;
	private	final int	_level;

	protected Artifact(Builder builder) {
		this._name = builder._name;
		this._level = builder._level;
	}

	public String	getName() {
		return this._name;
	}

	public int	getLevel() {
		return this._level;
	}

	public void	print() {
		System.out.println(
			this.getClass().getSimpleName()
			+ " " + this.getName()
			+ " LVL " + this.getLevel()
		);
	}

	public static abstract class Builder {
		private String _name;
		private int	_level;

		public Builder	setName(String p_name) {
			this._name = p_name;
			return this;
		}

		public Builder	setLevel(int p_level) {
			this._level = p_level;
			return this;
		}

		public abstract Artifact	build();
	}
}