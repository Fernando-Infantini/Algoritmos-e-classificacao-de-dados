import java.io.Serializable;

public class Rental implements Serializable, Constants {
    private static int nextContractNumber = 1;

    private long contract;
    private int time;
    private double price;
    private Equipment equipment;

    public Rental(int time, Equipment equipment) {
        this.contract = nextContractNumber++;
        this.time = time;
        this.equipment = equipment;
        
        calculatePrice();
    }

    private void calculatePrice() {
        double basicRate = equipment.getEquipmentAndValues().getBasicTax();
        double hourlyRate = equipment.getEquipmentAndValues().getTaxPerHour();


        double hours = (double) time / MINUTESPERHOUR;

        double lessonCost = 0.0; // Começa em 0

        if (equipment instanceof EquipmentWithLesson) {
            lessonCost = 20.0;
        }

        this.price = basicRate + (hourlyRate * hours) + lessonCost;
    }

    public double totalPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Contrato = " + contract + "\nTempo = " + time + " minutos\nPreço = R$ " 
                + String.format("%.2f", price) + "\nEquipamento = "
                + equipment.getType() + " - " + equipment.getDescription() + "\n";
    }
}