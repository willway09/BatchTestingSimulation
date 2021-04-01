
struct Person {
	unsigned char infected = 0;
	unsigned char cleared = 0;
};


int main() {
	Person* person = new Person[10];
	delete[] person;
}
