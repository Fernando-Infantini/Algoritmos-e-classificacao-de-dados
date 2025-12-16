import java.util.PriorityQueue;

public class Prim {

    public static void executar(Grafo g, int origem) {
        boolean[] visitado = new boolean[g.getNumeroVertices()];
        PriorityQueue<Aresta> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(a.peso, b.peso)
        );

        visitado[origem] = true;
        pq.addAll(g.getAdjacentes(origem));

        System.out.println("Prim (MST):");

        while (!pq.isEmpty()) {
            Aresta e = pq.poll();

            if (!visitado[e.destino]) {
                visitado[e.destino] = true;
                System.out.println(e);

                pq.addAll(g.getAdjacentes(e.destino));
            }
        }
    }
}
