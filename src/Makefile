all: Rnd.c src_Rnd.h
	gcc -fPIC -shared -I$(JAVA_HOME)\include -I$(JAVA_HOME)\include\win32 -o Rnd.dll Rnd.c
	#ld -shared -o Rnd.dll Rnd.o
	cp Rnd.dll ..
