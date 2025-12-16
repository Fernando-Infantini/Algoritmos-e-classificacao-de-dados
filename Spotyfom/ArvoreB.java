import java.util.ArrayList;
import java.util.List;

public class ArvoreB<K extends Comparable<K>, V> {
    private NoArvoreB<K, V> raiz;
    private int t; // Grau mínimo

    public ArvoreB(int t) {
        this.raiz = null;
        this.t = t;
    }

    // ================= BUSCA =================
    public List<V> buscar(K k) {
        return buscar(raiz, k);
    }

    private List<V> buscar(NoArvoreB<K, V> x, K k) {
        if (x == null) return null;
        int i = 0;
        while (i < x.chaves.size() && k.compareTo(x.chaves.get(i)) > 0) {
            i++;
        }
        if (i < x.chaves.size() && k.compareTo(x.chaves.get(i)) == 0) {
            return x.valores.get(i);
        }
        if (x.folha) return null;
        return buscar(x.filhos.get(i), k);
    }

    // ================= INSERÇÃO =================
    public void inserir(K k, V v) {
        if (raiz == null) {
            raiz = new NoArvoreB<>(true);
            raiz.chaves.add(k);
            List<V> lista = new ArrayList<>();
            lista.add(v);
            raiz.valores.add(lista);
        } else {
            if (raiz.chaves.size() == 2 * t - 1) {
                NoArvoreB<K, V> s = new NoArvoreB<>(false);
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
        NoArvoreB<K, V> z = new NoArvoreB<>(y.folha);
        
        // Move as chaves finais de y para z
        for (int j = 0; j < t - 1; j++) {
            z.chaves.add(y.chaves.remove(t));
            z.valores.add(y.valores.remove(t));
        }
        // Move os filhos finais de y para z
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
            // Verifica se chave já existe na folha
            int pos = -1;
            for(int idx = 0; idx < x.chaves.size(); idx++){
                if(x.chaves.get(idx).compareTo(k) == 0) {
                    pos = idx; 
                    break;
                }
            }
            if (pos != -1) {
                x.valores.get(pos).add(v); // Adiciona na lista existente
            } else {
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
            if (x.filhos.get(i).chaves.size() == 2 * t - 1) {
                splitChild(x, i, x.filhos.get(i));
                if (k.compareTo(x.chaves.get(i)) > 0) {
                    i++;
                } else if(k.compareTo(x.chaves.get(i)) == 0){
                    x.valores.get(i).add(v);
                    return;
                }
            }
            inserirNaoCheio(x.filhos.get(i), k, v);
        }
    }

    // ================= REMOÇÃO =================
    public boolean remover(K k, V valorParaRemover) {
        if (raiz == null) return false;

        boolean removeu = remover(raiz, k, valorParaRemover);

        if (raiz.chaves.isEmpty()) {
            if (raiz.folha) raiz = null;
            else raiz = raiz.filhos.get(0);
        }
        return removeu;
    }

    private boolean remover(NoArvoreB<K, V> x, K k, V valorParaRemover) {
        int idx = encontrarChave(x, k);

        if (idx < x.chaves.size() && x.chaves.get(idx).compareTo(k) == 0) {
            List<V> lista = x.valores.get(idx);
            boolean removeuItem = lista.remove(valorParaRemover);
            
            // Se ainda tem itens na lista, o trabalho acabou (não muda estrutura)
            if (!lista.isEmpty()) return removeuItem;

            // Se a lista ficou vazia, removemos a chave estruturalmente
            if (x.folha) {
                x.chaves.remove(idx);
                x.valores.remove(idx);
            } else {
                removerDeNaoFolha(x, idx);
            }
            return true;
        }

        if (x.folha) return false;

        boolean flag = (idx == x.chaves.size());
        if (x.filhos.get(idx).chaves.size() < t) {
            preencher(x, idx);
        }

        if (flag && idx > x.chaves.size()) {
            return remover(x.filhos.get(idx - 1), k, valorParaRemover);
        } else {
            return remover(x.filhos.get(idx), k, valorParaRemover);
        }
    }

    private int encontrarChave(NoArvoreB<K, V> x, K k) {
        int idx = 0;
        while (idx < x.chaves.size() && x.chaves.get(idx).compareTo(k) < 0) idx++;
        return idx;
    }

    private void removerDeNaoFolha(NoArvoreB<K, V> x, int idx) {
        K k = x.chaves.get(idx);
        if (x.filhos.get(idx).chaves.size() >= t) {
            NoArvoreB<K, V> cur = x.filhos.get(idx);
            while (!cur.folha) cur = cur.filhos.get(cur.filhos.size() - 1);
            
            K predK = cur.chaves.get(cur.chaves.size() - 1);
            List<V> predV = cur.valores.get(cur.valores.size() - 1);
            
            x.chaves.set(idx, predK);
            x.valores.set(idx, new ArrayList<>(predV)); // Copia a lista
            
            // Remove recursivamente o predecessor usando um item dummy da lista
            remover(x.filhos.get(idx), predK, predV.get(0));
        } else if (x.filhos.get(idx + 1).chaves.size() >= t) {
            NoArvoreB<K, V> cur = x.filhos.get(idx + 1);
            while (!cur.folha) cur = cur.filhos.get(0);
            
            K sucK = cur.chaves.get(0);
            List<V> sucV = cur.valores.get(0);
            
            x.chaves.set(idx, sucK);
            x.valores.set(idx, new ArrayList<>(sucV));
            
            remover(x.filhos.get(idx + 1), sucK, sucV.get(0));
        } else {
            merge(x, idx);
            remover(x.filhos.get(idx), k, null); // null força remoção estrutural se necessário na recursão
        }
    }

    private void preencher(NoArvoreB<K, V> x, int idx) {
        if (idx != 0 && x.filhos.get(idx - 1).chaves.size() >= t)
            pegarEmprestadoAnt(x, idx);
        else if (idx != x.chaves.size() && x.filhos.get(idx + 1).chaves.size() >= t)
            pegarEmprestadoProx(x, idx);
        else {
            if (idx != x.chaves.size()) merge(x, idx);
            else merge(x, idx - 1);
        }
    }

    private void pegarEmprestadoAnt(NoArvoreB<K, V> x, int idx) {
        NoArvoreB<K, V> filho = x.filhos.get(idx);
        NoArvoreB<K, V> irmao = x.filhos.get(idx - 1);
        
        filho.chaves.add(0, x.chaves.get(idx - 1));
        filho.valores.add(0, x.valores.get(idx - 1));
        
        if (!filho.folha) filho.filhos.add(0, irmao.filhos.remove(irmao.filhos.size() - 1));
        
        x.chaves.set(idx - 1, irmao.chaves.remove(irmao.chaves.size() - 1));
        x.valores.set(idx - 1, irmao.valores.remove(irmao.valores.size() - 1));
    }

    private void pegarEmprestadoProx(NoArvoreB<K, V> x, int idx) {
        NoArvoreB<K, V> filho = x.filhos.get(idx);
        NoArvoreB<K, V> irmao = x.filhos.get(idx + 1);
        
        filho.chaves.add(x.chaves.get(idx));
        filho.valores.add(x.valores.get(idx));
        
        if (!filho.folha) filho.filhos.add(irmao.filhos.remove(0));
        
        x.chaves.set(idx, irmao.chaves.remove(0));
        x.valores.set(idx, irmao.valores.remove(0));
    }

    private void merge(NoArvoreB<K, V> x, int idx) {
        NoArvoreB<K, V> filho = x.filhos.get(idx);
        NoArvoreB<K, V> irmao = x.filhos.get(idx + 1);
        
        filho.chaves.add(x.chaves.remove(idx));
        filho.valores.add(x.valores.remove(idx));
        
        for (int i = 0; i < irmao.chaves.size(); i++) {
            filho.chaves.add(irmao.chaves.get(i));
            filho.valores.add(irmao.valores.get(i));
        }
        if (!filho.folha) {
            for (int i = 0; i < irmao.filhos.size(); i++) {
                filho.filhos.add(irmao.filhos.get(i));
            }
        }
        x.filhos.remove(idx + 1);
    }

    public void imprimirArvore() {
        imprimirNo(raiz, 0);
    }

    private void imprimirNo(NoArvoreB<K, V> x, int nivel) {
        if (x != null) {
            String indent = "   ".repeat(nivel);
            System.out.print(indent + "Nivel " + nivel + " | Chaves: " + x.chaves);
            System.out.println(" (Valores: " + x.valores.stream().mapToInt(List::size).boxed().toList() + " itens)");
            if (!x.folha) {
                for (NoArvoreB<K, V> f : x.filhos) imprimirNo(f, nivel + 1);
            }
        }
    }
}