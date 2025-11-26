public class EquipmentWithLesson extends Equipment {
	public EquipmentWithLesson(int type, EquipmentAndValues values) {
		super(type, values);
		validateEquipmentType();
	}

	private void validateEquipmentType() {
		if (getType() < 1 || getType() > 5) {
			throw new InvalidEquipmentException(getType(), "Desabilitar incluir aula");
		}
	}
}