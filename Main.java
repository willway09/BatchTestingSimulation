import java.io.File;


import java.io.FileWriter;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {

            int swapCount = 100;
            
            File file = new File("file/TwoTestOut.csv");
        
            FileWriter out = new FileWriter(file);
            
            int N = 100;
            
            int numberOfRepititions = 100;
            
            int repetitionValues[] = new int[numberOfRepititions];
            
            Batch testTypes[] = { new TwoLevel() };
                
            for(int n = 1; n < N; n++) {
                for(int pInt = 1; pInt < 100; pInt++) {
                    double p = pInt / 100.0;
                    
                    for(Batch test : testTypes) {
                    
                        for(int i = 0; i < numberOfRepititions; i++) {
                            
                            repetitionValues[i] = test.runTrial(N, n, p, swapCount);
                            
                        }
                        
                        double mean = Stat.average(repetitionValues);
                        double stdDev = Stat.standardDeviation(repetitionValues, mean);
                        
                        out.write("" + N + ", " + n + ", " + p + ", " + mean + ", " + stdDev + "\n");
                        
                    }
                    
                }
            
            }
            
            out.close();

            
    }
    
}