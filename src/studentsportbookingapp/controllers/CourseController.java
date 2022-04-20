/**
 * 
 */
package studentsportbookingapp.controllers;

import java.time.LocalDate;
import java.util.*;

import studentsportbookingapp.models.*;
import studentsportbookingapp.utilities.*;
import studentsportbookingapp.views.CourseView;

/**
 * @author Rakeshsharma
 *
 */
public class CourseController {

	private CourseView courseView;
	private static CourseController instance = null;
	private static List<Booking> studentBookingList = new ArrayList<Booking>();

	public static void setStudentBookingList(List<Booking> bookingList) {
		CourseController.studentBookingList = bookingList;
	}

	public static List<Booking> getBookingList() {
		return studentBookingList;
	}

	public CourseController() {
		this.courseView = new CourseView();
		CourseController.studentBookingList.addAll(BookingData.bookingHistoryData());
	}

	public static CourseController getInstance() {
		if (instance == null) {
			instance = new CourseController();
		}
		return instance;
	}

	public void bookGroupExcerciseView() {
		courseView.bookGroupExcerciseView();
	}

	public void changeBookingView() {
		courseView.changeBookingView();
	}

	public void attendLessonAndRatingView() {
		courseView.attendLessonAndRatingView();
	}

	public ArrayList<Student> getPreregisteredStudents() {
		ArrayList<Student> preRegStudentList = PreRegisteredStudents.GetPreRegisteredStudents();
		return preRegStudentList;
	}
	
	public long getStudentCount(ExcerciseNamesEnum lessonName, LocalDate date) {
		long studentCount = 0;
		 studentCount = CourseController.getBookingList().stream().filter(s -> s.getLessonDate().isEqual(date) && s.getLessonName().equals(lessonName) && s.getBookingStatus() != BookingStatusEnum.CANCELLED).count();
		return studentCount;
	}
}
