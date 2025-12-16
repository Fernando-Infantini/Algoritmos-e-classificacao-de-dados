import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;


public class FordFulkerson {

    public static int executar(int[][] capacidade, int fonte, int sumidouro) {
        int n = capacidade.length;
        int[][] residual = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                residual[i][j] = capacidade[i][j];

        int fluxoMax = 0;

        while (true) {
            int[] pai = new int[n];
            boolean[] visitado = new boolean[n];
            Queue<Integer> fila = new LinkedList<>();

            fila.add(fonte);
            visitado[fonte] = true;
            pai[fonte] = -1;

            boolean encontrouCaminho = false;

            while (!fila.isEmpty()) {
                int u = fila.poll();

                for (int v = 0; v < n; v++) {
                    if (!visitado[v] && residual[u][v] > 0) {
                        fila.add(v);
                        visitado[v] = true;
                        pai[v] = u;

                        if (v == sumidouro) {
                            encontrouCaminho = true;
                            break;
                        }
                    }
                }
            }

            if (!encontrouCaminho) break;

            int fluxo = Integer.MAX_VALUE;

            int v = sumidouro;
            while (v != fonte) {
                int u = pai[v];
                fluxo = Math.min(fluxo, residual[u][v]);
                v = u;
            }

            v = sumidouro;
            while (v != fonte) {
                int u = pai[v];
                residual[u][v] -= fluxo;
                residual[v][u] += fluxo;
                v = u;
            }

            fluxoMax += fluxo;
        }

        System.out.println("Fluxo máximo = " + fluxoMax);
        return fluxoMax;
    }
}
