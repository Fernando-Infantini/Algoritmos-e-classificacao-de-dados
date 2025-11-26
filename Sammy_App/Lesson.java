public class Lesson {
	private static final double LESSON_COST = 20.0;

	private Equipment equipment;

	public Lesson(Equipment equipment) {
		this.equipment = equipment;
	}

	public double getValue(int time) {

		if (equipment.getEquipmentAndValues().canHaveLesson()) {
			return LESSON_COST * time;
		} else {
			return 0.0;
		}
	}
}
