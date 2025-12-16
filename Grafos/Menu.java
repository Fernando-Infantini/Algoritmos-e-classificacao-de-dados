import java.util.*;

public class Menu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        GrafoListaAdjacencia gLista = null;
        GrafoMatrizAdjacencia gMatriz = null;
        Grafo gAtual;

        System.out.println("===== INICIALIZAÇÃO DO GRAFO =====");
        System.out.println("1. Carregar grafo de arquivo .gr");
        System.out.println("2. Gerar grafo aleatório");
        System.out.print("Escolha: ");
        int escolha = sc.nextInt();

        int vertices = 0;

        if (escolha == 1) {

            System.out.print("Caminho do arquivo .gr: ");
            String caminho = sc.next();

            System.out.print("Número total de vértices: ");
            vertices = sc.nextInt();

            gLista = new GrafoListaAdjacencia(vertices);
            gMatriz = new GrafoMatrizAdjacencia(vertices);

            List<GraphLoader.Edge> edges = GraphLoader.load(caminho);

            System.out.println("Arquivo lido: " + edges.size() + " arestas.");

            for (GraphLoader.Edge e : edges) {
                int u = e.from - 1;
                int v = e.to - 1;

                if (u < 0 || u >= vertices || v < 0 || v >= vertices) {
                    System.out.println("Aresta ignorada (índice fora): " + e.from + " -> " + e.to);
                    continue;
                }
            
                gLista.adicionarAresta(u, v, e.weight);
                gMatriz.adicionarAresta(u, v, e.weight);
            }

        } else {

            System.out.print("Número de vértices: ");
            vertices = sc.nextInt();

            System.out.print("Densidade (0..1): ");
            double densidade = sc.nextDouble();

            gLista = new GrafoListaAdjacencia(vertices);
            gMatriz = new GrafoMatrizAdjacencia(vertices);

            GeradorGrafos.gerarArestas(gLista, gMatriz, vertices, densidade);
        }

        gAtual = gLista; // representação padrão

        while (true) {

            System.out.println("\n===== MENU =====");
            System.out.println("Representação atual: " + gAtual.getClass().getSimpleName());
            System.out.println("1. BFS");
            System.out.println("2. DFS");
            System.out.println("3. TopSort");
            System.out.println("4. Kruskal");
            System.out.println("5. Prim");
            System.out.println("6. Dijkstra");
            System.out.println("7. Bellman-Ford");
            System.out.println("8. Floyd-Warshall");
            System.out.println("9. Mostrar grafo (Lista)");
            System.out.println("10. Mostrar grafo (Matriz)");
            System.out.println("11. Usar Lista");
            System.out.println("12. Usar Matriz");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int op = sc.nextInt();

            Cronometro c = new Cronometro();
            c.iniciar();

            switch (op) {
                case 1: BFS.executar(gAtual, 0); break;
                case 2: DFS.executar(gAtual, 0); break;
                case 3: TopSort.executar(gAtual); break;
                case 4: Kruskal.executar(gAtual); break;
                case 5: Prim.executar(gAtual, 0); break;
                case 6: Dijkstra.executar(gAtual, 0); break;
                case 7: BellmanFord.executar(gAtual, 0); break;
                case 8: FloydWarshall.executar(gAtual); break;
                case 9: gLista.imprimir(); break;
                case 10: gMatriz.imprimir(); break;
                case 11: gAtual = gLista; break;
                case 12: gAtual = gMatriz; break;
                case 0: System.exit(0);
            }

            if (op >= 1 && op <= 8)
                System.out.printf("Tempo: %.4f ms\n", c.tempoDecorridoMs());
        }
    }
}
