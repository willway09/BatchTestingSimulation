package src;


import java.io.FileWriter;
import java.io.IOException;


import java.util.ArrayList;



import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;


class MultiTwoLevel extends Batch {

    public MultiTwoLevel(PreparedStatement out) {
	    super(out);
    }

    public void runTrial(int N, int n, double p, int swapCount, int repetitionValues[]) throws IOException, SQLException {
	    for(int r = 1; r <= 10; r++) {

		for(int i = 0; i < repetitionValues.length; i++) {
		           
		   repetitionValues[i] = internalComputation(N, n, r, p, swapCount);
		           
		}
		       
		double mean = Stat.average(repetitionValues);
		double stdDev = Stat.standardDeviation(repetitionValues, mean);
		       
		//out.write("" + n + ", " + n + ", " + p + ", " + mean + ", " + stddev + "\n");
		               
		out.setInt(1, n);
		out.setDouble(2, p);
		out.setInt(3, r);
		out.setDouble(4, mean);
		out.setDouble(5, stdDev);
		out.executeUpdate();
	    }


    }



    public int internalComputation(int N, int n, int r, double p, int swapCount) {
            Person persons[] = Person.createPersonsArray(N, p);
	    /*= new Person[N];
            
            for(int i = 0; i < N; i++) {
                if(i < Math.round(p * N)) { //Should we round or floor?
                    persons[i] = new Person(true);
                } else {
                    persons[i] = new Person(false);
                }
            }*/
            


            int sumOfTests = 0;


	    for(int round = 0; round < r; round++) {

		    int array[] = Rnd.generateSwapArray(N,swapCount);
		    
		    for(int i = 0; i < array.length / 2; i++) {
			Person temp = persons[array[i]];
			persons[array[i]] = persons[array[i + 1]];
			persons[array[i + 1]] = temp;
		    }
		    
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
		    sumOfTests += groups.size(); //One test per group, because of batching


		    for(Group group : groups) {
			    group.testGroup(); //getGroup() clears persons if group tests negative, so call here to clear person from future testing
		    }
           	 
	    }
            
	    for(Person person : persons) {
		    if(!person.isCleared()) {
			    sumOfTests++;
		    }
	    }


            return sumOfTests;
    }
    
    public int getType() {
        return 0;
    }
}
