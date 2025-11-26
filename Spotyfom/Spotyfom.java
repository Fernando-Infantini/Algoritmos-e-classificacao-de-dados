import java.io.*;
import java.util.*;

public class Spotyfom {

    public static Scanner sc = new Scanner(System.in);

    public static List<Musica> acervo = new LinkedList<>();
    public static Queue<Musica> playlistAleatoria = new ArrayDeque<>();
    public static Stack<Musica> playlistUsuario = new Stack<>();

    // ================= UTIL =================

    public static void imprime(Musica m) {
        System.out.println("----------------------------");
        System.out.println("Titulo: " + m.titulo);
        System.out.println("Artista: " + m.artista);
        System.out.println("Codigo: " + m.codigo);
        System.out.println("Execucoes: " + m.execucoes);
        System.out.println("Letra: " + m.letra);
    }

    public static void imprimirAcervo() {
        if (acervo.isEmpty()) {
            System.out.println("Acervo vazio.");
            return;
        }

        for (Musica m : acervo)
            imprime(m);
    }

    public static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }

    public static int lerOpcaoSegura() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    // ================= ARQUIVO =================

    public static void lerArquivo(String nome) {
        try (BufferedReader br = new BufferedReader(new FileReader(nome))) {

            int n = Integer.parseInt(br.readLine());
            acervo.clear();

            for (int i = 0; i < n; i++) {
                String[] p = br.readLine().split(";");

                Musica m = new Musica();
                m.artista = p[0];
                m.codigo = Integer.parseInt(p[1]);
                m.titulo = p[2];
                m.letra = p[3];
                m.execucoes = 0;

                acervo.add(m);
            }

            System.out.println("\nArquivo carregado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao abrir arquivo: " + e.getMessage());
        }
    }

    public static void backup(String nome) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nome))) {

            pw.println(acervo.size());

            for (Musica m : acervo)
                pw.println(m.artista + ";" + m.codigo + ";" + m.titulo + ";" + m.letra + ";" + m.execucoes);

            System.out.println("\nBackup salvo com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao salvar backup: " + e.getMessage());
        }
    }

    public static void lerBackup(String nome) {
        try (BufferedReader br = new BufferedReader(new FileReader(nome))) {

            int n = Integer.parseInt(br.readLine());
            acervo.clear();

            for (int i = 0; i < n; i++) {
                String[] p = br.readLine().split(";");

                Musica m = new Musica();
                m.artista = p[0];
                m.codigo = Integer.parseInt(p[1]);
                m.titulo = p[2];
                m.letra = p[3];
                m.execucoes = Integer.parseInt(p[4]);

                acervo.add(m);
            }

            System.out.println("\nBackup carregado com sucesso!");

        } catch (Exception e) {
            System.out.println("Backup invalido: " + e.getMessage());
        }
    }

    // ================= BUSCAS =================

    public static void buscaTitulo() {
        if (acervo.isEmpty()) {
            System.out.println("Acervo vazio.");
            return;
        }

        System.out.print("Digite o titulo: ");
        String titulo = sc.nextLine().trim();

        boolean achou = false;
        for (Musica m : acervo)
            if (m.titulo.equalsIgnoreCase(titulo)) {
                imprime(m);
                achou = true;
            }

        if (!achou)
            System.out.println("Nenhuma musica encontrada.");
    }

    public static void buscaArtista() {
        if (acervo.isEmpty()) {
            System.out.println("Acervo vazio.");
            return;
        }

        System.out.print("Digite o artista: ");
        String artista = sc.nextLine().trim();

        boolean achou = false;
        for (Musica m : acervo)
            if (m.artista.equalsIgnoreCase(artista)) {
                imprime(m);
                achou = true;
            }

        if (!achou)
            System.out.println("Nenhuma musica encontrada.");
    }

    public static void buscaCodigo() {
        if (acervo.isEmpty()) {
            System.out.println("Acervo vazio.");
            return;
        }

        System.out.print("Digite o codigo: ");
        int codigo = lerOpcaoSegura();

        boolean achou = false;
        for (Musica m : acervo)
            if (m.codigo == codigo) {
                imprime(m);
                achou = true;
            }

        if (!achou)
            System.out.println("Nenhuma musica encontrada.");
    }

    // ================= PLAYLIST =================

    public static void criaPlaylistAleatoria() {

        if (acervo.isEmpty()) {
            System.out.println("Acervo vazio.");
            return;
        }

        playlistAleatoria.clear();
        System.out.print("Quantidade de musicas: ");
        int q = lerOpcaoSegura();

        if (q <= 0) {
            System.out.println("Quantidade invalida.");
            return;
        }

        if (q > acervo.size()) {
            System.out.println("Quantidade maior que acervo. Ajustado.");
            q = acervo.size();
        }

        Random r = new Random();

        for (int i = 0; i < q; i++)
            playlistAleatoria.add(acervo.get(r.nextInt(acervo.size())));

        System.out.println("\nPlaylist aleatoria criada:");
        for (Musica m : playlistAleatoria)
            imprime(m);
    }

    public static void criaPlaylistUsuario() {

        if (acervo.isEmpty()) {
            System.out.println("Acervo vazio.");
            return;
        }

        playlistUsuario.clear();
        int op;

        do {
            System.out.println("\n1. Buscar por titulo");
            System.out.println("2. Buscar por codigo");
            System.out.println("0. Finalizar");
            System.out.print("Opcao: ");

            op = lerOpcaoSegura();

            if (op == 1) {
                System.out.print("Titulo: ");
                String t = sc.nextLine();

                boolean achou = false;
                for (Musica m : acervo)
                    if (m.titulo.equalsIgnoreCase(t)) {
                        playlistUsuario.push(m);
                        achou = true;
                    }

                if (!achou)
                    System.out.println("Musica nao encontrada.");
            }

            if (op == 2) {
                System.out.print("Codigo: ");
                int c = lerOpcaoSegura();

                boolean achou = false;
                for (Musica m : acervo)
                    if (m.codigo == c) {
                        playlistUsuario.push(m);
                        achou = true;
                    }

                if (!achou)
                    System.out.println("Codigo invalido.");
            }

        } while (op != 0);

        if (playlistUsuario.isEmpty()) {
            System.out.println("\nPlaylist vazia.");
            return;
        }

        System.out.println("\nPlaylist criada:");
        for (Musica m : playlistUsuario)
            imprime(m);
    }

    public static void executaAleatoria() {
        if (playlistAleatoria.isEmpty()) {
            System.out.println("Playlist vazia.");
            return;
        }

        while (!playlistAleatoria.isEmpty()) {
            Musica m = playlistAleatoria.poll();
            m.execucoes++;
            imprime(m);
        }
    }

    public static void executaUsuario() {
        if (playlistUsuario.isEmpty()) {
            System.out.println("Playlist vazia.");
            return;
        }

        while (!playlistUsuario.isEmpty()) {
            Musica m = playlistUsuario.pop();
            m.execucoes++;
            imprime(m);
        }
    }

    // ================= RELATORIO =================

    public static void relatorio() {

        if (acervo.isEmpty()) {
            System.out.println("Nao ha dados.");
            return;
        }

        int total = 0;
        Musica top = acervo.get(0);

        for (Musica m : acervo) {
            total += m.execucoes;
            if (m.execucoes > top.execucoes)
                top = m;
        }

        System.out.println("\n========== RELATORIO ==========");
        System.out.println("Total de musicas: " + acervo.size());
        System.out.println("Total de execucoes: " + total);
        System.out.println("\nMais tocada:");
        imprime(top);

        acervo.sort((a, b) -> b.execucoes - a.execucoes);

        System.out.println("\nRanking:");
        int pos = 1;
        for (Musica m : acervo)
            System.out.println(pos++ + "º - " + m.titulo + " (" + m.execucoes + ")");

        System.out.println("===============================");
    }
}
