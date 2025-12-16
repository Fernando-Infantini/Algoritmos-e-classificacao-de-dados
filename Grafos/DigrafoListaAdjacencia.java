import java.util.ArrayList;
import java.util.List;

public class DigrafoListaAdjacencia implements Grafo {

    private int vertices;
    private List<List<Aresta>> adj;

    public DigrafoListaAdjacencia(int vertices) {
        this.vertices = vertices;
        adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }
    }

    
    public int getNumeroVertices() {
        return vertices;
    }

    
    public void adicionarAresta(int origem, int destino) {
        adicionarAresta(origem, destino, 1.0);
    }

    
    public void adicionarAresta(int origem, int destino, double peso) {
        adj.get(origem).add(new Aresta(origem, destino, peso)); // dirigido
    }

    
    public List<Aresta> getAdjacentes(int vertice) {
        return adj.get(vertice);
    }

    
    public void imprimir() {
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + ": ");
            for (Aresta e : adj.get(i)) {
                System.out.print(" -> " + e.destino + "(" + e.peso + ")");
            }
            System.out.println();
        }
    }
}
