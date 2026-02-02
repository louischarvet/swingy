package swingy.artifact;

public class Helm extends Artifact {
	private Helm(Builder builder) {
		super(builder);
	}

	public static class Builder extends Artifact.Builder {
		@Override
		public Helm	build() {
			return new Helm(this);
		}
	}
}