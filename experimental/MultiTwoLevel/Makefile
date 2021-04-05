all: main.cpp
	g++ -L. -O3 -o main main.cpp -lsqlitewrapper -lsqlite3

test: test.cpp
	g++ -L. -o test test.cpp -lsqlitewrapper -lsqlite3
clean:
	rm main
