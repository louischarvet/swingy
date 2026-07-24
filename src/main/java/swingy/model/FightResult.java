package swingy.model;

import java.lang.StringBuilder;

public class FightResult {
	private boolean	won;
	private StringBuilder	report = new StringBuilder();

	public void	setWon(boolean w) {
		won = w;
		if (won == true)
			report.append("Enemy defeated !\n");
		else
			report.append("Game over...\n");
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
}