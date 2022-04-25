package studentsportbookingapp.utilities;



public enum ExerciseNamesEnum {
	YOGA("Yoga"),
	ZUMBA("Zumba"),
	AQUACISE("Aquacise"),
	BOX_FIT("Box Fit"),
	BODY_BLITZ("Body Blitz"),
	BOXING("Boxing");
	
	private final String exerciseName;

	ExerciseNamesEnum(String exerciseName) {
		this.exerciseName = exerciseName;
    }
    public String getlessonName() {
        return exerciseName;
    }
}

