import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;

public class Quick{
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
	public static <T extends Comparable<T>> void sort(T[] a, int n){
		Stack<Integer> s = new Stack<Integer>();
		s.push(0);
		s.push(n-1);

		int j, u, p;

		while(!s.empty()){
			u = s.pop();
			p = s.pop();
			j=partition(a, p, u);

			if(j-1 > p){
				s.push(p);
				s.push(j-1);
			}
			if(j+1 < u){
				s.push(j+1);
				s.push(u);
			}
		}
		return;
	}

    public static <T extends Comparable<T>> int partition(T[] a, int p, int u){
		T pivot = a[u];
		int i = p-1;

		for(int j = p; j < u; j++){
			if(a[j].compareTo(pivot)<= 0){
				i++;
				swap(a,i,j);
			}
		}
		swap(a,i+1,u);
		return i+1;
	}
    public static <T extends Comparable<T>> void swap(T[] a, int i, int j){
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
