/**
 * 
 */
package studentsportbookingapp.models;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import studentsportbookingapp.controllers.CourseController;
import studentsportbookingapp.utilities.ExerciseDaysEnum;
import studentsportbookingapp.utilities.ExerciseNamesEnum;
import studentsportbookingapp.utilities.LessonPricing;
import studentsportbookingapp.utilities.SessionsEnum;

/**
 * @author Rakeshsharma
 *
 */
public class TimeTable {

	private static TimeTable instance = null;
	public List<Days> daysList;

	public static TimeTable getInstance() {
		if (instance == null) {
			instance = new TimeTable();
		}
		return instance;
	}

	public TimeTable getTimeTable() {
		TimeTable tbl = TimeTable.getInstance();

		var week1day1 = assignDayTimeTable(LocalDate.of(2022, Month.APRIL, 2), ExerciseDaysEnum.SATURDAY, true);
		var week1day2 = assignDayTimeTable(LocalDate.of(2022, Month.APRIL, 3), ExerciseDaysEnum.SUNDAY, false);

		var week2day1 = assignDayTimeTable(LocalDate.of(2022, Month.APRIL, 9), ExerciseDaysEnum.SATURDAY, true);
		var week2day2 = assignDayTimeTable(LocalDate.of(2022, Month.APRIL, 10), ExerciseDaysEnum.SUNDAY, false);

		var week3day1 = assignDayTimeTable(LocalDate.of(2022, Month.APRIL, 16), ExerciseDaysEnum.SATURDAY, true);
		var week3day2 = assignDayTimeTable(LocalDate.of(2022, Month.APRIL, 17), ExerciseDaysEnum.SUNDAY, false);

		var week4day1 = assignDayTimeTable(LocalDate.of(2022, Month.APRIL, 23), ExerciseDaysEnum.SATURDAY, true);
		var week4day2 = assignDayTimeTable(LocalDate.of(2022, Month.APRIL, 24), ExerciseDaysEnum.SUNDAY, false);

		var week5day1 = assignDayTimeTable(LocalDate.of(2022, Month.APRIL, 30), ExerciseDaysEnum.SATURDAY, true);
		var week5day2 = assignDayTimeTable(LocalDate.of(2022, Month.MAY, 1), ExerciseDaysEnum.SUNDAY, false);

		var week6day1 = assignDayTimeTable(LocalDate.of(2022, Month.MAY, 7), ExerciseDaysEnum.SATURDAY, true);
		var week6day2 = assignDayTimeTable(LocalDate.of(2022, Month.MAY, 8), ExerciseDaysEnum.SUNDAY, false);

		var week7day1 = assignDayTimeTable(LocalDate.of(2022, Month.MAY, 14), ExerciseDaysEnum.SATURDAY, true);
		var week7day2 = assignDayTimeTable(LocalDate.of(2022, Month.MAY, 15), ExerciseDaysEnum.SUNDAY, false);

		var week8day1 = assignDayTimeTable(LocalDate.of(2022, Month.MAY, 21), ExerciseDaysEnum.SATURDAY, true);
		var week8day2 = assignDayTimeTable(LocalDate.of(2022, Month.MAY, 22), ExerciseDaysEnum.SUNDAY, false);

		tbl.daysList = new ArrayList<Days>(
				Arrays.asList(week1day1, week1day2, week2day1, week2day2, week3day1, week3day2, week4day1, week4day2,
						week5day1, week5day2, week6day1, week6day2, week7day1, week7day2, week8day1, week8day2));

		return tbl;
	}

	public static Days assignDayTimeTable(LocalDate date, ExerciseDaysEnum dayName, boolean isDay1) {
		Days dayObj = new Days(date, dayName);
		if (isDay1) {

			var lesson1 = assignLesson(ExerciseNamesEnum.YOGA, SessionsEnum.MORNING);
			var lesson2 = assignLesson(ExerciseNamesEnum.ZUMBA, SessionsEnum.AFTERNOON);
			var lesson3 = assignLesson(ExerciseNamesEnum.AQUACISE, SessionsEnum.EVENING);
			dayObj.lessonList = new ArrayList<Lesson>(Arrays.asList(lesson1, lesson2, lesson3));
		} else {
			var lesson1 = assignLesson(ExerciseNamesEnum.BOX_FIT, SessionsEnum.MORNING);
			var lesson2 = assignLesson(ExerciseNamesEnum.BODY_BLITZ, SessionsEnum.AFTERNOON);
			var lesson3 = assignLesson(ExerciseNamesEnum.BOXING, SessionsEnum.EVENING);
			dayObj.lessonList = new ArrayList<Lesson>(Arrays.asList(lesson1, lesson2, lesson3));
		}

		return dayObj;

	}

	public static Lesson assignLesson(ExerciseNamesEnum lessonName, SessionsEnum sessionName) {

		Lesson lessonDetails = new Lesson();
		lessonDetails.setSessionName(sessionName);

		switch (lessonName) {

		case YOGA:
			lessonDetails.setLessonName(lessonName);
			lessonDetails.setLessonPrice(LessonPricing.YOGA_PRICE);

			break;
		case ZUMBA:
			lessonDetails.setLessonName(lessonName);
			lessonDetails.setLessonPrice(LessonPricing.ZUMBA_PRICE);
			break;
		case AQUACISE:
			lessonDetails.setLessonName(lessonName);
			lessonDetails.setLessonPrice(LessonPricing.AQUACISE_PRICE);
			break;
		case BOX_FIT:
			lessonDetails.setLessonName(lessonName);
			lessonDetails.setLessonPrice(LessonPricing.BOX_FIT_PRICE);
			break;
		case BODY_BLITZ:
			lessonDetails.setLessonName(lessonName);
			lessonDetails.setLessonPrice(LessonPricing.BODY_BLITZ_PRICE);
			break;
		case BOXING:
			lessonDetails.setLessonName(lessonName);
			lessonDetails.setLessonPrice(LessonPricing.BOXING_PRICE);
			break;
		default:
			break;
		}
		return lessonDetails;
	}

	public static boolean checkLessonAndDateAvailable(ExerciseNamesEnum lessonName, LocalDate date) {
		boolean isAvailable = false;
		var lst = TimeTable.getInstance().getTimeTable().daysList;
		var lessonDetails = TimeTable.getInstance().getTimeTable().daysList.stream()
				.filter(s -> s.getDate().isEqual(date)).findFirst().orElse(null);
		if (lessonDetails != null && lessonDetails.lessonList != null) {
			isAvailable = lessonDetails.lessonList.stream().anyMatch(p -> p.getLessonName().equals(lessonName));

		}
		return isAvailable;

	}

	public static List<Days> getTimeTableByMonthName(int month) {
		var list = TimeTable.getInstance().getTimeTable().daysList.stream()
				.filter(s -> s.getDate().getMonthValue() == month).collect(Collectors.toList());
		return list;
	}
}
