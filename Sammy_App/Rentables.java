public interface Rentables {
    String newRental(int id, int time, boolean hasLesson) throws InvalidEquipmentException, NumberFormatException;
    void saveToFile(String name);
    String listAll();
    int size();
}
