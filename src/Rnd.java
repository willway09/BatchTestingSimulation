package src;

import java.util.Random;

class Rnd {

    public static Random rnd = new Random();

    public static int[] generateSwapArray(int N, int swapCount) {
        
        int rtn[] = new int[2 * swapCount];
        
        for(int i = 0; i < rtn.length; i++){
            rtn[i] = rnd.nextInt(N);
        }
        
        return rtn;
    }

    public static void shuffle(Person persons[]) {
	for(int i = persons.length - 1; i >=1; i--) {
		int j = rnd.nextInt(i + 1); //Must be inclusive for i
		Person temp = persons[j];
		persons[j] = persons[i];
		persons[i] = temp;
	}
    }
}
