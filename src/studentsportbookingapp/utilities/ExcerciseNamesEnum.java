package studentsportbookingapp.utilities;



public enum ExcerciseNamesEnum {
	YOGA("Yoga"),
	ZUMBA("Zumba"),
	AQUACISE("Aquacise"),
	BOX_FIT("Box Fit"),
	BODY_BLITZ("Body Blitz"),
	BOXING("Boxing");
	
	private final String name;

	ExcerciseNamesEnum(String lessonName) {
        name = lessonName;
    }
    public String getlessonName() {
        return name;
    }
}

