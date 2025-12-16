public class Musica {
    private String banda;
    private int codigo;
    private String nome;
    private String letra;
    private int linhaNoArquivo;

    public Musica(String banda, int codigo, String nome, String letra, int linhaNoArquivo) {
        this.banda = banda;
        this.codigo = codigo;
        this.nome = nome;
        this.letra = letra;
        this.linhaNoArquivo = linhaNoArquivo;
    }

    // Getters
    public String getBanda() { return banda; }
    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getLetra() { return letra; }
    public int getLinhaNoArquivo() { return linhaNoArquivo; }

    // Formato para salvar no arquivo txt
    public String toFileString() {
        return banda + ";" + codigo + ";" + nome + ";" + letra + ";0"; 
    }

    public String toString() {
        return "Line: " + linhaNoArquivo + " | Code: " + codigo + " | Band: " + banda + " | Song: " + nome + "| Letra: " + letra;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Musica m = (Musica) obj;
        return this.codigo == m.codigo;
    }
}