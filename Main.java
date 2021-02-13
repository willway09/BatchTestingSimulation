import java.io.File;


import java.io.FileWriter;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {

            int swapCount = 100;
            
            
            FileWriter twoTestOut = new FileWriter(new File("file/TwoTestOut.csv"));
            FileWriter multiTestOut = new FileWriter(new File("file/MultiTestOut.csv"));
            FileWriter multiTwoTestOut = new FileWriter(new File("file/MultiTwoTestOut.csv"));

            int numberOfRepititions = 100;
            
            int repetitionValues[] = new int[numberOfRepititions];
            
            Batch testTypes[] = { new TwoLevel(twoTestOut) };


            int N = 100;
            



            for(int n = 1; n < N; n++) {
                for(int pInt = 1; pInt < 100; pInt++) {
                    double p = pInt / 100.0;
                    
                    for(Batch test : testTypes) {
                    	test.runTrial(N, n, p, swapCount, repetitionValues) ;
                    }
                    
                }
            
            }
            

            twoTestOut.close();
            multiTestOut.close();
            multiTwoTestOut.close();
            
    }
    
}
