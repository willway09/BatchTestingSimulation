package src;
import java.util.ArrayList;
import java.io.IOException;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;

class MultiLevel extends Batch {

	public MultiLevel(PreparedStatement out) {
		super(out);
	}

	public void runTrial(int N, int n, double p, int swapCount, long repetitionValues[]) throws IOException, SQLException {
		for(int m = 1; m < n; m++)
		//int m = 3;
		{
			for(int i = 0; i < repetitionValues.length; i++) {
				Person persons[] = Person.createPersonsArray(N, p);
				/*Person persons[] = {
					new Person(true),
					new Person(false),
					new Person(false),
					new Person(false),

					new Person(true),
					new Person(true),
					new Person(false),
					new Person(false),

					new Person(true),
					new Person(false),
					new Person(true),
					new Person(false),

					new Person(false),
					new Person(false),
					new Person(false),
					new Person(false)
				};*/

				int sizes[] = {m, n};
				repetitionValues[i] = internalComputation(sizes, swapCount, persons, 0, 1);

			}

			double mean = Stat.average(repetitionValues);
			double stdDev = Stat.standardDeviation(repetitionValues, mean);

			//out.write("" + N + ", " + n + ", " + p + ", " + mean + ", " + stdDev + "\n");
			out.setInt(1, n);
			out.setDouble(2, p);
			out.setInt(3, m);
			out.setDouble(4, mean);
			out.setDouble(5, stdDev);
			out.executeUpdate();
		}
	}

	void printSpaces(int n) {
		for(int i = 0; i < n; i++) {
			System.out.print(" ");
		}
	}

	public long internalComputation(int sizes[], int swapCount, Person persons[], long sumOfTests, int level) {

		ArrayList<Group> groups = new ArrayList<Group>();
		groups.ensureCapacity((persons.length / sizes[level]) + 1);

		for(int i = 0; i < (persons.length / sizes[level]); i++) {
			groups.add(new Group(persons, sizes[level] * i, sizes[level] * (i + 1)));
		}

		double recombinationPercentage = 0;

		//Need to decide what to do with remainders - this needs revisiting
		if(persons.length % sizes[level]  != 0) {

			if((1.0 * (persons.length % sizes[level])) / sizes[level] < recombinationPercentage) { //Do not create new group, recombine with older groups
				int baseOfRemainder = (persons.length / sizes[level] * sizes[level]); //Integer division to clear remainder
				int remainder = persons.length % sizes[level];

				try {
					for(int i = 0; i < remainder; i++) {
						groups.get(i % groups.size()).addMember(persons[i + baseOfRemainder]);
					}
				} catch(Exception e) {
					System.out.println(e);


					System.out.println(persons.length);
					System.out.println(sizes[level]);
					System.out.println(baseOfRemainder);
					System.out.println(remainder);
					System.out.println(groups.size());

					System.exit(0);
				}


			} else { //Create new group

				Group remainder = new Group(persons.length - (persons.length / sizes[level] * sizes[level])); //Integer division to clear remainder

				for(int i = (persons.length / sizes[level] * sizes[level]); i < persons.length; i++) { //Integer division to clear remainder
					remainder.addMember(persons[i]);
				}

				groups.add(remainder);
			}
		}


		for(Group group : groups) {
			sumOfTests++; //Test for group

			if(group.testGroup()) {

				if(level == 0) {
					//No recursion
					//printSpaces(2);
					sumOfTests += group.getSize(); //If positive group, test each individual members
				} else {
					//Recursion
					sumOfTests = internalComputation(sizes, swapCount, group.getInternalArray(), sumOfTests, level - 1);
				}


			}
		}
		//printSpaces(1 - level);
		//System.out.println(sumOfTests);

		return sumOfTests;
	}

	public int getType() {
		return 0;
	}
}
