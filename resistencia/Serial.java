import java.util.ArrayList;

public class Serial extends Circuit{

    public ArrayList<Circuit> serie;

    public Serial(){
        serie = new ArrayList<Circuit>();
    }

    public void addResistor(Circuit r){
        serie.add(r);
    }

    public double getResistance(){
        double valor = 0;

        for(Circuit r : serie){
            valor += r.getResistance();
        }

        return valor;
    }
}