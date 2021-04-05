#!/bin/sh

table="MultiLevel"
echo $table
for i in {0..100..5}
do
	if [ $i -eq 0 ]
	then
		i=1
	fi

	p=$(./divide $i)

	sqlite3 -csv MasterFile.db "SELECT n, m, p, 1 - MIN(mean) / 5000, stdDev FROM $table WHERE p=$p"

done

table="MultiTwoLevel"
echo $table
for i in {0..100..5}
do
	if [ $i -eq 0 ]
	then
		i=1
	fi

	p=$(./divide $i)

	sqlite3 -csv MasterFile.db "SELECT n, r, p, 1 - MIN(mean) / 5000, stdDev FROM $table WHERE p=$p"

done


table="TwoLevel"
echo $table
for i in {0..100..5}
do
	if [ $i -eq 0 ]
	then
		i=1
	fi

	p=$(./divide $i)

	sqlite3 -csv Dorfman.db "SELECT n, p, 1 - MIN(mean) / 5000, stdDev FROM $table WHERE p=$p"

done
