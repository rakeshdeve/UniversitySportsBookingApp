/**
 * 
 */
package studentsportbookingapp.utilities;

import java.util.HashMap;

/**
 * @author Rakeshsharma
 *
 */
public class LessonPricing {

	public static final double YOGA_PRICE = 8.99;
	public static final double ZUMBA_PRICE = 7.99;
	public static final double AQUACISE_PRICE = 6.99;
	public static final double BOX_FIT_PRICE = 5.99;
	public static final double BODY_BLITZ_PRICE = 4.99;
	public static final double BOXING_PRICE = 3.99;
	
	public static double getPriceByLessonName(ExerciseNamesEnum lessonName) {
		HashMap<ExerciseNamesEnum,Double> lessonList = new HashMap<ExerciseNamesEnum, Double>();
		lessonList.put(ExerciseNamesEnum.YOGA, YOGA_PRICE);
		lessonList.put(ExerciseNamesEnum.ZUMBA, ZUMBA_PRICE);
		lessonList.put(ExerciseNamesEnum.AQUACISE, AQUACISE_PRICE);
		lessonList.put(ExerciseNamesEnum.BOX_FIT, BOX_FIT_PRICE);
		lessonList.put(ExerciseNamesEnum.BODY_BLITZ, BODY_BLITZ_PRICE);
		lessonList.put(ExerciseNamesEnum.BOXING, BOXING_PRICE);
		
		return lessonList.get(lessonName);
	}
}

