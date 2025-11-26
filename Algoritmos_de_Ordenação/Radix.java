import java.util.ArrayList;
import java.util.Random;
import java.util.Collections; // Necessário para a ordenação final do Bucket Sort, mas não para o Radix Sort

public class Radix {

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
                    // O Radix Sort é mais fácil de implementar para inteiros positivos
                    // O limite 32767 é mantido do seu exemplo original
                    list.add(rand.nextInt(32767)); 
                }
                break;
        }
        
        // Execução e medição do tempo
        long time = System.nanoTime();
        sort(list); // Chama a versão específica para Radix Sort (ArrayList<Integer>)
        time = System.nanoTime() - time;
        System.out.println(time);
    }
    
    // O método sort é restrito a Integer para o Radix Sort
    public static void sort(ArrayList<Integer> array) {
        if (array == null || array.size() <= 1) {
            return;
        }

        // 1. Encontra o valor máximo para saber quantos dígitos processar
        int max = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) > max) {
                max = array.get(i);
            }
        }
        
        // 2. Chama o countingSort para cada posição de dígito (exp é 1, 10, 100, ...)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(array, exp);
        }
    }

    // Função auxiliar (Counting Sort) para ordenar os elementos com base no dígito representado por 'exp'
    private static void countingSort(ArrayList<Integer> array, int exp) {
        int n = array.size();
        // Array de saída temporário que conterá os elementos ordenados
        ArrayList<Integer> output = new ArrayList<>(Collections.nCopies(n, 0)); 
        // Array de contagem (para dígitos 0 a 9)
        int[] count = new int[10];

        // Inicializa count
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }

        // Armazena a contagem de ocorrências de cada dígito
        for (int i = 0; i < n; i++) {
            count[(array.get(i) / exp) % 10]++;
        }

        // Altera count[i] para que count[i] contenha a posição real deste dígito no output
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Constrói o array de saída. É necessário fazer isso de trás para frente para
        // garantir a estabilidade do algoritmo (elementos iguais mantêm a ordem relativa)
        for (int i = n - 1; i >= 0; i--) {
            int digit = (array.get(i) / exp) % 10;
            // A posição no output é count[digit] - 1
            output.set(count[digit] - 1, array.get(i));
            count[digit]--;
        }

        // Copia o output de volta para o array original (in-place)
        for (int i = 0; i < n; i++) {
            array.set(i, output.get(i));
        }
    }
}