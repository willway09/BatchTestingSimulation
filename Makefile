all: src/*.java
	javac -cp "src;sqlite-jdbc-3.34.0.jar" src/*.java

clean:
	rm src/*.class
	
zip:
	zip out.zip *
