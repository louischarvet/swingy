package swingy.model;

import java.lang.StringBuilder;

import swingy.model.artifact.Artifact;

public class FightResult {
	private boolean	won;
	private boolean	levelUp;
	private	Artifact	drop = null;
	private StringBuilder	report = new StringBuilder();

	public void	setWon(boolean w) {
		won = w;
		if (won == true)
			report.append("Enemy defeated !\n");
		else
			report.append("Game over...\n");
	}

	public void	setLevelUp(boolean l) {
		levelUp = l;
	}

	public void	setDrop(Artifact d) {
		this.drop = d;
	}

	public FightResult	append(String str) {
		report.append(str);

		return this;
	}

	public FightResult	append(int i) {
		report.append(i);

		return this;
	}

	public String	toString() {
		return report.toString();
	}

	public boolean	isWon() {
		return won;
	}

	public boolean	isLevelUp() {
		return levelUp;
	}

	public Artifact	getDrop() {
		return this.drop;
	}
}