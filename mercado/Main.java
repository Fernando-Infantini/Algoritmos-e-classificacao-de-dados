import java.util.Scanner;
import java.util.LinkedList;

public class Main {

    public static Cliente criaCl(Scanner sc){
        String nome;
        String cpf;

        System.out.println("Digite nome do cliente: ");
        sc.nextLine();
        nome = sc.nextLine();
        System.out.println("Digite CPF do cliente: ");
        cpf = sc.nextLine();

        Cliente c = new Cliente(nome, cpf);

        return c;
    }

    public static void main(String[] args) {

        int op = 1;
        Scanner sc = new Scanner(System.in);
        Cliente cl;
        Pedido p = new Pedido();
        int temps;
        int tempi;
        TipoPagamento[] formas = TipoPagamento.values();

        LinkedList<Produto> estoque = new LinkedList<Produto>();

        estoque.add(new Produto(Descricao.ARROZ, 10, 100));
        estoque.add(new Produto(Descricao.FEIJAO, 15, 50));        
        estoque.add(new Produto(Descricao.FARINHA, 5, 65));
        estoque.add(new Produto(Descricao.LEITE, 7, 35));        
        estoque.add(new Produto(Descricao.CHOCOLATE, 5, 25));
        estoque.add(new Produto(Descricao.CARNE, 37, 72));

        while(op != 0){
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("==========MENU==========");
            System.out.println("1.Novo Pedido");
            System.out.println("2.Realizar Pagamento");
            System.out.println("0.Sair do aplicativo");
            System.out.println("Digite opcao: ");

            op = sc.nextInt();

            switch(op){
                case 1:

                    cl = criaCl(sc);
                    p = new Pedido(cl);

                    while(op != 2){
                        System.out.println("");
                        System.out.println("==========SACOLA==========");
                        System.out.println("1.Novo Item");
                        System.out.println("2.Fechar Sacola");
                        System.out.println("Digite opcao: ");
                        op = sc.nextInt();
                        System.out.println("");
                        System.out.println("");

                        switch(op){
                            case 1:
                                System.out.println("Produtos:\n1.Arroz \n2.Feijao \n3.Farinha \n4.Leite \n5.Chocolate \n6.Carne\nDigite produto: ");
                                temps = sc.nextInt();
                                System.out.println("Digite quantidade: ");
                                tempi = sc.nextInt();
                                System.out.println("");
                                System.out.println("");

                                p.adicionarItem(new Item(tempi, estoque.get(temps-1)));
                                break;

                            case 2:
                                System.out.println("\nPedido Finalizado\n");
                                break;

                            default:
                                System.out.println("\nOPCAO INVALIDA\n");
                                break;
                        }
                    }

                    break;
                
                case 2:
                    if (p.getItem().isEmpty()) {
                        System.out.println("Nenhum pedido criado!");
                    } 
                    else {

                        System.out.println("Valor Final = " + p.calculaTotal());
                        System.out.println("Formas de pagamento:\n1.Dinheiro\n2.Debito\n3,Credito\n4.Pix\n5.Cheque\nInsira forma de pagamento: ");
                        tempi = sc.nextInt();

                        if(tempi >= 1 && tempi <= 5){
                            p.realizarPagamento(formas[tempi]);
                            
                            System.out.println("\nFatura: \n" + p.toString());
                        }
                        else{
                            System.out.println("Forma de pagamento invalida!!!");
                        }
                    }
                    break;
                
                case 0:
                    System.out.println("SAINDO!!!");
                    break;
                
                default:
                    System.out.println("\nOPCAO INVALIDA\n");
                    break;
            }
        }

        sc.close();
    }
}
