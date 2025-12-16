import java.util.List;
import java.util.ArrayList;

public interface Grafo {
    int getNumeroVertices();
    void adicionarAresta(int origem, int destino);
    void adicionarAresta(int origem, int destino, double peso);
    List<Aresta> getAdjacentes(int vertice);
    void imprimir();
}
