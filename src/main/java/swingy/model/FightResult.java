package swingy.model;

import java.lang.StringBuilder;

import swingy.model.artifact.Artifact;

public class FightResult {
	private int	won;
	private boolean	levelUp;
	private	Artifact	drop = null;
	private StringBuilder	report = new StringBuilder();

	public void	setWon(int w) {
		won = w;
		if (won > 0)
			report.append("Enemy defeated !\n");
		else if (won < 0)
			report.append("Game over...\n");
		else
			report.append("You managed to run from the enemy.\n");
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

	public int	getWon() {
		return won;
	} 

	public boolean	isLevelUp() {
		return levelUp;
	}

	public Artifact	getDrop() {
		return this.drop;
	}
}