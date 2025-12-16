import java.util.ArrayList;
import java.util.List;

public class Aresta {
    public int origem;
    public int destino;
    public double peso;

    public Aresta(int origem, int destino) {
        this(origem, destino, 1.0);
    }

    public Aresta(int origem, int destino, double peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    
    public String toString() {
        return origem + " -> " + destino + " (peso: " + peso + ")";
    }
}
