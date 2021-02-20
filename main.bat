@echo off
java -Djava.library.path=. -cp ".;sqlite-jdbc-3.34.0.jar" src/Main %1 %2 %3
