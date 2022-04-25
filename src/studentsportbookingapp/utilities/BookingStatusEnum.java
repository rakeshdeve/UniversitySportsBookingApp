package studentsportbookingapp.utilities;

public enum BookingStatusEnum {
	BOOKED("Booked"), 
	ATTENDED("Attended"), 
	CHANGED("Changed"), 
	CANCELLED("Cancelled");
	private final String statusName;

	BookingStatusEnum(String status) {
		this.statusName = status;
    }
    public String getStatus() {
        return statusName;
    }
}
