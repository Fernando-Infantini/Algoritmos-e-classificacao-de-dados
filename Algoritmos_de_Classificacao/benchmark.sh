#!/bin/bash
for i in 100 1000 10000 100000;
	do
	PLIST="";
	SEED=$RANDOM
	for algorithm in $*;
		do javac "$algorithm.java" &&{
			echo "Ascending: $(java $algorithm $i A)" >> "$algorithm$i.log" & PLIST="$PLIST $!";
			echo "Descending: $(java $algorithm $i D)" >> "$algorithm$i.log" & PLIST="$PLIST $!";
			echo "Random: $(java $algorithm $i R $SEED)" >> "$algorithm$i.log" & PLIST="$PLIST $!";
		}
	done;
done;
wait $PLIST
