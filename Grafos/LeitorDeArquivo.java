import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorDeArquivo {

    public static void ler(String caminho, GrafoListaAdjacencia gLista, GrafoMatrizAdjacencia gMatriz, boolean dirigido) {

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

            String linha;

            while ((linha = br.readLine()) != null) {
                linha = linha.trim();

                if (linha.isEmpty() || linha.startsWith("#") || linha.startsWith("p"))
                    continue;

                String[] partes = linha.split("\\s+");
                if (partes.length < 2) continue;

                int u = Integer.parseInt(partes[0]);
                int v = Integer.parseInt(partes[1]);
                double peso = (partes.length >= 3) ? Double.parseDouble(partes[2]) : 1.0;

                gLista.adicionarAresta(u, v, peso);
                gMatriz.adicionarAresta(u, v, peso);

                if (!dirigido) {
                    gLista.adicionarAresta(v, u, peso);
                    gMatriz.adicionarAresta(v, u, peso);
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
}
