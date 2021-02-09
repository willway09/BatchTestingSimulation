import java.io.FileWriter;
import java.io.IOException;


import java.util.ArrayList;

class TwoLevel implements Batch {
    public int runTrial(int N, int n, double p, int swapCount) {
            
            Person persons[] = new Person[N];
            
            for(int i = 0; i < N; i++) {
                if(i < Math.round(p * N)) { //Should we round or floor?
                    persons[i] = new Person(true);
                } else {
                    persons[i] = new Person(false);
                }
            }
            
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