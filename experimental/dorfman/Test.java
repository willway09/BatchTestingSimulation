import java.util.Random;



public class Test {

	public static int internalComputation(int N, int n, float p) {
		long begin = System.nanoTime();

		Random rnd = new Random();

		int rtn = 0;

		boolean[] persons = new boolean[N];
		//System.out.print(System.nanoTime() - begin);
		//System.out.print(",");

		for(int i = 0; i < N; i++) {
			if(i < Math.round(N * p)) {
				persons[i] = true;
			} else {
				persons[i] = false;
			}
		}
		//System.out.print(System.nanoTime() - begin);
		//System.out.print(",");


		for(int i = N - 1; i >= 0; i--) {
			int j = rnd.nextInt(i + 1);
			boolean temp = persons[i];
			persons[i] = persons[j];
			persons[j] = temp;
		}
		//System.out.print(System.nanoTime() - begin);
		//System.out.print(",");


		//for(int i = 0; i < N; i++) {
			//std::cout << persons[i] << " ";
		//}
		//std::cout << std::endl;
		//std::exit(0);


		for(int i = 0; i < N / n; i++) {
			rtn += 1; //Increment by alpha, test each group

			for(int j = 0; j < n; j++) {
				if(persons[n * i + j]) { //Infected
					rtn += n; //At beta=1, increment by one test per individual in the group
					break;
				}
			}

		}
		//System.out.print(System.nanoTime() - begin);
		//System.out.print(",");


		if(N % n != 0) {
			rtn += 1;
			for(int j = N / n * n; j < N; j++) {
				if(persons[j]) { //Infected
					rtn += N % n; //At beta=1, increment by one test per individual in the group 
					break;
				}

			}
		}
		//System.out.println(System.nanoTime() - begin);


		return rtn;
	}

	public static void main(String args[]) {
		int repetitionsValue = 1000;
		int N = 5000;


		System.out.println("running,averaging");
		
		int[] repetitionArray = new int[repetitionsValue];

		//for(int pInt = 1; pInt < 100; pInt++) {
		int pInt = 1;
		{
			float p = pInt / 100.0f;
			for(int n = 1; n < 100; n++) {
				long begin = System.nanoTime();
				for(int i = 0; i < repetitionsValue; i++) {
					repetitionArray[i] = internalComputation(N, n, p);
				}


				System.out.print(System.nanoTime() - begin);
				System.out.print(",");

				long sum = 0;
				for(int i = 0; i < repetitionsValue; i++) {
					sum += repetitionArray[i];

				}

				System.out.println(System.nanoTime() - begin);

				//System.out.println(1.0f * sum / repetitionsValue);
			}
		}

	}
}
