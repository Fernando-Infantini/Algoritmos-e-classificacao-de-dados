public class Cliente{
    private String nome;
    private String cpf;

    public Cliente(){
        nome = "Joao Ninguem";
        cpf = "00000000000";
    }

    public Cliente(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome(){
        return nome;
    }

    public String getCpf(){
        return cpf;
    }

    public String toString(){
        String s = "Nome: " + nome + "CPF: " + cpf;
        return s;
    }
}