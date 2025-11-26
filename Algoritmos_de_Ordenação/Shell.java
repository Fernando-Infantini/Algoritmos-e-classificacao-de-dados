import java.util.ArrayList;
import java.util.Random;

public class Shell { // Renomeado para Shell

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
                    list.add(rand.nextInt(32767));
                }
                break;
        }
        
        // Execução e medição do tempo
        long time = System.nanoTime();
        sort(list); // Pode usar a versão genérica
        time = System.nanoTime() - time;
        System.out.println(time);
    }

    // O Shell Sort é um algoritmo de comparação, então mantém a restrição T extends Comparable<T>
    public static <T extends Comparable<T>> void sort(ArrayList<T> array) {
        int n = array.size();
        
        // Começa com uma grande lacuna (gap), e então reduz a lacuna
        // A sequência de gaps de Knuth (h = h * 3 + 1) é uma escolha comum e eficiente.
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        // O loop principal que reduz o gap
        while (h >= 1) {
            // Um Insertion Sort com passo 'h'
            // O 'i' começa em 'h' para ter elementos à esquerda para comparar
            for (int i = h; i < n; i++) {
                T temp = array.get(i);
                int j;
                
                // Desloca os elementos anteriores 'h' posições para cima até a posição correta
                for (j = i; j >= h && array.get(j - h).compareTo(temp) > 0; j -= h) {
                    array.set(j, array.get(j - h));
                }
                
                // Coloca o 'temp' (elemento original array[i]) em sua posição correta
                array.set(j, temp);
            }
            // Reduz o gap
            h = h / 3; 
        }
    }
}