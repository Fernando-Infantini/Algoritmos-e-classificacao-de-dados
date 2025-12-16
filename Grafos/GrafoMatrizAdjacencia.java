import java.util.ArrayList;
import java.util.List;

public class GrafoMatrizAdjacencia implements Grafo {

    private int vertices;
    private double[][] matriz;

    public GrafoMatrizAdjacencia(int vertices) {
        this.vertices = vertices;
        matriz = new double[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                matriz[i][j] = 0;
            }
        }
    }

    
    public int getNumeroVertices() {
        return vertices;
    }

    
    public void adicionarAresta(int origem, int destino) {
        adicionarAresta(origem, destino, 1.0);
    }

    
    public void adicionarAresta(int origem, int destino, double peso) {
        // 1. Configura a aresta de origem para destino
    matriz[origem][destino] = peso; 
    
    // 2. CONFIGURA A ARESTA DE DESTINO PARA ORIGEM
    // Isso garante a simetria, que é a característica de um grafo não dirigido.
    matriz[destino][origem] = peso; // <-- CORREÇÃO PRINCIPAL
    }

    public List<Aresta> getAdjacentes(int vertice) {
        List<Aresta> lista = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            if (matriz[vertice][i] != 0) {
                lista.add(new Aresta(vertice, i, matriz[vertice][i]));
            }
        }
        return lista;
    }

    public void imprimir() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
