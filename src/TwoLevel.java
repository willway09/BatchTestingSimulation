package src;
import java.util.ArrayList;
import java.io.IOException;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;

class TwoLevel extends Batch {

    public TwoLevel(PreparedStatement out) {
	    super(out);
    }

    public void runTrial(int N, int n, double p, int swapCount, int repetitionValues[]) throws IOException, SQLException {
       for(int i = 0; i < repetitionValues.length; i++) {
                   
           repetitionValues[i] = internalComputation(N, n, p, swapCount);
                   
        }
               
        double mean = Stat.average(repetitionValues);
        double stdDev = Stat.standardDeviation(repetitionValues, mean);
               
        //out.write("" + N + ", " + n + ", " + p + ", " + mean + ", " + stdDev + "\n");
        out.setInt(1, n);
	out.setDouble(2, p);
	out.setDouble(3, mean);
	out.setDouble(4, stdDev);
	out.executeUpdate();

    }



    public int internalComputation(int N, int n, double p, int swapCount) {
            Person persons[] = Person.createPersonsArray(N, p);

            /*for(Person person : persons) {
                System.out.println(person);
            }*/
            
            ArrayList<Group> groups = new ArrayList<Group>();
            groups.ensureCapacity((N / n) + 1);
            
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
                
                if(group.testGroup()) {
                    sumOfTests += group.getSize(); //If positive group, test each individual members
                }
            }
            
            return sumOfTests;
    }
    
    public int getType() {
        return 0;
    }
}
