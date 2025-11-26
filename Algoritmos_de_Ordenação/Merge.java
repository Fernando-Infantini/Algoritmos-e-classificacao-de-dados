import java.util.ArrayList;
import java.util.Random;

public class Merge {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (args.length < 2) return;

        switch (args[1]) {
            case "A":
                for (int i = 1; i <= Integer.parseInt(args[0]); i++) {
                    list.add(i);
                }
                break;
            case "D":
                for (int i = Integer.parseInt(args[0]); i > 0; i--) {
                    list.add(i);
                }
                break;
            case "R":
                Random rand = new Random(Integer.parseInt(args[2]));
                for (int i = 1; i <= Integer.parseInt(args[0]); i++) {
                    list.add(rand.nextInt(32767));
                }
                break;
        }

        long time = System.nanoTime();
        sort(list);
        time = System.nanoTime() - time;
        System.out.println(time);
    }

    public static <T extends Comparable<T>> void sort(ArrayList<T> array) {
        if (array == null || array.size() < 2) return;
        mergeSort(array, 0, array.size() - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(ArrayList<T> array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private static <T extends Comparable<T>> void merge(ArrayList<T> array, int left, int middle, int right) {
        ArrayList<T> leftList = new ArrayList<>(array.subList(left, middle + 1));
        ArrayList<T> rightList = new ArrayList<>(array.subList(middle + 1, right + 1));

        int i = 0, j = 0, k = left;
        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).compareTo(rightList.get(j)) <= 0) {
                array.set(k++, leftList.get(i++));
            } else {
                array.set(k++, rightList.get(j++));
            }
        }
        while (i < leftList.size()) {
            array.set(k++, leftList.get(i++));
        }
        while (j < rightList.size()) {
            array.set(k++, rightList.get(j++));
        }
    }
}
