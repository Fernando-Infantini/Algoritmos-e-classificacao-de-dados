import java.util.*;

public class Main {

    public static void main(String[] args) {

        int op;

        do {
            System.out.println("\n===============================");
            System.out.println("        SISTEMA SPOTYFOM       ");
            System.out.println("===============================");
            System.out.println("1. Ler arquivo");
            System.out.println("2. Playlists");
            System.out.println("3. Buscas");
            System.out.println("4. Imprimir acervo");
            System.out.println("5. Backup");
            System.out.println("6. Ler backup");
            System.out.println("7. Relatorio");
            System.out.println("0. Sair");
            System.out.println("===============================");
            System.out.print("Opcao: ");
            op = Spotyfom.lerOpcaoSegura();

            switch (op) {

                case 1:
                    System.out.print("Arquivo: ");
                    Spotyfom.lerArquivo(Spotyfom.sc.nextLine());
                    Spotyfom.pausar();
                    break;

                case 2:
                    menuPlaylists();
                    break;

                case 3:
                    menuBuscas();
                    break;

                case 4:
                    Spotyfom.imprimirAcervo();
                    Spotyfom.pausar();
                    break;

                case 5:
                    System.out.print("Arquivo backup: ");
                    Spotyfom.backup(Spotyfom.sc.nextLine());
                    Spotyfom.pausar();
                    break;

                case 6:
                    System.out.print("Arquivo backup: ");
                    Spotyfom.lerBackup(Spotyfom.sc.nextLine());
                    Spotyfom.pausar();
                    break;

                case 7:
                    Spotyfom.relatorio();
                    Spotyfom.pausar();
                    break;

                case 0:
                    System.out.println("\nEncerrando...");
                    break;

                default:
                    System.out.println("\nOpcao invalida!");
                    Spotyfom.pausar();
            }

        } while (op != 0);
    }

    public static void menuPlaylists() {

        int p;
        System.out.println("\n======= PLAYLISTS =======");
        System.out.println("1. Criar aleatoria");
        System.out.println("2. Criar do usuario");
        System.out.println("3. Tocar aleatoria");
        System.out.println("4. Tocar usuario");
        System.out.println("0. Voltar");
        System.out.print("Opcao: ");

        p = Spotyfom.lerOpcaoSegura();

        if (p == 1) Spotyfom.criaPlaylistAleatoria();
        else if (p == 2) Spotyfom.criaPlaylistUsuario();
        else if (p == 3) Spotyfom.executaAleatoria();
        else if (p == 4) Spotyfom.executaUsuario();
        else if (p == 0) return;
        else System.out.println("Opcao invalida!");

        Spotyfom.pausar();
    }

    public static void menuBuscas() {

        int b;
        System.out.println("\n========= BUSCAS =========");
        System.out.println("1. Titulo");
        System.out.println("2. Artista");
        System.out.println("3. Codigo");
        System.out.println("0. Voltar");
        System.out.print("Opcao: ");

        b = Spotyfom.lerOpcaoSegura();

        if (b == 1) Spotyfom.buscaTitulo();
        else if (b == 2) Spotyfom.buscaArtista();
        else if (b == 3) Spotyfom.buscaCodigo();
        else if (b == 0) return;
        else System.out.println("Opcao invalida!");

        Spotyfom.pausar();
    }
}
