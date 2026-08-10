package swingy.model.artifact;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Table;

@Entity(name = "Weapon")
@DiscriminatorValue(value = "Weapon")
// @Table(name = "weapon")
public class Weapon extends Artifact {
	private Weapon(Builder builder) {
		super(builder);
	}

	public static class Builder extends Artifact.Builder< Weapon > {
		// @Override
		public Weapon	build() {
			return new Weapon(this);
		}
	}
}