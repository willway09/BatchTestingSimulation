all: *.java
	javac -cp . *.java

clean:
	rm *.class
	
zip:
	zip out.zip *