#include <iostream>
#include <cstdlib>
#include <ctime>
#include <cmath>


inline unsigned int internalComputation(int N, int n, float p) __attribute__((always_inline));

inline unsigned int internalComputation(int N, int n, float p) {
	//clock_t begin = clock();

	//std::cout << n << ","; 

	unsigned int rtn = 0;

	bool* persons = new bool[N];
	//std::cout << clock() - begin << ",";

	for(int i = 0; i < N; i++) {
		if(i < std::round(N * p)) {
			persons[i] = true;
		} else {
			persons[i] = false;
		}
	}
	//std::cout << clock() - begin << ",";


	for(int i = N - 1; i >= 0; i--) {
		int j = rand() % (i + 1);
		bool temp = persons[i];
		persons[i] = persons[j];
		persons[j] = temp;
	}

	//std::cout << clock() - begin << ",";


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
	//std::cout << clock() - begin << ",";


	if(N % n != 0) {
		rtn += 1;
		for(int j = N / n * n; j < N; j++) {
			if(persons[j]) { //Infected
				rtn += N % n; //At beta=1, increment by one test per individual in the group 
				break;
			}

		}
	}
	//std::cout << clock() - begin << ",";


	delete[] persons; 
	//std::cout << clock() - begin << std::endl; 

	return rtn;
}

int main() {
	int repetitionsValue = 1000;
	int N = 5000;


	//std::cout << "n,allocating,initializing,shuffling,testing,testinglast,deleting" << std::endl;
	std::cout << "n,running,averaging" << std::endl;


	std::srand(std::time(NULL));
	
	unsigned int* repetitionArray = new unsigned int[repetitionsValue];

	//for(int pInt = 1; pInt < 100; pInt++) {
	int pInt = 1;
	{
		float p = pInt / 100.0f;
		for(int n = 1; n < 100; n++) {
			clock_t begin = clock();
			std::cout << n << ",";
			for(int i = 0; i < repetitionsValue; i++) {
				repetitionArray[i] = internalComputation(N, n, p);



			}
			std::cout << clock() - begin << ","; 

			unsigned long long sum = 0;
			for(int i = 0; i < repetitionsValue; i++) {
				sum += repetitionArray[i];

			}

			std::cout << clock() - begin << std::endl;
			//std::cout << 1.0f * sum / repetitionsValue << std::endl;
		}
	}


	delete[] repetitionArray;
}
