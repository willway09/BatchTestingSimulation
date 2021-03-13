package src;

import java.io.FileWriter;
import java.io.IOException;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;

abstract class Batch {


	Batch(PreparedStatement out) {
		this.out = out;
	}

	public abstract void runTrial(int N, int n, double p, int swapCount, long repetitionsValue[]) throws IOException, SQLException;
	public PreparedStatement out;
	public abstract int getType();
}
