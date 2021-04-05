/*package src;

import java.io.FileWriter;
import java.io.IOException;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;


import java.util.ArrayList;

class MultiLevel extends Batch {

    public MultiLevel(PreparedStatement out) {
        super(out);
    }



    public void runTrial(int N, int n, double p, int swapCount, int repetitionValues[]) throws IOException, SQLException {
        for(int m = 1; m < n; m++) {

            for (int i = 0; i < repetitionValues.length; i++) {

                repetitionValues[i] = internalComputation(N, n, m, p, swapCount);

            }

            double mean = Stat.average(repetitionValues);
            double stdDev = Stat.standardDeviation(repetitionValues, mean);

            //out.write("" + N + ", " + n + ", " + m + ", " + p + ", " + mean + ", " + stdDev + "\n");
			out.setInt(1, n);
			out.setDouble(2, p);
			out.setInt(3, m);
			out.setDouble(4, mean);
			out.setDouble(5, stdDev);
			out.executeUpdate();
        }



    }



    public int internalComputation(int N, int n, int m, double p, int swapCount) {
        Person persons[] = Person.createPersonsArray(N, p);

        ArrayList<Group> groups = new ArrayList<Group>();
        ArrayList<Group> subgroups = new ArrayList<Group>();
        groups.ensureCapacity((N / n) + 1);
		subgroups.ensureCapacity((n / m) + 1);

        for(int i = 0; i < (N / n); i++) {
            groups.add(new Group(persons, n * i, n * (i + 1)));
        }

        double recombinationPercentage = .2;

        //Need to decide what to do with remainders - this needs revisiting
        if(N % n != 0) {

            if((1.0 * (N% n)) / n < recombinationPercentage) { //Do not create new group, recombine with older groups
                int baseOfRemainder = (N / n * n); //Integer division to clear remainder
                int remainder = N % n;

                try {
                    for(int i = 0; i < remainder; i++) {
                        groups.get(i % groups.size()).addMember(persons[i + baseOfRemainder]);
                    }
                } catch(Exception e) {
                    System.out.println(e);


                    System.out.println(N);
                    System.out.println(n);
                    System.out.println(p);
                    System.out.println(baseOfRemainder);
                    System.out.println(remainder);
                    System.out.println(groups.size());
                    System.exit(0);
                }


            } else { //Create new group

                Group remainder = new Group(N - (N / n * n)); //Integer division to clear remainder

                for(int i = (N / n * n); i < N; i++) { //Integer division to clear remainder
                    remainder.addMember(persons[i]);
                }

                groups.add(remainder);
            }
        }



        int sumOfTests = 0;


        for(Group group : groups) {
            sumOfTests++; //Test for group
			subgroups.clear();

            if(group.testGroup()) {

                for (int j = 0; j < (group.getSize() / m); j++) {
                    subgroups.add(new Group(group.getInternalArray(), m * j, m * (j + 1)));
                }

                //if not even number of groups
                if (group.getSize() % m != 0) {

                    if ((1.0 * (group.getSize() % m)) / m < recombinationPercentage) { //Do not create new group, recombine with older groups
                        int baseOfRemainder = (group.getSize() / m * m); //Integer division to clear remainder
                        int remainder = group.getSize() % m;

                        try {
                            for (int k = 0; k < remainder; k++) {
                                subgroups.get(k % subgroups.size()).addMember(group.getInternalArray()[k + baseOfRemainder]);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(e);
							System.out.println("This is the spot");


                            System.out.println(n);
                            System.out.println(m);
                            System.out.println(p);
                            System.out.println(baseOfRemainder);
                            System.out.println(remainder);
                            System.out.println(groups.size());

                            System.exit(0);
                        }


                    } else { //Create new group

                        Group remainder = new Group(group.getSize() - (group.getSize() / m * m)); //Integer division to clear remainder

                        for (int l = (group.getSize() / m * m); l < group.getSize(); l++) { //Integer division to clear remainder
                            remainder.addMember(persons[l]);
                        }

                        subgroups.add(remainder);
                    }

					for(Group subgroup : subgroups) {
						sumOfTests++; //Test for subgroup

						if(subgroup.testGroup()) {
							sumOfTests += subgroup.getSize(); //If positive subgroup, test each individual members
						}
					}

                }

            }

        }
        return sumOfTests;
    }

    public int getType() {
        return 1;
    }
}*/
