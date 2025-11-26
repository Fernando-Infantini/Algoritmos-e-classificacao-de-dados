import java.util.ArrayList;
import java.util.Random;

public class Insertion{
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
        T chave;
        int j;

		for(int i = 1; i < n; i++){

			chave = array[i];
			j=i-1;

			while(j >= 0 && array[j].compareTo(chave) > 0){
				array[j+1] = array[j];
				j = j-1;
			}
			array[j+1] = chave;
		}
		return;
	}
}
