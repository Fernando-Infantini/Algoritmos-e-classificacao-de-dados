import java.util.ArrayList;
import java.util.List;

public class DigrafoMatrizAdjacencia implements Grafo {

    private int vertices;
    private double[][] matriz;

    public DigrafoMatrizAdjacencia(int vertices) {
        this.vertices = vertices;
        matriz = new double[vertices][vertices];
    }

    
    public int getNumeroVertices() {
        return vertices;
    }

    
    public void adicionarAresta(int origem, int destino) {
        adicionarAresta(origem, destino, 1.0);
    }

    
    public void adicionarAresta(int origem, int destino, double peso) {
        matriz[origem][destino] = peso;
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
