#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <stdbool.h>
#include <stdio.h>


inline unsigned int internalComputation(int N, int n, float p) __attribute__((always_inline));

inline unsigned int internalComputation(int N, int n, float p) {
	//clock_t begin = clock();

	//cout << n << ","; 

	unsigned int rtn = 0;

	bool* persons = malloc(sizeof(bool) * N);
	//cout << clock() - begin << ",";

	for(int i = 0; i < N; i++) {
		if(i < round(N * p)) {
			persons[i] = true;
		} else {
			persons[i] = false;
		}
	}
	//cout << clock() - begin << ",";


	for(int i = N - 1; i >= 0; i--) {
		int j = rand() % (i + 1);
		bool temp = persons[i];
		persons[i] = persons[j];
		persons[j] = temp;
	}

	//cout << clock() - begin << ",";


	//for(int i = 0; i < N; i++) {
		//cout << persons[i] << " ";
	//}
	//cout << endl;
	//exit(0);


	for(int i = 0; i < N / n; i++) {
		rtn += 1; //Increment by alpha, test each group

		for(int j = 0; j < n; j++) {
			if(persons[n * i + j]) { //Infected
				rtn += n; //At beta=1, increment by one test per individual in the group
				break;
			}
		}

	}
	//cout << clock() - begin << ",";


	if(N % n != 0) {
		rtn += 1;
		for(int j = N / n * n; j < N; j++) {
			if(persons[j]) { //Infected
				rtn += N % n; //At beta=1, increment by one test per individual in the group 
				break;
			}

		}
	}
	//cout << clock() - begin << ",";


	free(persons);
	//cout << clock() - begin << endl; 

	return rtn;
}

int main() {
	int repetitionsValue = 1000;
	int N = 5000;


	//cout << "n,allocating,initializing,shuffling,testing,testinglast,deleting" << endl;
	printf("n,running,averaging\n");


	srand(time(NULL));
	
	unsigned int* repetitionArray = malloc(sizeof(unsigned int) * repetitionsValue);

	//for(int pInt = 1; pInt < 100; pInt++) {
	int pInt = 1;
	{
		float p = pInt / 100.0f;
		for(int n = 1; n < 100; n++) {
			clock_t begin = clock();
			printf("%d,",n);
			for(int i = 0; i < repetitionsValue; i++) {
				repetitionArray[i] = internalComputation(N, n, p);



			}

			printf("%d,",clock() - begin);


			unsigned long long sum = 0;
			for(int i = 0; i < repetitionsValue; i++) {
				sum += repetitionArray[i];

			}

			printf("%d\n",clock() - begin);
			//cout << 1.0f * sum / repetitionsValue << endl;
		}
	}


	free(repetitionArray);
}
