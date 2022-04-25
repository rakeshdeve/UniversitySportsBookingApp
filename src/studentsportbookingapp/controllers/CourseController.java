/**
 * 
 */
package studentsportbookingapp.controllers;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

	public void bookGroupExerciseView() {
		courseView.bookGroupExerciseView();
	}

	public void changeBookingView() {
		courseView.changeBookingView();
	}

	public void attendLessonAndRatingView() {
		courseView.attendLessonAndRatingView();
	}

	// Get Student Details By Id
	public Student getStudentDetailsbyId(int studentId) {
		Student studentDetails = new Student();
		if (studentId > 0) {
			var preRegisteredStudents = getPreregisteredStudents();
			if (preRegisteredStudents != null && !preRegisteredStudents.isEmpty()) {
				// Predicate
				studentDetails = preRegisteredStudents.stream().filter(s -> s.getStudentId() == studentId).findFirst()
						.orElse(null);
			}
		}
		return studentDetails;
	}

	public ArrayList<Student> getPreregisteredStudents() {
		ArrayList<Student> preRegStudentList = PreRegisteredStudents.getPreRegisteredStudents();
		return preRegStudentList;
	}

	public long getStudentCount(ExerciseNamesEnum lessonName, LocalDate date) {
		long studentCount = 0;
		studentCount = CourseController
				.getBookingList().stream().filter(s -> s.getLessonDate().isEqual(date)
						&& s.getLessonName().equals(lessonName) && s.getBookingStatus() != BookingStatusEnum.CANCELLED)
				.count();
		return studentCount;
	}

	public boolean checkIsLessonAlreadyBooked(int studentId, ExerciseNamesEnum lessonName, LocalDate date) {
		boolean isExist;
		isExist = CourseController.getBookingList().stream().anyMatch(s -> s.getStudentId() == studentId
				&& s.getLessonDate().isEqual(date) && s.getLessonName().equals(lessonName));
		return isExist;

	}

	public void changeBoookingStatusByBookingId(String bookingId, BookingStatusEnum status) {
		if (bookingId != null && !bookingId.isBlank() && !bookingId.isEmpty()) {
			CourseController.getBookingList().stream().filter(f -> f.getBookingId().equals(bookingId)).map(t -> {
				t.setBookingStatus(status);
				return t;
			}).collect(Collectors.toList());
		}
	}

	public int getStudentIdByBookingId(String bookingId) {

		int studentId = 0;
		var studentObj = CourseController.getBookingList().stream().filter(
				f -> f.getBookingId().equals(bookingId) && f.getBookingStatus() != BookingStatusEnum.CANCELLED)
				.findFirst().orElse(null);
		studentId = studentObj != null && studentObj.getStudentId() > 0 ? studentObj.getStudentId() : 0;
		return studentId;
	}
	
	public void assignRating(String bookingId, int rating, String review) {
		CourseController.getBookingList().stream().filter(f -> f.getBookingId().equals(bookingId)).map(t -> {
			t.setRating(rating);
			t.setReview(review);
			return t;
		}).collect(Collectors.toList());
	}
}
