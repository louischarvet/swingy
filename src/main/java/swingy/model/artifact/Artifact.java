package swingy.model.artifact;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;

@Entity(name = "Artifact")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @Table(name = "artifact")
public abstract class Artifact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int	id;

	@Column(name = "name")
	private String	name;

	@Column(name = "level")
	private int	level;

	protected Artifact(Builder builder) {
		this.name = builder.name;
		this.level = builder.level;
	}

	public int	getId() {
		return this.id;
	}

	public String	getName() {
		return this.name;
	}

	public int	getLevel() {
		return this.level;
	}

	public void	setId(int id) {
		this.id = id;
	}

	public void	setName(String name) {
		this.name = name;
	}

	public void	setLevel(int level) {
		this.level = level;
	}

	public void	print() {
		System.out.println(
			this.getClass().getSimpleName()
			+ " " + this.getName()
			+ " LVL " + this.getLevel()
		);
	}

	public static abstract class Builder< T extends Artifact > {
		private String name;
		private int	level;

		public Builder< T >	withName(String p_name) {
			this.name = p_name;
			return this;
		}

		public Builder< T >	withLevel(int p_level) {
			this.level = p_level;
			return this;
		}

		public abstract T	build();
	}
}