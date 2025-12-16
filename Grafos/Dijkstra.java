import java.util.PriorityQueue;

public class Dijkstra {

    public static void executar(Grafo g, int origem) {
        double[] dist = new double[g.getNumeroVertices()];
        for (int i = 0; i < dist.length; i++)
            dist[i] = Double.POSITIVE_INFINITY;
        dist[origem] = 0;

        PriorityQueue<Aresta> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(a.peso, b.peso)
        );

        pq.add(new Aresta(-1, origem, 0));

        while (!pq.isEmpty()) {
            Aresta atual = pq.poll();
            int v = atual.destino;

            for (Aresta e : g.getAdjacentes(v)) {
                if (dist[v] + e.peso < dist[e.destino]) {
                    dist[e.destino] = dist[v] + e.peso;
                    pq.add(new Aresta(v, e.destino, dist[e.destino]));
                }
            }
        }

        System.out.println("Dijkstra:");
        for (int i = 0; i < dist.length; i++)
            System.out.println("Distância até " + i + " = " + dist[i]);
    }
}
