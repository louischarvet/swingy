package swingy.model.artifact;

public class Armor extends Artifact {
	private Armor(Builder builder) {
		super(builder);
	}

	public static class Builder extends Artifact.Builder {
		@Override
		public Armor	build() {
			return new Armor(this);
		}
	}
}