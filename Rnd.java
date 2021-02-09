import java.util.Random;

class Rnd {
    static int[] generateSwapArray(int N, int swapCount) {
        Random rnd = new Random();
        
        int rtn[] = new int[2 * swapCount];
        
        for(int i = 0; i < rtn.length; i++){
            rtn[i] = rnd.nextInt(N);
        }
        
        return rtn;
    }
}