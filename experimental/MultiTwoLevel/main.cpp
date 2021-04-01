#include <iostream>
#include <cstdlib>
#include <ctime>
#include <cmath>
#include <cstring>
#include <fstream>

#include "Database.hpp"
#include "Statement.hpp"
#include "OutputRow.hpp"

struct Person {
	unsigned char infected = 0;
	unsigned char cleared = 0;
};


int main() {

	std::system("rm test.db");

	Database db("test.db");

	Statement createTable = db.prepare("CREATE TABLE MultiTwoLevel (n INT, r INT, p REAL, mean REAL);");
	createTable.run();

	Statement insert = db.prepare("INSERT INTO MultiTwoLevel VALUES ((?), (?), (?), (?));");


	std::ofstream out("out.csv");


	int repetitionsValue = 1000;
	int N = 5000;



	std::srand(std::time(NULL));


	for(int n = 1; n < 100; n++) {
		//for(int pInt = 1; pInt < 100; pInt++) {
		int pInt = 1;
		{

			double p = pInt / 100.;


			for(int r = 1; r <= 10; r++) {

				unsigned long long sumOfTests = 0;

				for(int rep = 0; rep < repetitionsValue; rep++) {
					//std::memset(persons, 0, N * sizeof(Person));
					Person* persons = new Person[N];
					for(int i = 0; i < std::round(N * p); i++) {
						persons[i].infected = 1;
					}

					for(int round = 1; round <= r; round++) {

						//Shuffle array for randomization
						for(int i = N - 1; i >=1; i--) {
							int j = rand() % (i + 1);
							Person temp = persons[j];
							persons[j] = persons[i];
							persons[i] = temp;
						}


						for(int i = 0; i < N / n; i++) {
							sumOfTests++; //Increment tests, as a batch is tested here
							bool negativeGroup = true;
							for(int j = 0; j < n; j++) {
								if(persons[i * n + j].infected) { //If person is infected
									negativeGroup = false;
									break;
								}
							}

							if(negativeGroup) {

								for(int j = 0; j < n; j++) {
									persons[i * n + j].cleared = 1; 
								}

							}
						}


						{ //Repeat for remainder
							sumOfTests++; //Increment tests, as a batch is tested here
							bool negativeGroup = true;
							for(int i = N / n * n; i < N; i++) {
								if(persons[i].infected) { //If person is infected
									negativeGroup = false;
									break;
								}
							}

							if(negativeGroup) {

								for(int i = N / n * n; i < N; i++) {
									persons[i].cleared = 1; 
								}

							}
						}
					}

					for(int i = 0; i < N; i++) {
						if(!(persons[i].cleared)) { //If individual is not cleared, test them
							sumOfTests++;
						}
					}

					delete[] persons;
				}


				double mean = 1.0 * sumOfTests / (repetitionsValue * N);
				//out << n << "," << r << "," << p << "," << mean << std::endl; 
				std::cout << n << "," << r << "," << p << "," << mean << std::endl; 
				/*insert.bind(n);
				insert.bind(r);
				insert.bind(p);
				insert.bind(mean);
				insert.run();*/
			}
		}

		std::cout << "n: " << n << std::endl;
	}


	out.close();




	//OutputRow asdf;

	//while(stmt.run(&asdf)) {
	//std::cout << asdf[0].getValueInt() << " " << asdf[1].getValueText() << std::endl;
	//}


}
