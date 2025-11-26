import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Bucket { // Renomeado para Bucket

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (args.length < 2) return;
        
        // Determina o tamanho da lista a partir do primeiro argumento
        int size = Integer.parseInt(args[0]);
        
        switch (args[1]) {
            case "A": // Lista em ordem crescente (Almost Sorted)
                for (int i = 1; i <= size; i++) {
                    list.add(i);
                }
                break;
            case "D": // Lista em ordem decrescente (Descending)
                for (int i = size; i > 0; i--) {
                    list.add(i);
                }
                break;
            case "R": // Lista aleatória (Random)
                if (args.length < 3) return; // Precisa da seed
                Random rand = new Random(Integer.parseInt(args[2]));
                for (int i = 1; i <= size; i++) {
                    // O Bucket Sort funciona melhor com um limite superior conhecido.
                    list.add(rand.nextInt(32767)); 
                }
                break;
        }
        
        // Execução e medição do tempo
        long time = System.nanoTime();
        sort(list); // Chama a versão específica para Bucket Sort (ArrayList<Integer>)
        time = System.nanoTime() - time;
        System.out.println(time);
    }
    
    // O método sort é restrito a Integer para o Bucket Sort.
    public static void sort(ArrayList<Integer> array) {
        if (array == null || array.size() <= 1) {
            return;
        }

        // 1. Encontra o valor máximo e mínimo para determinar a faixa de valores
        int max = array.get(0);
        int min = array.get(0);
        for (int x : array) {
            if (x > max) max = x;
            if (x < min) min = x;
        }
        
        // Se max == min, a lista já está ordenada (todos os elementos são iguais)
        if (max == min) return;

        // 2. Cria os Baldes (Buckets)
        // Um número razoável de baldes, por exemplo, o número de elementos ou um fator dele.
        // Aqui, usamos um número fixo de baldes (e.g., 10), o que funciona bem para inteiros uniformes.
        final int NUM_BUCKETS = 10;
        
        // Lista de Listas (ArrayLists) para os baldes
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(NUM_BUCKETS);
        for (int i = 0; i < NUM_BUCKETS; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        // Define o intervalo de cada balde
        // O +1 garante que o 'max' caia no último balde e não cause um erro de índice
        double range = (double) (max - min + 1) / NUM_BUCKETS;

        // 3. Distribui os elementos nos baldes
        for (int x : array) {
            // Calcula o índice do balde: (valor - min) / range
            int bucketIndex = (int) ((x - min) / range);
            // Garante que o índice não exceda o limite (para o caso de x == max)
            if (bucketIndex >= NUM_BUCKETS) {
                bucketIndex = NUM_BUCKETS - 1;
            }
            buckets.get(bucketIndex).add(x);
        }

        // 4. Ordena cada balde e junta-os de volta
        int currentIndex = 0;
        for (ArrayList<Integer> bucket : buckets) {
            // Ordena o conteúdo de cada balde. Collections.sort() usa um Timsort eficiente.
            Collections.sort(bucket); 
            
            // Copia os elementos ordenados do balde de volta para o array original
            for (int x : bucket) {
                array.set(currentIndex++, x);
            }
        }
    }
}