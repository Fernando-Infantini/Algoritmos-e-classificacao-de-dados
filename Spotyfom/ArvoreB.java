import java.util.ArrayList;
import java.util.List;

public class ArvoreB<K extends Comparable<K>, V> {
    private NoArvoreB<K, V> raiz;
    private int t; // Grau mínimo, metade da ordem.

    public ArvoreB(int t) {
        this.raiz = null;
        this.t = t;
    }

    public List<V> buscar(K k) {
        return buscar(raiz, k);
    }

    private List<V> buscar(NoArvoreB<K, V> x, K k) {
        if (x == null) return null;
        int i = 0;
		// Busca Linear
        while (i < x.chaves.size() && k.compareTo(x.chaves.get(i)) > 0) i++;
        // Se encontrou a chave, retorna a lista de valores associados
        if (i < x.chaves.size() && k.compareTo(x.chaves.get(i)) == 0) return x.valores.get(i);
        if (x.folha) {
            return null;
        } else {
            return buscar(x.filhos.get(i), k);
        }
    }

    // --- Inserção ---
    public void inserir(K k, V v) {
        if (raiz == null) {
            raiz = new NoArvoreB<>(t, true);
            raiz.chaves.add(k);
            List<V> listaValores = new ArrayList<>();
            listaValores.add(v);
            raiz.valores.add(listaValores);
        } else {
            // Verifica se a chave já existe na raiz para apenas adicionar o valor
            // (Simplificação: Inserção completa verifica duplicatas na descida)
            // Aqui faremos a inserção padrão da árvore B.
            if (raiz.chaves.size() == 2 * t - 1) {
                NoArvoreB<K, V> s = new NoArvoreB<>(t, false);
                s.filhos.add(raiz);
                splitChild(s, 0, raiz);
                raiz = s;
                inserirNaoCheio(s, k, v);
            } else {
                inserirNaoCheio(raiz, k, v);
            }
        }
    }

    private void splitChild(NoArvoreB<K, V> x, int i, NoArvoreB<K, V> y) {
        NoArvoreB<K, V> z = new NoArvoreB<>(t, y.folha);
        
        // Passa a metade superior das chaves de y para z
        for (int j = 0; j < t - 1; j++) {
            z.chaves.add(y.chaves.remove(t));
            z.valores.add(y.valores.remove(t));
        }
        if (!y.folha) {
            for (int j = 0; j < t; j++) {
                z.filhos.add(y.filhos.remove(t));
            }
        }

        x.filhos.add(i + 1, z);
        x.chaves.add(i, y.chaves.remove(t - 1));
        x.valores.add(i, y.valores.remove(t - 1));
    }

    private void inserirNaoCheio(NoArvoreB<K, V> x, K k, V v) {
        int i = x.chaves.size() - 1;

        if (x.folha) {
            // Verifica se a chave já existe neste nó folha
            int pos = -1;
            for(int idx=0; idx < x.chaves.size(); idx++) {
                if(x.chaves.get(idx).compareTo(k) == 0) {
                    pos = idx;
                    break;
                }
            }
            if (pos != -1) {
                // Chave existe, apenas adiciona o valor à lista
                x.valores.get(pos).add(v);
            } else {
                // Insere nova chave ordenada
                while (i >= 0 && k.compareTo(x.chaves.get(i)) < 0) {
                    i--;
                }
                x.chaves.add(i + 1, k);
                List<V> lista = new ArrayList<>();
                lista.add(v);
                x.valores.add(i + 1, lista);
            }
        } else {
            while (i >= 0 && k.compareTo(x.chaves.get(i)) < 0) {
                i--;
            }
            i++;
            // Verifica se o filho está cheio
            if (x.filhos.get(i).chaves.size() == 2 * t - 1) {
                splitChild(x, i, x.filhos.get(i));
                if (k.compareTo(x.chaves.get(i)) > 0) {
                    i++;
                } else if (k.compareTo(x.chaves.get(i)) == 0) {
                    // Se durante o split a chave subiu, adiciona aqui
                     x.valores.get(i).add(v);
                     return;
                }
            }
            inserirNaoCheio(x.filhos.get(i), k, v);
        }
    }

    // --- Métodos de Impressão (Visualização) ---
    public void imprimirArvore() {
        imprimirNo(raiz, 0);
    }

    private void imprimirNo(NoArvoreB<K, V> x, int nivel) {
        if (x != null) {
            String indent = "   ".repeat(nivel); // Indentação baseada no nível
            System.out.print(indent + "Nivel " + nivel + " | Chaves: [ ");
            for (K chave : x.chaves) {
                System.out.print(chave + " | ");
            }
            System.out.println("]");

            // Recursão para os filhos
            if (!x.folha) {
                for (NoArvoreB<K, V> filho : x.filhos) {
                    imprimirNo(filho, nivel + 1);
                }
            }
        }
    }
}
