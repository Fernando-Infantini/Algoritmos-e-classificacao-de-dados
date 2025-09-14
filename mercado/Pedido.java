import java.util.ArrayList;

public class Pedido{

    private TipoPagamento pag;
    private Cliente cliente;
    private ArrayList<Item> itens;

    public Pedido(){
        pag = TipoPagamento.A_PAGAR;
        cliente = new Cliente();
        itens = new ArrayList<Item>();
    }

    public Pedido(Cliente cliente){
        pag = TipoPagamento.A_PAGAR;
        this.cliente = cliente;
        itens = new ArrayList<Item>();
    }

    public TipoPagamento getPag(){
        return pag;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public ArrayList<Item> getItem(){
        return itens;
    }

    public void adicionarItem(Item it) {
        if (it.removeEstoque()) { 
            itens.add(it);
        } else {
            System.out.println("Estoque insuficiente para " + it.getProd().getDesc());
        }
    }

    public double calculaTotal(){
        double total = 0;

        for(Item i : itens){
            total += i.getSubtotal();
        }

        return total;
    }

    public void realizarPagamento(TipoPagamento tipo) {
        this.pag = tipo;
        System.out.println("Pagamento realizado com " + tipo);
        System.out.println("Total pago: " + calculaTotal());
    }

    public String toString() {
        String s = "====== FATURA ======\n";
        s += "Cliente: " + cliente.getNome() + " | CPF: " + cliente.getCpf() + "\n";
        s += "Itens:\n";

        for (Item i : itens) {
            s += "- " + i.getProd().getDesc()
               + " | Preço: " + i.getProd().getPreco()
               + " | Quantidade: " + i.getQuantidade()
               + " | Subtotal: " + i.getSubtotal() + "\n";
        }

        s += "Total do pedido: " + calculaTotal() + "\n";

        if (pag != TipoPagamento.A_PAGAR) {
            s += "Pagamento: " + pag + "\n";
        } else {
            s += "Pagamento: NÃO REALIZADO\n";
        }

        return s;
    }


}