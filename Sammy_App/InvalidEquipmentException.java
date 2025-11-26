public class InvalidEquipmentException extends RuntimeException {
    private int id;
    private String className;

    public InvalidEquipmentException(int id, String className) {
        super("Equipamento inválido: id=" + id + ", classe = " + className);
        this.id = id;
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }
}
