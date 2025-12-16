public class Musica {
    private String banda;
    private int codigo;
    private String nome;
    private String letra;
    private int linhaNoArquivo; // Para saber onde está no txt

    public Musica(String banda, int codigo, String nome, String letra, int linhaNoArquivo) {
        this.banda = banda;
        this.codigo = codigo;
        this.nome = nome;
        this.letra = letra;
        this.linhaNoArquivo = linhaNoArquivo;
    }

    public String toString() {
        return "Line: " + linhaNoArquivo + " | Code: " + codigo + " | Band: " + banda + " | Song: " + nome;
    }

    public String getBanda() { return banda; }
    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getLetra() { return letra; }
    public String toFileString() {
        // Formato: Banda;Codigo;Nome;Letra;LIXO
        // O "0" no final é o lixo padrão que definimos para manter o formato
        return banda + ";" + codigo + ";" + nome + ";" + letra + ";0"; 
    }
}
