public class DFS {

    public static void executar(Grafo g, int origem) {
        boolean[] visitado = new boolean[g.getNumeroVertices()];
        System.out.print("DFS: ");
        dfsRec(g, origem, visitado);
        System.out.println();
    }

    private static void dfsRec(Grafo g, int atual, boolean[] visitado) {
        visitado[atual] = true;
        System.out.print(atual + " ");

        for (Aresta e : g.getAdjacentes(atual)) {
            if (!visitado[e.destino])
                dfsRec(g, e.destino, visitado);
        }
    }
}
