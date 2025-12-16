import java.util.*;

public class TopSort {

    public static void executar(Grafo g) {
        int v = g.getNumeroVertices();
        boolean[] visitado = new boolean[v];
        boolean[] emPilhaRecursiva = new boolean[v]; 
        Stack<Integer> pilha = new Stack<>();
        
        boolean temCiclo = false; 

        for (int i = 0; i < v; i++) {
            if (!visitado[i])
                temCiclo = dfs(g, i, visitado, emPilhaRecursiva, pilha); 
            
            if (temCiclo) {
                System.out.println("TopSort: IMPOSSÍVEL. O grafo contém um ciclo.");
                return;
            }
        }

        System.out.print("TopSort: ");
        while (!pilha.isEmpty())
            System.out.print(pilha.pop() + " ");
        System.out.println();
    }

    private static boolean dfs(Grafo g, int v, boolean[] vis, boolean[] emPilha, Stack<Integer> p) {
        vis[v] = true;
        emPilha[v] = true;

        for (Aresta e : g.getAdjacentes(v)) {
            
            if (!vis[e.destino]) {
                if (dfs(g, e.destino, vis, emPilha, p))
                    return true;
            } 
            
            else if (emPilha[e.destino]) { 
                return true;
            }
        }
        
        emPilha[v] = false;
        p.push(v);
        return false;
    }
}