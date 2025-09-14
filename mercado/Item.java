public class Item{
    private int quantidade;
    private Produto prod;

    public Item(){
        quantidade = 0;
        prod = new Produto();
    }

    public Item(int quantidade, Produto prod){
        this.quantidade = quantidade;
        this.prod = prod;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public Produto getProd(){
        return prod;
    }

    public boolean removeEstoque(){
        return prod.removeEstoque(quantidade); 
    }

    public double getSubtotal(){
        return prod.getPreco() * quantidade;
    }

    public String toString(){
        String s = "Nome: " + prod.getDesc() + " Valor: " + prod.getPreco() + " Quantidade: " + quantidade;
        return s;
    }


}