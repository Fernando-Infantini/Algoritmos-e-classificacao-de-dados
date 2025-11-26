import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Rentals implements Serializable {
    private List<Rental> rentalList;

    public Rentals() {
        this.rentalList = new ArrayList<>();
    }

    public String newRental(int id, int time, boolean hasLesson) throws InvalidEquipmentException {
        Equipment equipment = createEquipment(id, hasLesson);
        Rental rental = new Rental(time, equipment);
        rentalList.add(rental);
        return "Aluguel criado: " + rental.toString();
    }

    private Equipment createEquipment(int id, boolean hasLesson) throws InvalidEquipmentException {
        EquipmentAndValues values = getEquipmentValuesById(id);
        Equipment equipment;

        if (hasLesson) {
            equipment = new EquipmentWithLesson(id, values);
        } else {
            equipment = new EquipmentWithoutLesson(id, values);
        }

        return equipment;
    }

    private EquipmentAndValues getEquipmentValuesById(int id) throws InvalidEquipmentException {
        for (EquipmentAndValues value : EquipmentAndValues.values()) {
            if (value.ordinal() == id - 1) {
                return value;
            }
        }
        throw new InvalidEquipmentException(id, "Alugueis");
    }

    public void saveToFile(String name) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name))) {
            oos.writeObject(rentalList);
            System.out.println("Alugueis salvos no arquivo: " + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String listAll() {
        StringBuilder result = new StringBuilder();
        for (Rental rental : rentalList) {
            result.append(rental.toString()).append("\n");
        }
        return result.toString();
    }

    public int size() {
        return rentalList.size();
    }
}
