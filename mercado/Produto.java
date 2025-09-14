public class Produto{
    private Descricao desc;
    private double preco;
    private int quantidadeEstoque;

    public Produto(){
        preco = 0;
        quantidadeEstoque = 0;
        desc = Descricao.VAZIO;
    }

    public Produto(Descricao desc, double preco, int quantidadeEstoque){
        this.desc = desc;
        this.preco = preco;
        this.quantidadeEstoque= quantidadeEstoque;
    }

    public Descricao getDesc(){
        return desc;
    }

    public double getPreco(){
        return preco;
    }

    public int getEstoque(){
        return quantidadeEstoque;
    }

    public void reporEstoque(int quantidade){
        quantidadeEstoque += quantidade;
    }
    public boolean removeEstoque(int quantidade) {
        if (quantidadeEstoque >= quantidade) {
            quantidadeEstoque -= quantidade;
            return true;
        } else {
            return false;
        }
    }

    public String toString(){
        String s = "Produto: " + desc + " Valor: " + preco + "  Estoque: " + quantidadeEstoque;
        return s;
    }
}