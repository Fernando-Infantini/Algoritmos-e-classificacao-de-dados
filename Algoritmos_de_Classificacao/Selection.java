import java.util.ArrayList;
import java.util.Random;

public class Selection{
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
		for(int i=0; i<size-1; i++){
			int minimum = i;
			for(int j=i+1; j<size; j++){
				if(array.get(minimum).compareTo(array.get(j)) == -1){
					minimum = j;
				}
			}
			if(minimum != i){
				T temp = array.get(i);
				array.set(i, array.get(minimum));
				array.set(minimum, temp);
			}
		}
		return;
	}
}
