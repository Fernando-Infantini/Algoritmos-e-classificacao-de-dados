import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Spotyfom {
    // Definindo as 3 árvores (Índices)
    private static ArvoreB<String, Musica> arvoreNome = new ArvoreB<>(3);
    private static ArvoreB<String, Musica> arvoreBanda = new ArvoreB<>(3);
    private static ArvoreB<Integer, Musica> arvoreCodigo = new ArvoreB<>(3);

    private static String nomeArquivo = "musicas.txt"; // Valor padrão
    private static int ultimaLinhaLida = 0; // Controla o número da linha

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== CONFIGURACAO INICIAL ===");
        System.out.print("Digite o nome do arquivo de musicas (ex: musicas.txt): ");
        String inputNome = sc.nextLine();
        if (!inputNome.trim().isEmpty()) {
            nomeArquivo = inputNome;
        }

        carregarDados();
        menu(sc);
    }

    private static void carregarDados() {
        System.out.println("Carregando arquivo " + nomeArquivo + "...");
        File arquivo = new File(nomeArquivo);
        
        // Zera contador caso recarregue
        ultimaLinhaLida = 0;

        if (!arquivo.exists()) {
            System.out.println("Arquivo nao encontrado. Sera criado ao adicionar musicas.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha = br.readLine(); // Lê cabeçalho (quantidade)
            ultimaLinhaLida = 1; // Linha 1 é o cabeçalho
            
            while ((linha = br.readLine()) != null) {
                ultimaLinhaLida++; // Incrementa contador de linha atual
                
                if (linha.trim().isEmpty()) continue; // Ignora linhas espaços, tabs e outros não visiveis.
                
                try {
                    String[] partes = linha.split(";");
                    if (partes.length >= 3) { // Garante o mínimo de dados
                        String banda = partes[0].trim();
                        int codigo = Integer.parseInt(partes[1].trim());
                        String nome = partes[2].trim();
                        // Trata caso a letra ou lixo não existam
                        String letra = (partes.length > 3) ? partes[3].trim() : "";
                        
                        Musica m = new Musica(banda, codigo, nome, letra, ultimaLinhaLida);
                        indexarMusica(m);
                    }
                } catch (Exception e) {
                    System.err.println("Erro de formato na linha " + ultimaLinhaLida + ": " + e.getMessage());
                }
            }
            System.out.println("Carga concluida! Total de linhas processadas: " + ultimaLinhaLida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void indexarMusica(Musica m) {
        arvoreNome.inserir(m.getNome().toUpperCase(), m);
        arvoreBanda.inserir(m.getBanda().toUpperCase(), m);
        arvoreCodigo.inserir(m.getCodigo(), m);
    }

    private static void adicionarMusicaNoArquivo(Musica m) {
        /*
         * Logica para evitar linha em branco extra:
         * 1. Verifica se o arquivo tem conteúdo.
         * 2. Se tiver, verifica se o ultimo caractere é newline (isso é complexo em Java simples).
         * 3. Solução Prática: Escrever sempre com newline ANTES, mas garantir que leitura ignore vazios.
         * Se o arquivo original terminar com \n, vai ficar um gap. 
         * Para o trabalho, usar o append simples é mais seguro.
         */
        try (FileWriter fw = new FileWriter(nomeArquivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            // Verifica se o arquivo existe e tem tamanho > 0 para decidir se põe quebra de linha inicial
            File f = new File(nomeArquivo);
            if (f.exists() && f.length() > 0) {
                bw.newLine();
            }
            
            bw.write(m.toFileString());
            // Não damos newLine no final para evitar criar gap na próxima inserção
            
        } catch (IOException e) {
            System.out.println("Erro ao salvar no disco: " + e.getMessage());
        }
    }

    private static void menu(Scanner sc) {
        while (true) {
            System.out.println("\n=== SPOTYFOM 2.0 (" + nomeArquivo + ") ===");
            System.out.println("1. Buscar por Nome da Musica");
            System.out.println("2. Buscar por Banda");
            System.out.println("3. Buscar por Codigo");
            System.out.println("4. Adicionar Nova Musica");
            System.out.println("5. Visualizar Estrutura das Arvores (Debug)");
            System.out.println("0. Sair");
            System.out.print("Opcao: ");
            
            String op = sc.nextLine();

            switch (op) {
                case "1":
                    System.out.print("Digite o nome da musica: ");
                    exibirResultado(arvoreNome.buscar(sc.nextLine().toUpperCase()));
                    break;
                case "2":
                    System.out.print("Digite a banda: ");
                    exibirResultado(arvoreBanda.buscar(sc.nextLine().toUpperCase()));
                    break;
                case "3":
                    System.out.print("Digite o codigo: ");
                    try {
                        exibirResultado(arvoreCodigo.buscar(Integer.parseInt(sc.nextLine())));
                    } catch (NumberFormatException e) {
                        System.out.println("Codigo invalido.");
                    }
                    break;
                case "4":
                    cadastrarMusica(sc);
                    break;
                case "5":
                    menuVisualizacao(sc);
                    break;
                case "0":
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }
    
    private static void menuVisualizacao(Scanner sc) {
        System.out.println("\n--- Qual arvore voce quer ver? ---");
        System.out.println("1. Arvore de Nomes");
        System.out.println("2. Arvore de Bandas");
        System.out.println("3. Arvore de Codigos");
        String op = sc.nextLine();
        
        System.out.println("\n--- ESTRUTURA DA ARVORE B ---");
        switch(op) {
            case "1": arvoreNome.imprimirArvore(); break;
            case "2": arvoreBanda.imprimirArvore(); break;
            case "3": arvoreCodigo.imprimirArvore(); break;
            default: System.out.println("Opcao invalida.");
        }
        System.out.println("-----------------------------");
    }

    private static void exibirResultado(List<Musica> resultado) {
        if (resultado == null || resultado.isEmpty()) {
            System.out.println(">>> Nenhuma musica encontrada.");
        } else {
            System.out.println(">>> Encontrado(s) " + resultado.size() + " registro(s):");
            for (Musica m : resultado) {
                System.out.println(m);
            }
        }
    }

    private static void cadastrarMusica(Scanner sc) {
        try {
            System.out.print("Nome da Banda: ");
            String banda = sc.nextLine();
            
            System.out.print("Codigo (Inteiro): ");
            int codigo = Integer.parseInt(sc.nextLine());
            
            System.out.print("Nome da Musica: ");
            String nome = sc.nextLine();
            
            System.out.print("Trecho da Letra: ");
            String letra = sc.nextLine();

            ultimaLinhaLida++; 
            
            Musica m = new Musica(banda, codigo, nome, letra, ultimaLinhaLida);
            
            adicionarMusicaNoArquivo(m); // Escreve no txt
            indexarMusica(m); // Atualiza na RAM
            
            System.out.println("Musica cadastrada na linha " + ultimaLinhaLida + " com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            // Se falhou, decrementa para manter consistência
            ultimaLinhaLida--; 
        }
    }
}
