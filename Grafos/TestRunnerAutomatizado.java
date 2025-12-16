import java.io.*;
import java.util.*;

public class TestRunnerAutomatizado {

    private static final String[] ALGORITMOS = {
        "BFS", "DFS", "KRUSKAL", "PRIM", "DIJKSTRA", "BELLMANFORD", "FLOYDWARSHALL"
    };

    private static final int REPETICOES = 11;

    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            System.out.println("Uso: java TestRunnerAutomatizado <pasta_com_arquivos_gr>");
            System.exit(1);
        }

        String pasta = args[0];
        File dir = new File(pasta);
        if (!dir.isDirectory()) {
            System.out.println("Erro: não é uma pasta válida.");
            System.exit(1);
        }

        PrintWriter csv = new PrintWriter(new FileWriter("resultados.csv", false));
        csv.println("arquivo;algoritmo;estrutura;execucao;tempo_ms");

        for (File file : dir.listFiles((d, nome) -> nome.endsWith(".gr"))) {

            System.out.println("\n=== Processando arquivo: " + file.getName() + " ===");

            int vertices = deduzNumeroVerticesDoNome(file.getName());
            System.out.println("Número de vértices detectado: " + vertices);

            GrafoListaAdjacencia gLista = new GrafoListaAdjacencia(vertices);
            GrafoMatrizAdjacencia gMatriz = new GrafoMatrizAdjacencia(vertices);

            List<GraphLoader.Edge> edges = GraphLoader.load(file.getAbsolutePath());
            for (GraphLoader.Edge e : edges) {
                int u = e.from - 1;
                int v = e.to - 1;

                gLista.adicionarAresta(u, v, e.weight);
                gLista.adicionarAresta(v, u, e.weight);

                gMatriz.adicionarAresta(u, v, e.weight);
                gMatriz.adicionarAresta(v, u, e.weight);
            }

            for (String algoritmo : ALGORITMOS) {

                for (int r = 1; r <= REPETICOES; r++) {
                    double tempo = executarAlgoritmo(algoritmo, gLista);
                    csv.printf("%s;%s;LISTA;%d;%.4f%n", file.getName(), algoritmo, r, tempo);
                    System.out.printf("Arquivo: %s | %s | LISTA | Exec: %d | %.4f ms%n",
                            file.getName(), algoritmo, r, tempo);
                }

                for (int r = 1; r <= REPETICOES; r++) {
                    double tempo = executarAlgoritmo(algoritmo, gMatriz);
                    csv.printf("%s;%s;MATRIZ;%d;%.4f%n", file.getName(), algoritmo, r, tempo);
                    System.out.printf("Arquivo: %s | %s | MATRIZ | Exec: %d | %.4f ms%n",
                            file.getName(), algoritmo, r, tempo);
                }
            }
        }

        csv.close();
        System.out.println("\n=== Testes finalizados. CSV salvo em resultados.csv ===");
    }

    private static double executarAlgoritmo(String algoritmo, Grafo g) {
        Cronometro c = new Cronometro();
        c.iniciar();
        switch (algoritmo) {
            case "BFS": BFS.executar(g, 0); break;
            case "DFS": DFS.executar(g, 0); break;
            case "KRUSKAL": Kruskal.executar(g); break;
            case "PRIM": Prim.executar(g, 0); break;
            case "DIJKSTRA": Dijkstra.executar(g, 0); break;
            case "BELLMANFORD": BellmanFord.executar(g, 0); break;
            case "FLOYDWARSHALL": FloydWarshall.executar(g); break;
        }
        return c.tempoDecorridoMs();
    }

    private static int deduzNumeroVerticesDoNome(String nomeArquivo) {
        try {
            int inicio = nomeArquivo.indexOf("sample") + "sample".length();
            int fim = nomeArquivo.indexOf("-", inicio);
            String numStr = nomeArquivo.substring(inicio, fim);
            return Integer.parseInt(numStr);
        } catch (Exception e) {
            System.err.println("Erro ao deduzir número de vértices do nome: " + nomeArquivo);
            return 0;
        }
    }
}
