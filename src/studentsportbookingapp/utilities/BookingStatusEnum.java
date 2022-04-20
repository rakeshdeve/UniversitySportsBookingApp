package studentsportbookingapp.utilities;

public enum BookingStatusEnum {
	BOOKED("Booked"), 
	ATTENDED("Attended"), 
	CHANGED("Changed"), 
	CANCELLED("Cancelled");
	private final String name;

	BookingStatusEnum(String status) {
        name = status;
    }
    public String getStatus() {
        return name;
    }
}
