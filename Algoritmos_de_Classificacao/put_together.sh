#!/bin/bash
TIME=$(date -u +%Y%m%d%H%M%S)
for algorithm in $*; do
	for order in "Ascending" "Descending" "Random"; do
		mkdir -p "./results/$algorithm/"
		for i in 100 1000 10000 100000; do
				echo "$i;" $(cat "$algorithm$i.log" | grep "$order" | awk -F ': ' '{print $2}')";" >> "./results/$algorithm/$algorithm$order$TIME.csv";
		done;
	done;
done;
for algorithm in $*; do
	for i in 100 1000 10000 100000; do
		rm "$algorithm$i.log"
	done;
done;
