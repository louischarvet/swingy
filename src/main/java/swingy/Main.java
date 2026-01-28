package swingy;

public class Main {
	public static void	main(String args[]) {
		if (args.length != 2) {
			System.out.println("Arguments needed: <name> <class>");
			return;
		}
//		Character	character = new Hero(args[0], args[1]);
		Character	hero = new Hero.Builder()
			.withName(args[0])
			.withClass(args[1])
			.build();

		hero.print();
	}
}