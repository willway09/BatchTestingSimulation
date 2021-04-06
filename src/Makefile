all: Rnd.c src_Rnd.h
	make -C ranluxpp
	g++ -fPIC -shared -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -Iranluxpp/inc -Lranluxpp -o Rnd.dll Rnd.c -lranlux++
	#ld -shared -o Rnd.dll Rnd.o
	cp Rnd.dll ../libRnd.so
