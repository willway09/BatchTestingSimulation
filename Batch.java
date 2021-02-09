import java.io.FileWriter;
import java.io.IOException;

interface Batch {
    public int runTrial(int N, int n, double p, int swapCount);
    public int getType();
}