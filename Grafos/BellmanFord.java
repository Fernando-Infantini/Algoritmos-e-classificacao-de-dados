public class BellmanFord {

    public static void executar(Grafo g, int origem) {
        int V = g.getNumeroVertices();
        double[] dist = new double[V];

        for (int i = 0; i < V; i++)
            dist[i] = Double.POSITIVE_INFINITY;
        dist[origem] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (int u = 0; u < V; u++) {
                for (Aresta e : g.getAdjacentes(u)) {
                    if (dist[u] + e.peso < dist[e.destino])
                        dist[e.destino] = dist[u] + e.peso;
                }
            }
        }

        System.out.println("Bellman-Ford:");
        for (int i = 0; i < V; i++)
            System.out.println("Dist até " + i + " = " + dist[i]);
    }
}
