package swingy.model.character;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import swingy.model.artifact.Artifact;
import swingy.model.artifact.Weapon;
import swingy.model.artifact.Armor;
import swingy.model.artifact.Helm;

@Entity
@Table(name = "villain")
public class Villain extends Character {
	private Villain(Builder builder) {
		super(builder);

		this.weapon = builder.weapon;
		this.armor = builder.armor;
		this.helm = builder.helm;
	}

	public	List< Artifact >	dropArtifacts() {
		List< Artifact >	artifacts = new ArrayList<>();

		// if (this.weapon != null)
		// 	artifacts.add(this.weapon);
		// if (this.armor != null)
		// 	artifacts.add(this.armor);
		// if (this.helm != null)
		// 	artifacts.add(this.helm);

		return artifacts;
	}

	@Override
	public String	toString() {
		String	string = new StringBuilder(name)
			.append(", ").append(klass)
			.append(", level ").append(level)
			.append(", attack ").append(attack)
			.append(", defense ").append(defense)
			.append(", hp ").append(hitPoints)
			.toString();
		return string;
	}

	public static class Builder extends Character.Builder< Villain > {
		private List< Artifact >	artifacts;
		private Weapon	weapon = null;
		private Armor	armor = null;
		private Helm	helm = null;

		public Builder withArtifact(Artifact artifact) {
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

	//	@Override
		public Villain	build() {
			return new Villain(this);
		}
	}
}