all: src/*.java
	javac -h src -cp "src:sqlite-jdbc-3.34.0.jar" src/*.java
	make -C src

clean:
	rm src/*.class
	
zip:
	zip out.zip *
