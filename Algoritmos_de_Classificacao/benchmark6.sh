#!/bin/bash
TIME=$(date -u +%Y%m%d%H%M%S)
PLIST="";
SEED=$RANDOM
for algorithm in $*;
	do mkdir -p "./results/$algorithm/"; javac "$algorithm.java" &&{
		echo "Ascending: $(java $algorithm 1000000 A)" >> "results/$algorithm/$algorithm 1000000$TIME.log" & PLIST="$PLIST $!";
		echo "Descending: $(java $algorithm 1000000 D)" >> "results/$algorithm/$algorithm 1000000$TIME.log" & PLIST="$PLIST $!";
		echo "Random: $(java $algorithm 1000000 R $SEED)" >> "results/$algorithm/$algorithm 1000000$TIME.log" & PLIST="$PLIST $!";
	}
done;
sleep 1
