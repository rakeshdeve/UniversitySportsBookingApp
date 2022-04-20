/**
 * 
 */
package studentsportbookingapp.utilities;

/**
 * @author Rakeshsharma
 *
 */
public enum RatingEnum {
	VERY_DISSATISFIED("Very Dissatisfied"), 
	DISSATISFIED("Dissatisfied"),
	OK("Ok"),
	SATISFIED("Satisfied");
	
	private final String rating;

	RatingEnum(String rating) {
		this.rating = rating;
    }
    public String getRating() {
        return rating;
    }
}
