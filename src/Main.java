package src;

import java.io.File;


import java.io.FileWriter;
import java.io.IOException;


import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

class Main {
    public static void main(String[] args) throws IOException, SQLException {
            
	    //Clear database file for new write
	    (new File("file/data.db")).delete();

	    
	    Connection c = null;
	    //c = DriverManager.getConnection("jdbc:sqlite:file/data.db");
	    c = DriverManager.getConnection("jdbc:sqlite::memory:");
	    
	    Statement stmt = c.createStatement();
	    //out.write("" + N + ", " + n + ", " + p + ", " + mean + ", " + stdDev + "\n");
	    String sql = "PRAGMA synchronous = OFF;";
	    stmt.execute(sql);

	    sql = "CREATE TABLE TwoLevel (n INT, p REAL, mean REAL, stdDev REAL);";
	    stmt.executeUpdate(sql);
	    
	    sql = "CREATE TABLE MultiLevel (n INT, p REAL, m REAL, mean REAL, stdDev REAL);";
	    stmt.executeUpdate(sql);
	    
	    sql = "CREATE TABLE MultiTwoLevel (n INT, p REAL, r REAL, mean REAL, stdDev REAL);";
	    stmt.executeUpdate(sql);

	    stmt.close();

	    
            
            /*FileWriter twoTestOut = new FileWriter(new File("file/TwoTestOut.csv"));
            FileWriter multiTestOut = new FileWriter(new File("file/MultiTestOut.csv"));
            FileWriter multiTwoTestOut = new FileWriter(new File("file/MultiTwoTestOut.csv"));*/




	    PreparedStatement twoTestOut = c.prepareStatement("INSERT INTO TwoLevel (n, p, mean, stdDev) values (?, ?, ?, ?);");
	    
	    PreparedStatement multiTestOut = c.prepareStatement("INSERT INTO MultiLevel (n, p, m, mean, stdDev) values (?, ?, ?, ?, ?);");
	    
	    PreparedStatement multiTwoTestOut = c.prepareStatement("INSERT INTO MultiTwoLevel (n, p, r, mean, stdDev) values (?, ?, ?, ?, ?);");
	    
            int swapCount = 100;

            int numberOfRepititions = 100;
            
            int repetitionValues[] = new int[numberOfRepititions];

	    ArrayList<Batch> testTypes = new ArrayList<Batch>();
	    testTypes.ensureCapacity(3);

	    for(String arg : args) {
		    switch(arg) {
			case "TwoLevel":
				testTypes.add(new TwoLevel(twoTestOut));
				break;
			case "MultiLevel":
				testTypes.add(new MultiLevel(multiTwoTestOut));
				break;
			case "MultiTwoLevel":
				testTypes.add(new MultiTwoLevel(multiTwoTestOut));
				break;
		    }
	    }


	    //Case where no arguments are specified
	    if(testTypes.size() == 0) {

		testTypes.add(new TwoLevel(twoTestOut));
		testTypes.add(new MultiLevel(multiTwoTestOut));
		testTypes.add(new MultiTwoLevel(multiTwoTestOut));

	    }
	




            int N = 100;

            for(int n = 1; n < N; n++) {
                for(int pInt = 1; pInt < 100; pInt++) {
                    double p = pInt / 100.0;
                    
                    for(Batch test : testTypes) {
                    	test.runTrial(N, n, p, swapCount, repetitionValues) ;
                    }
                    
                }
		System.out.print("n: ");
           	System.out.println(n); 
            }
            

            twoTestOut.close();
            multiTestOut.close();
            multiTwoTestOut.close(); 
	    
            //Export memory database to file
	    sql = "ATTACH \"file/data.db\" AS external;";
	    stmt.executeUpdate(sql);

	    sql = "CREATE TABLE external.TwoLevel (n INT, p REAL, mean REAL, stdDev REAL);";
	    stmt.executeUpdate(sql);
	    
	    sql = "CREATE TABLE external.MultiLevel (n INT, p REAL, m REAL, mean REAL, stdDev REAL);";
	    stmt.executeUpdate(sql);
	    
	    sql = "CREATE TABLE external.MultiTwoLevel (n INT, p REAL, r REAL, mean REAL, stdDev REAL);";
	    stmt.executeUpdate(sql);

	    sql = "BEGIN;INSERT INTO external.TwoLevel SELECT * FROM TwoLevel;COMMIT;"; 
	    stmt.executeUpdate(sql);

	    sql = "BEGIN;INSERT INTO external.MultiLevel SELECT * FROM MultiLevel;COMMIT;"; 
	    stmt.executeUpdate(sql);

	    sql = "BEGIN;INSERT INTO external.MultiTwoLevel SELECT * FROM MultiTwoLevel;COMMIT;"; 
	    stmt.executeUpdate(sql);


	    stmt.close();

	    c.close();
    }
    
}
