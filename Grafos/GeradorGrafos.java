import java.util.Random;
import java.util.HashSet;
import java.util.Set;

public class GeradorGrafos {

    public static void gerarArestas(GrafoListaAdjacencia gLista,
                                    GrafoMatrizAdjacencia gMatriz,
                                    int vertices,
                                    double densidade) {

        Random rand = new Random();
        Set<String> usadas = new HashSet<>();

        int maxArestas = vertices * (vertices - 1) / 2;

        int alvo = (int) (densidade * maxArestas);

        int geradas = 0;

        while (geradas < alvo) {

            int u = rand.nextInt(vertices);
            int v = rand.nextInt(vertices);

            if (u == v) continue;

            String chave = Math.min(u, v) + "-" + Math.max(u, v);

            if (usadas.contains(chave)) continue;

            usadas.add(chave);

            double peso = 1 + rand.nextInt(20);

            gLista.adicionarAresta(u, v, peso);
            gMatriz.adicionarAresta(u, v, peso);

            geradas++;
        }

        System.out.println("Grafo gerado com " + vertices +
                " vértices, densidade " + densidade +
                " e " + geradas + " arestas.");
    }
}
