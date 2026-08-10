package swingy.model.artifact;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Table;

@Entity(name = "Armor")
@DiscriminatorValue(value = "Armor")
// @Table(name = "armor")
public class Armor extends Artifact {
	private Armor(Builder builder) {
		super(builder);
	}

	public static class Builder extends Artifact.Builder< Armor > {
		// @Override
		public Armor	build() {
			return new Armor(this);
		}
	}
}