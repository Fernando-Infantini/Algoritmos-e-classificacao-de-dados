import java.util.*;

public class BFS {

    public static void executar(Grafo g, int origem) {
        boolean[] visitado = new boolean[g.getNumeroVertices()];
        Queue<Integer> fila = new LinkedList<>();

        fila.add(origem);
        visitado[origem] = true;

        System.out.print("BFS: ");

        while (!fila.isEmpty()) {
            int atual = fila.poll();
            System.out.print(atual + " ");

            for (Aresta e : g.getAdjacentes(atual)) {
                if (!visitado[e.destino]) {
                    visitado[e.destino] = true;
                    fila.add(e.destino);
                }
            }
        }

        System.out.println();
    }
}
