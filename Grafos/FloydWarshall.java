public class FloydWarshall {

    public static void executar(Grafo g) {
        int V = g.getNumeroVertices();
        double[][] dist = new double[V][V];

        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                dist[i][j] = (i == j) ? 0 : Double.POSITIVE_INFINITY;

        for (int i = 0; i < V; i++)
            for (Aresta e : g.getAdjacentes(i))
                dist[e.origem][e.destino] = e.peso;

        for (int k = 0; k < V; k++)
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];

        System.out.println("Floyd-Warshall:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++)
                System.out.print(dist[i][j] + " ");
            System.out.println();
        }
    }
}
