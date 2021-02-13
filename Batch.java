import java.io.FileWriter;
import java.io.IOException;

abstract class Batch {


    Batch(FileWriter out) {
	this.out = out;
    }

    public abstract void runTrial(int N, int n, double p, int swapCount, int repetitionsValue[]) throws IOException;
    public FileWriter out;
    public abstract int getType();
}
