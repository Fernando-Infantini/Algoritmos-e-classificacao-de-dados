import java.io.Serializable;

public class Equipment implements Serializable, Constants {
    private int type;
    private String description;
    private EquipmentAndValues equipmentAndValues;

    public Equipment(int type, EquipmentAndValues values) {
        this.type = type;
        this.description = values.getDescription();
        this.equipmentAndValues = values;
    }

    public double getValue(int time) {
        double basicRate = equipmentAndValues.getBasicTax();
        double hourlyRate = equipmentAndValues.getTaxPerHour();
        double lessonCost = equipmentAndValues.canHaveLesson() ? 20.0 * time : 0.0;

        return basicRate + (hourlyRate * time) + lessonCost;
    }

    public EquipmentAndValues getEquipmentAndValues() {
        return equipmentAndValues;
    }

    public String getDescription() {
        return description;
    }

    public int getType() {
        return type;
    }
}