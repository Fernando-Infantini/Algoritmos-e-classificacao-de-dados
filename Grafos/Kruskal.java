import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {

    public static void executar(Grafo g) {
        List<Aresta> arestas = new ArrayList<>();

        for (int i = 0; i < g.getNumeroVertices(); i++)
            arestas.addAll(g.getAdjacentes(i));

        Collections.sort(arestas, (a, b) -> Double.compare(a.peso, b.peso));

        UnionFind uf = new UnionFind(g.getNumeroVertices());

        System.out.println("Kruskal (MST):");
        for (Aresta e : arestas) {
            if (uf.find(e.origem) != uf.find(e.destino)) {
                uf.union(e.origem, e.destino);
                System.out.println(e);
            }
        }
    }
}
