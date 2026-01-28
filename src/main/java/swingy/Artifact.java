package swingy;

public abstract class Artifact {
	private final String	_name;
//	private String	_class;
	private	final int	_level;

	protected Artifact(Builder builder) {
		this._name = builder._name;
		this._level = builder._level;
	}

//	public abstract void upgradeStat(); ///////

	public static class Builder {
		private String _name;
		private int	_level;

		public Builder	withName(String p_name) {
			this._name = p_name;
			return this;
		}

		public Builder	withLevel(int p_level) {
			this._level = p_level;
			return this;
		}
	}
}