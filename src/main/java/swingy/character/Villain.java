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

		this._weapon = builder._weapon;
		this._armor = builder._armor;
		this._helm = builder._helm;
	}

	public	List< Artifact >	dropArtifacts() {
		List< Artifact >	artifacts = new ArrayList<>();

		if (this._weapon != null)
			artifacts.add(this._weapon);
		if (this._armor != null)
			artifacts.add(this._armor);
		if (this._helm != null)
			artifacts.add(this._helm);

		return artifacts;
	}

	public static class Builder extends Character.Builder {
		private List< Artifact >	_artifacts;
		private Weapon	_weapon = null;
		private Armor	_armor = null;
		private Helm	_helm = null;

		public Builder setArtifact(Artifact artifact) {
			String	artifactType = artifact.getClass().getSimpleName();

			switch (artifactType) {
				case "Weapon":
					this._weapon = (Weapon)artifact;
				case "Armor":
					this._armor = (Armor)artifact;
				case "Helm":
					this._helm = (Helm)artifact;
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