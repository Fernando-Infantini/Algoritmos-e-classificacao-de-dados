import java.util.ArrayList;
import java.util.List;

public class GrafoListaAdjacencia implements Grafo {

    private int vertices;
    private List<List<Aresta>> adj;

    public GrafoListaAdjacencia(int vertices) {
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
        // 1. Verifica se a aresta já existe
        for (Aresta e : adj.get(origem)) {
            if (e.destino == destino) {
                // Se já existe, atualiza o peso APENAS se o novo peso for menor
                if (peso < e.peso) {
                    e.peso = peso; // Atualiza para o novo peso mínimo
                }
                return; // Aresta tratada, encerra o método
            }
        }
    
        // 2. Se a aresta não existe, adiciona normalmente
        adj.get(origem).add(new Aresta(origem, destino, peso));
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
