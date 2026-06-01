package swingy.model;

import java.lang.StringBuilder;

public class HeroSchema {
	private String	name;
	private String	klass;

	public void	setName(String name) {
		this.name = name;
	}

	public void	setKlass(String klass) {
		this.klass = klass;
	}

	public String	getName() {
		return name;
	}

	public String	getKlass() {
		return klass;
	}

	public String	toString() {
		return new StringBuilder(name)
			.append(", ")
			.append(klass)
			.toString();
	}
}