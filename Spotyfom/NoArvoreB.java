import java.util.ArrayList;
import java.util.List;

public class NoArvoreB<K extends Comparable<K>, V> {
    int t; // Grau mínimo
    List<K> chaves; // Lista de chaves no nó
    List<List<V>> valores; // Lista de listas de valores (para tratar duplicatas como mesmo artista)
    List<NoArvoreB<K, V>> filhos; // Filhos do nó
    boolean folha; // Se é folha

    public NoArvoreB(int t, boolean folha) {
        this.t = t;
        this.folha = folha;
        this.chaves = new ArrayList<>();
        this.valores = new ArrayList<>();
        this.filhos = new ArrayList<>();
    }
}
