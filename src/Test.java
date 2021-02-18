package src;


public class Test {
	public static void main(String args[]) {
		int N = 100;
		double p = .1;

		
            Person persons[] = new Person[N];
            
            for(int i = 0; i < N; i++) {
                if(i < Math.round(p * N)) { //Should we round or floor?
                    persons[i] = new Person(true);
                } else {
                    persons[i] = new Person(false);
                }
            }

	    Rnd.shuffle(persons);

	    for(Person person : persons) {

		   System.out.println(person);
	    }
	}
}
