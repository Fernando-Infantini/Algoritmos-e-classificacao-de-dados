public class Resistor extends Circuit{

    private double resistance;

    public Resistor(){
        resistance = 0;
    }

    public Resistor(double resistance){
        this.resistance = resistance;
    }

    public double getResistance(){
        return resistance;
    }
}