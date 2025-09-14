import java.util.ArrayList;

public class Parallel extends Circuit{

    public ArrayList<Circuit> parallel;

    public Parallel(){
        parallel = new ArrayList<Circuit>();
    }

    public void addResistor(Circuit r){
        parallel.add(r);
    }

    public double getResistance(){
        double valor = 0;

        for(Circuit r : parallel){
            valor += 1/r.getResistance();
        }

        valor = 1/valor;

        return valor;
    }
}