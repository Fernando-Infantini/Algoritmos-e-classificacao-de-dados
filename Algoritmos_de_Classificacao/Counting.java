import java.util.ArrayList;
import java.util.Random;

public class Counting {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (args.length < 2) return;
        
        // Determina o tamanho da lista a partir do primeiro argumento
        int size = Integer.parseInt(args[0]);
        
        switch (args[1]) {
            case "A":
                for (int i = 1; i <= size; i++) {
                    list.add(i);
                }
                break;
            case "D":
                for (int i = size; i > 0; i--) {
                    list.add(i);
                }
                break;
            case "R":
                if (args.length < 3) return;
                Random rand = new Random(Integer.parseInt(args[2]));
                for (int i = 1; i <= size; i++) {
                    // Limite aumentado para 1.000.000 para testes maiores,
                    // mantendo 32767 se for o caso original
                    // Vamos usar um limite maior para que ele se comporte como N=1000000
                    list.add(rand.nextInt(1000001)); 
                }
                break;
        }

        Integer[] inlist = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            inlist[i] = list.get(i);
        }

        long time = System.nanoTime();
        // Chamada de sort corrigida - o valor máximo será calculado dentro de sort
        sort(inlist, list.size()); 
        time = System.nanoTime() - time;
        System.out.println(time);
    }

    /**
     * Implementação do Counting Sort que calcula o valor máximo (k) 
     * internamente para dimensionar o array de contagem (C).
     */
    // O argumento 'k' foi removido
    public static void sort(Integer[] A, int n) { 
        if (n <= 1) return;

        // 1. Encontra o valor máximo (k) na lista
        int max = A[0];
        for (int i = 1; i < n; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }
        
        // 2. Cria o array de contagem (C) com tamanho max + 1
        // Se max é 100000, o tamanho deve ser 100001 (índices 0 a 100000)
        int k = max;
        int[] B = new int[n];
        int[] C = new int[k + 1]; // <--- CORREÇÃO AQUI

        for (int i = 0; i <= k; i++) {
            C[i] = 0;
        }

        // Conta as ocorrências
        for (int j = 0; j < n; j++) {
            C[A[j]]++;
        }

        // Soma cumulativa
        for (int i = 1; i <= k; i++) {
            C[i] += C[i - 1];
        }

        // Constrói o array de saída (B)
        for (int j = n - 1; j >= 0; j--) {
            B[C[A[j]] - 1] = A[j];
            C[A[j]]--;
        }

        // Copia B para A
        for (int i = 0; i < n; i++) {
            A[i] = B[i];
        }
        return;
    }
}