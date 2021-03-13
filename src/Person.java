package src;

class Person {
	private static Rnd rnd = new Rnd();
	private boolean state;
	private boolean cleared = false;

	public Person(boolean state) {
		this.state = state;
	}

	public boolean getState() {
		return state;
	}

	public String toString() {
		return "" + state;
	}
	public void clear() {
		cleared = true;
	}
	public boolean isCleared() {
		return cleared;
	}
	public static Person[] createPersonsArray(int N, double p) {
		Person persons[] = new Person[N];

		boolean[] shuffledArray = rnd.createShuffledArray(N, p);

		for(int i = 0; i < N; i++) {
			persons[i] = new Person(shuffledArray[i]);
		}


		return persons;


	}

}
