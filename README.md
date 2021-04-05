# BatchTestingSimulation

This is the Java simulation code corresponding to the Batch Simulation Comparison paper By Richard (Will) McCoy (willway09), Aryeh Silver (AriSilver1), and Sophia Keane (Sophia01717).

To compile, clone the repository, then run
```
make
```
in the root directory

To run, execute

```
java Main
```

Note that the simulation outputs to a SQLite database, for streamlined querying. We analyzed the data in R, using the RSQLite package, though due to the ubiquity of SQLite, either native access or a popular package should be available for your choice of data analysis technique. In addition, using the sqlite3 executable, running the query
```
.headers on
.mode csv
.output data.csv
SELECT * FROM <TableName>
```
where TableName is either TwoLevel, MultiLevel, or MultiTwoLevel, will export the data to a CSV, which in turn is essentially guaranteed to be importable to any data analysis program.






