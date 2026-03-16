package swingy.artifact;

public class Weapon extends Artifact {
	private Weapon(Builder builder) {
		super(builder);
	}

	public static class Builder extends Artifact.Builder {
		@Override
		public Weapon	build() {
			return new Weapon(this);
		}
	}
}