package src;

import java.util.Random;

class Rnd {

	static {
		System.loadLibrary("Rnd");
	}

	public static Random rnd = new Random();

	/*public static int[] generateSwapArray(int N, int swapCount) {

		int rtn[] = new int[2 * swapCount];

		for(int i = 0; i < rtn.length; i++) {
			rtn[i] = rnd.nextInt(N);
		}

		return rtn;
	}*/

	public static void shuffle(Person persons[]) {

		float[] randomArray = generateRandomArray(persons.length);
		
		for(int i = persons.length - 1; i >=1; i--) {
			//int j = rnd.nextInt(i + 1); //Must be inclusive for i
			int j = (int)(randomArray[i] * (i + 1)); //Must be inclusive for i, floor
			Person temp = persons[j];
			persons[j] = persons[i];
			persons[i] = temp;
		}
	}

	public native boolean[] createShuffledArray(int N, double p);
	private static native float[] generateRandomArray(int N);
}
