public class EquipmentWithoutLesson extends Equipment {
    
    public EquipmentWithoutLesson(int type, EquipmentAndValues values) {
        super(type, values);
        validateEquipmentType();
    }

    private void validateEquipmentType() {
        
    }
}