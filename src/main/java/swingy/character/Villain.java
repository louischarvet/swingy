package swingy.character;

import java.util.List;
import java.util.ArrayList;

import swingy.artifact.Artifact;
import swingy.artifact.Weapon;
import swingy.artifact.Armor;
import swingy.artifact.Helm;

public class Villain extends Character {
	private Villain(Builder builder) {
		super(builder);

		this.weapon = builder.weapon;
		this.armor = builder.armor;
		this.helm = builder.helm;
	}

	public	List< Artifact >	dropArtifacts() {
		List< Artifact >	artifacts = new ArrayList<>();

		if (this.weapon != null)
			artifacts.add(this.weapon);
		if (this.armor != null)
			artifacts.add(this.armor);
		if (this.helm != null)
			artifacts.add(this.helm);

		return artifacts;
	}

	public static class Builder extends Character.Builder {
		private List< Artifact >	_artifacts;
		private Weapon	weapon = null;
		private Armor	armor = null;
		private Helm	helm = null;

		public Builder setArtifact(Artifact artifact) {
			String	artifactType = artifact.getClass().getSimpleName();

			switch (artifactType) {
				case "Weapon":
					this.weapon = (Weapon)artifact;
				case "Armor":
					this.armor = (Armor)artifact;
				case "Helm":
					this.helm = (Helm)artifact;
				default:
					;
			}

			return this;
		}

		@Override
		public Villain	build() {
			return new Villain(this);
		}
	}
}