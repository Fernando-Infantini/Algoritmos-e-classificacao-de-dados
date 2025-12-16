import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Spotyfom {
    // Definindo as 3 árvores (Índices)
    private static ArvoreB<String, Musica> arvoreNome = new ArvoreB<>(3);
    private static ArvoreB<String, Musica> arvoreBanda = new ArvoreB<>(3);
    private static ArvoreB<Integer, Musica> arvoreCodigo = new ArvoreB<>(3);

    private static String nomeArquivo = "musicas.txt";
    private static int ultimaLinhaLida = 0; 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== CONFIGURACAO INICIAL ===");
        System.out.print("Digite o nome do arquivo inicial (padrao: musicas.txt): ");
        String input = sc.nextLine();
        if(!input.trim().isEmpty()) nomeArquivo = input;

        carregarDados();
        menu(sc);
    }

    // Reinicializa as árvores para limpar a memória antes de carregar novo arquivo
    private static void resetarArvores() {
        arvoreNome = new ArvoreB<>(3);
        arvoreBanda = new ArvoreB<>(3);
        arvoreCodigo = new ArvoreB<>(50);
        ultimaLinhaLida = 0;
        System.gc(); // Sugere ao Java limpar o lixo de memória
    }

    private static void carregarDados() {
        System.out.println("Carregando " + nomeArquivo + "...");
        File arquivo = new File(nomeArquivo);
        
        if (!arquivo.exists()) {
            System.out.println(">>> Arquivo '" + nomeArquivo + "' nao encontrado. Ele sera criado ao inserir dados.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha = br.readLine(); // Cabeçalho
            if (linha != null) ultimaLinhaLida = 1;

            while ((linha = br.readLine()) != null) {
                ultimaLinhaLida++;
                if (linha.trim().isEmpty()) continue;

                try {
                    String[] partes = linha.split(";");
                    if (partes.length >= 3) {
                        String banda = partes[0].trim();
                        int codigo = Integer.parseInt(partes[1].trim());
                        String nome = partes[2].trim();
                        String letra = (partes.length > 3) ? partes[3].trim() : "Sem letra cadastrada";
                        
                        Musica m = new Musica(banda, codigo, nome, letra, ultimaLinhaLida);
                        indexarMusica(m);
                    }
                } catch (Exception e) {
                    // Ignora linhas mal formatadas
                }
            }
            System.out.println(">>> Carga concluida! " + ultimaLinhaLida + " linhas processadas.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void indexarMusica(Musica m) {
        arvoreNome.inserir(m.getNome().toUpperCase(), m);
        arvoreBanda.inserir(m.getBanda().toUpperCase(), m);
        arvoreCodigo.inserir(m.getCodigo(), m);
    }

    private static void menu(Scanner sc) {
        while (true) {
            System.out.println("\n=== SPOTYFOM 2.0 (" + nomeArquivo + ") ===");
            System.out.println("1. Buscar por Nome");
            System.out.println("2. Buscar por Banda");
            System.out.println("3. Buscar por Codigo");
            System.out.println("4. Adicionar Musica");
            System.out.println("5. Remover Musica");
            System.out.println("6. Visualizar Arvore");
            System.out.println("7. Trocar Arquivo");
            System.out.println("0. Sair");
            System.out.print("Opcao: ");
            String op = sc.nextLine();

            switch (op) {
                case "1":
                    System.out.print("Nome: ");
                    exibirResultado(arvoreNome.buscar(sc.nextLine().toUpperCase()));
                    break;
                case "2":
                    System.out.print("Banda: ");
                    exibirResultado(arvoreBanda.buscar(sc.nextLine().toUpperCase()));
                    break;
                case "3":
                    System.out.print("Codigo: ");
                    try {
                        exibirResultado(arvoreCodigo.buscar(Integer.parseInt(sc.nextLine())));
                    } catch(Exception e) { System.out.println("Erro: Digite um numero."); }
                    break;
                case "4": cadastrarMusica(sc); break;
                case "5": removerMusica(sc); break;
                case "6":
                    System.out.println("1-Nome, 2-Banda, 3-Codigo");
                    String tipo = sc.nextLine();
                    if(tipo.equals("1")) arvoreNome.imprimirArvore();
                    else if(tipo.equals("2")) arvoreBanda.imprimirArvore();
                    else if(tipo.equals("3")) arvoreCodigo.imprimirArvore();
                    break;
                case "7": trocarArquivo(sc); break;
                case "0": return;
                default: System.out.println("Invalido.");
            }
        }
    }

    private static void trocarArquivo(Scanner sc) {
        System.out.print("Digite o nome do novo arquivo (ex: rock.txt): ");
        String novoNome = sc.nextLine();
        File f = new File(novoNome);
        
        if (f.exists()) {
            System.out.println("Arquivo encontrado. Limpando memoria e recarregando...");
            nomeArquivo = novoNome;
            resetarArvores(); // Zera as árvores antigas
            carregarDados();  // Carrega as novas
        } else {
            System.out.println("Erro: O arquivo '" + novoNome + "' nao existe na pasta.");
            System.out.println("Deseja criar um novo vazio com esse nome? (S/N)");
            if (sc.nextLine().equalsIgnoreCase("S")) {
                nomeArquivo = novoNome;
                resetarArvores();
                System.out.println("Arquivo novo definido. Use a opcao 4 para adicionar musicas.");
            }
        }
    }

    private static void exibirResultado(List<Musica> lista) {
        if (lista == null || lista.isEmpty()) System.out.println(">>> Nao encontrado.");
        else {
            System.out.println(">>> " + lista.size() + " resultado(s):");
            for (Musica m : lista) {
                System.out.println("------------------------------------------------");
                System.out.println(m); // Aqui ele chama o toString()
            }
            System.out.println("------------------------------------------------");
        }
    }

    private static void cadastrarMusica(Scanner sc) {
        try {
            System.out.print("Banda: "); String banda = sc.nextLine();
            System.out.print("Codigo: "); int codigo = Integer.parseInt(sc.nextLine());
            System.out.print("Musica: "); String nome = sc.nextLine();
            System.out.print("Letra: "); String letra = sc.nextLine();

            ultimaLinhaLida++;
            Musica m = new Musica(banda, codigo, nome, letra, ultimaLinhaLida);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
                File f = new File(nomeArquivo);
                if (f.length() > 0) bw.newLine();
                bw.write(m.toFileString());
            }

            indexarMusica(m);
            System.out.println("Sucesso! Salvo em " + nomeArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            ultimaLinhaLida--;
        }
    }

    private static void removerMusica(Scanner sc) {
        System.out.print("Digite o CODIGO para remover: ");
        try {
            int cod = Integer.parseInt(sc.nextLine());
            List<Musica> encontrados = arvoreCodigo.buscar(cod);
            
            if (encontrados == null || encontrados.isEmpty()) {
                System.out.println("Codigo nao encontrado.");
                return;
            }

            Musica m = encontrados.get(0);
            System.out.println("Removendo: " + m.getNome());

            arvoreCodigo.remover(m.getCodigo(), m);
            arvoreNome.remover(m.getNome().toUpperCase(), m);
            arvoreBanda.remover(m.getBanda().toUpperCase(), m);

            removerDoArquivo(cod);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void removerDoArquivo(int codigoAlvo) {
        File arqOriginal = new File(nomeArquivo);
        File arqTemp = new File("temp_" + nomeArquivo);

        try (BufferedReader br = new BufferedReader(new FileReader(arqOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(arqTemp))) {

            String linha;
            boolean primeiro = true;

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                
                boolean copiar = true;
                try {
                    String[] partes = linha.split(";");
                    if (partes.length >= 2) {
                        int codLido = Integer.parseInt(partes[1].trim());
                        if (codLido == codigoAlvo) copiar = false;
                    }
                } catch (Exception e) {}

                if (copiar) {
                    if (!primeiro) bw.newLine();
                    bw.write(linha);
                    primeiro = false;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro de IO: " + e.getMessage());
            return;
        }

        if (arqOriginal.delete()) arqTemp.renameTo(arqOriginal);
        else System.out.println("Erro ao substituir arquivo.");
    }
}