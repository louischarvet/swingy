package swingy.model.artifact;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Table;

@Entity(name = "Helm")
@DiscriminatorValue(value = "Helm")
// @Table(name = "helm")
public class Helm extends Artifact {
	private Helm(Builder builder) {
		super(builder);
	}

	public static class Builder extends Artifact.Builder< Helm > {
		// @Override
		public Helm	build() {
			return new Helm(this);
		}
	}
}