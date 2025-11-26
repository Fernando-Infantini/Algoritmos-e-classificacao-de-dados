import java.io.Serializable;

public enum EquipmentAndValues implements Serializable {
	ONE("jet ski", 50.0, 30.0, true), TWO("barco pontão", 40.0, 30.0, true), THREE("barco a remo", 15.0, 20.0, true),
	FOUR("canoa", 12.0, 20.0, true), FIVE("caiaque", 10.0, 20.0, true), SIX("cadeira de praia", 2.0, 5.0, false),
	SEVEN("guarda-sol", 1.0, 5.0, false), EIGHT("gazebo", 3.0, 7.0, false);

	private final String description;
	private final double basicTax;
	private final double taxPerHour;
	private final boolean canHaveLesson;

	EquipmentAndValues(String desc, double tax, double perHour, boolean lesson) {
		this.description = desc;
		this.basicTax = tax;
		this.taxPerHour = perHour;
		this.canHaveLesson = lesson;
	}

	public String getDescription() {
		return description;
	}

	public double getBasicTax() {
		return basicTax;
	}

	public double getTaxPerHour() {
		return taxPerHour;
	}

	public boolean canHaveLesson() {
		return canHaveLesson;
	}
}