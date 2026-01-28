package swingy;

public class Hero extends Character {
	private int	_experience;

//	public Hero(String p_name, String p_class) {
	private Hero(Builder builder) {
		super(builder);
//		super(Builder(p_name, p_class));
//		super(new Builder()
//			.withName(p_name)
//			.withClass(p_class)
//		);

		this._experience = 0;
	}

	// @Override
	// public Character	Builder.build() {
	// 	return new Hero(this)
	// }

	public static class Builder extends Character.Builder {
		@Override
		public Hero	build() {
			return new Hero(this);
		}
	}
}