import java.util.ArrayList;
import java.util.List;

public class NoArvoreB<K extends Comparable<K>, V> {
    List<K> chaves;               // Chaves ordenadas
    List<List<V>> valores;        // Lista de listas
    List<NoArvoreB<K, V>> filhos; // Filhos
    boolean folha;                // Se é folha

    public NoArvoreB(boolean folha) {
        this.folha = folha;
        this.chaves = new ArrayList<>();
        this.valores = new ArrayList<>();
        this.filhos = new ArrayList<>();
    }
}