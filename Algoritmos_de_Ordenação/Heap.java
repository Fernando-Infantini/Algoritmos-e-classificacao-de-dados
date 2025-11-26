import java.util.ArrayList;
import java.util.Random;

public class Heap{
	public static void main(String[] args){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(args.length < 2) return;
		switch(args[1]){
			case "A":
				for(int i = 1; i <= Integer.parseInt(args[0]) ; i++){
					list.add(i);
				}
			break;
			case "D":
				for(int i = Integer.parseInt(args[0]); i > 0 ; i--){
					list.add(i);
				}
			break;
			case "R":
				Random rand = new Random(Integer.parseInt(args[2]));
				for(int i = 1; i <= Integer.parseInt(args[0]) ; i++){
					list.add(rand.nextInt(32767));
				}
			break;
		}
		long time = System.nanoTime();
		sort(list);
		time = System.nanoTime() - time;
		System.out.println(time);
	}

	public static <T extends Comparable<T>> void sort(ArrayList<T> array){
		int size = array.size();
		heapfy(array);
		for(int i = 0; i < size-1; i++){
			T temp = array.get(size-1-i);
			array.set(size-1-i, array.get(0));
			array.set(0, temp);
			siftDown(array, 0, size-1-i);
		}
		return;
	}
	public static <T extends Comparable<T>> void heapfy(ArrayList<T> array){
		int start = (array.size()-1)/2 + 1;
		while(start > 0){
			start = start-1;
			siftDown(array, start, array.size()-1);
		}
		return;
	}
	public static <T extends Comparable<T>> void siftDown(ArrayList<T> array, int start, int end){
		while(2*start+1 < end){
			int gchild = 2*start+1;
			if(2*start+2 < end){
				if(array.get(2*start+1).compareTo(array.get(2*start+2)) < 0){
					gchild = 2*start+2;
				}
			}
			if(array.get(start).compareTo(array.get(gchild)) > 0) return;

			T temp = array.get(gchild);
			array.set(gchild, array.get(start));
			array.set(start, temp);
			start = gchild;
		}
		return;
	}
}
