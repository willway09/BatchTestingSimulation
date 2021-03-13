package src;

import java.sql.*;

public class SQLite {
	public static void main(String args[]) throws Exception {
		Connection c = null;
		c = DriverManager.getConnection("jdbc:sqlite:data.db");


		Statement stmt = c.createStatement();
		//out.write("" + N + ", " + n + ", " + p + ", " + mean + ", " + stdDev + "\n");
		String sql = "CREATE TABLE TwoLevel (groupSize INT, percentage REAL, mean REAL, stdDev REAL);";
		stmt.executeUpdate(sql);
		stmt.close();
		c.close();
	}
}
