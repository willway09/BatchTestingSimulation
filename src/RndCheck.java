package src;

public class RndCheck {
	public static void main(String args[]) {
		System.out.println("Hello world");
		Rnd rnd = new Rnd();

		for(int i = 0; i < 20; i++) {
			boolean array[] = rnd.createShuffledArray(10, .3);

			for(boolean b : array) {
				System.out.print(b);
				System.out.print(", ");
			}
			System.out.println();
		}
		
	}
}
