public class Cronometro {
    private long inicio;

    public void iniciar() {
        inicio = System.nanoTime();
    }

    public double tempoDecorridoMs() {
        long fim = System.nanoTime();
        return (fim - inicio) / 1_000_000.0; 
    }
}
