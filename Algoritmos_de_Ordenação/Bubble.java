import java.util.ArrayList;
import java.util.Random;

public class Bubble{
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
		Integer[] inlist = new Integer[list.size()];
		for(int i = 0; i<list.size(); i++){
			inlist[i] = list.get(i);
		}
		long time = System.nanoTime();
		sort(inlist, list.size());
		time = System.nanoTime() - time;
		System.out.println(time);
	}
	public static <T extends Comparable<T>> void sort(T[] array, int n){
		boolean troca = true;
		T temp;

		for(int i = 0; i < n && troca == true; i++){
			troca = false;
			for(int j = n - 1; j > i; j--){
				if(array[j].compareTo(array[j-1]) < 0){
					troca = true;

					temp = array[j];
					array[j] = array[j-1];
					array[j-1] = temp;
				}
			}
		}
		return;
	}
}
