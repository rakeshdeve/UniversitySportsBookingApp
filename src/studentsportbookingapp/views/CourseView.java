/**
 * 
 */
package studentsportbookingapp.views;

import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.*;

import studentsportbookingapp.controllers.*;
import studentsportbookingapp.models.*;
import studentsportbookingapp.utilities.*;

/**
 * @author Rakeshsharma
 *
 */
public class CourseView {

	Booking booking = new Booking();
	public int globalStudentId;
	public static final int MAX_STUDENT_COUNT = 4;
	String changeBookingId;

	public void bookGroupExcerciseView() {
		Scanner sc = new Scanner(System.in);
		System.out.println("--Student Id from 1 to 20");
		System.out.println("Enter your student Id:");

		if (!sc.hasNextInt()) {
			System.out.println("Invalid Student Id");
			return;
		}
		int studentId = sc.nextInt();
		if (studentId > 0 && studentId <= 20) {
			globalStudentId = studentId;
			getStudentDetailsbyId(studentId);
		} else {
			System.out.println("Invalid Student Id");
		}
	}

	public void getStudentDetailsbyId(int studentId) {
		if (studentId > 0) {
			var preRegisteredStudents = CourseController.getInstance().getPreregisteredStudents();
			if (preRegisteredStudents != null && !preRegisteredStudents.isEmpty()) {
				// Predicate
				var studentDetails = preRegisteredStudents.stream().filter(s -> s.getStudentId() == studentId)
						.findFirst().orElse(null);

				if (studentDetails != null) {
					System.out.println("Welcome " + studentDetails.getStudentName() + "!..");
					chooseTimetable();
				}
			}
		}

	}

	public void chooseTimetable() {

		Scanner sc = new Scanner(System.in);

		// Display menu
		System.out.println("\nChoose Timetable\n=====================");
		System.out.println("1. View timetable by day");
		System.out.println("2. View timetable by exercise name");
		System.out.println("Please enter your choice:");
		if (!sc.hasNextInt()) {
			System.out.println("Invalid choice");
			return;
		}
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			printExerciseDays();
			break;
		case 2:
			printExerciseNames();
			break;
		default:
			System.out.println("Invalid choice");
			return;
		}
	}

	public void printExerciseDays() {
		int i = 1;

		for (ExcerciseDaysEnum day : ExcerciseDaysEnum.values()) {
			System.out.println(i + ". " + day);
			i++;
		}
		System.out.println("Please enter your choice:");
		Scanner sc = new Scanner(System.in);
		if (!sc.hasNextInt()) {
			System.out.println("Invalid choice");
			return;
		}
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			printTimeTable(true, "SATURDAY", "");
			break;
		case 2:
			printTimeTable(true, "SUNDAY", "");
			break;
		default:
			System.out.println("Invalid choice");
			return;
		}

	}

	public void printExerciseNames() {
		int i = 1;
		for (ExcerciseNamesEnum excercise : ExcerciseNamesEnum.values()) {
			System.out.println(i + ". " + excercise.getlessonName());
			i++;
		}
		System.out.println("Please enter your choice:");
		Scanner sc = new Scanner(System.in);
		if (!sc.hasNextInt()) {
			System.out.println("Invalid choice");
			return;
		}
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			printTimeTable(false, "", ExcerciseNamesEnum.YOGA.toString());
			break;
		case 2:
			printTimeTable(false, "", ExcerciseNamesEnum.ZUMBA.toString());
			break;
		case 3:
			printTimeTable(false, "", ExcerciseNamesEnum.AQUACISE.toString());
			break;
		case 4:
			printTimeTable(false, "", ExcerciseNamesEnum.BOX_FIT.toString());
			break;
		case 5:
			printTimeTable(false, "", ExcerciseNamesEnum.BODY_BLITZ.toString());
			break;
		case 6:
			printTimeTable(false, "", ExcerciseNamesEnum.BOXING.toString());
			break;
		default:
			System.out.println("Invalid choice");
			return;
		}

	}

	public void printTimeTable(boolean isPrintByDay, String dayName, String excerciseName) {

		CommandLineTable st = new CommandLineTable();
		// st.setRightAlign(true);//if true then cell text is right aligned
		st.setShowVerticalLines(true);// if false (default) then no vertical lines are shown
		var list = TimeTable.getInstance().getTimeTable();

		// filter - get lesson by day
		if (isPrintByDay) {
			list.daysList = list.daysList.stream().filter(s -> s.getDay().toString() == dayName)
					.collect(Collectors.toList());
		}

		
		st.setHeaders("DAY", "DATE", "MORNING(Courses)", "PRICE", "AFTERNOON(Courses)", "PRICE", "EVENING(Courses)",
				"PRICE");
		for (var item : list.daysList) {
			var lessonList = item.lessonList;
			if (!isPrintByDay) {
				// filter - get lesson by lesson name

				lessonList = lessonList.stream().filter(s -> s.getLessonName().toString() == excerciseName)
						.collect(Collectors.toList());
			}
			var morningSession = lessonList.stream().filter(s -> s.getSessionName() == SessionsEnum.MORNING).findFirst()
					.orElse(null);
			var afternoonSession = lessonList.stream().filter(s -> s.getSessionName() == SessionsEnum.AFTERNOON)
					.findFirst().orElse(null);
			var eveningSession = lessonList.stream().filter(s -> s.getSessionName() == SessionsEnum.EVENING).findFirst()
					.orElse(null);
			if (morningSession != null || afternoonSession != null || eveningSession != null) {
				st.addRow(item.getDay().toString(), item.getDate(),
						(morningSession != null
								? morningSession.getLessonName().toString() + "(" + morningSession.getSessionName()
										+ ")"
								: "-"),
						(morningSession != null ? "$" + String.valueOf(morningSession.getLessonPrice()) : "-"),
						(afternoonSession != null
								? afternoonSession.getLessonName().toString() + "(" + afternoonSession.getSessionName()
										+ ")"
								: "-"),
						(afternoonSession != null ? "$" + String.valueOf(afternoonSession.getLessonPrice()) : "-"),
						(eveningSession != null
								? eveningSession.getLessonName().toString() + "(" + eveningSession.getSessionName()
										+ ")"
								: "-"),
						(eveningSession != null ? "$" + String.valueOf(eveningSession.getLessonPrice()) : "-"));
			}
		}
		st.print();
		bookSlotMenu();
	}

	// global variable
	ExcerciseNamesEnum lessonName;
	LocalDate lessonDate;

	public void bookSlotMenu() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Which course would you like to choose?");
		int i = 1;
		for (ExcerciseNamesEnum name : ExcerciseNamesEnum.values()) {
			System.out.print(i + "." + name.getlessonName() + "  ");
			i++;
		}
		System.out.println("Please choose a option:");
		if (!sc.hasNextInt()) {
			System.out.println("Invalid choice");
			return;
		}
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			lessonName = ExcerciseNamesEnum.YOGA;
			break;
		case 2:
			lessonName = ExcerciseNamesEnum.ZUMBA;
			break;
		case 3:
			lessonName = ExcerciseNamesEnum.AQUACISE;
			break;
		case 4:
			lessonName = ExcerciseNamesEnum.BOX_FIT;
			break;
		case 5:
			lessonName = ExcerciseNamesEnum.BODY_BLITZ;
			break;
		case 6:
			lessonName = ExcerciseNamesEnum.BOXING;
			break;
		default:
			System.out.println("Invalid choice");
			return;
		}

		System.out.println("Enter the date you need to need book for the slot(in format: dd/MM/yyyy):");
		Scanner input = new Scanner(System.in);
		String dateString;
		do {
			dateString = input.nextLine();
		} while (!Utility.isValidDate(dateString));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		lessonDate = LocalDate.parse(dateString, formatter);

		char confirmOption;
		do {
			if (changeBookingId != null && !changeBookingId.isEmpty() && !changeBookingId.isBlank()) {
				System.out.println("Are you sure want to change the booking? (Type Y or N): ");
			} else {
				System.out.println("Are you sure want to confirm the booking? (Type Y or N): ");
			}

			confirmOption = input.next().charAt(0);
			confirmOption = Character.toLowerCase(confirmOption);
		} while (confirmOption != 'y' && confirmOption != 'n');

		if (confirmOption == 'y') {
			createBooking();
		} else {
			return;
		}

	}

	public void createBooking() {
		if (TimeTable.CheckLessonAndDateAvailable(lessonName, lessonDate)) {

			if (!checkIsLessonAlreadyBooked(globalStudentId, lessonName, lessonDate)) {
				if (CourseController.getInstance().getStudentCount(lessonName, lessonDate) < MAX_STUDENT_COUNT) {

					if (changeBookingId != null && !changeBookingId.isBlank() && !changeBookingId.isEmpty()) {
						// Change Booking
						CourseController.getBookingList().stream().filter(f -> f.getBookingId().equals(changeBookingId)).map(t -> {
							t.setBookingStatus(BookingStatusEnum.CHANGED);
							t.setLessonName(lessonName);
							t.setLessonDate(lessonDate);
							t.setBookingDate(LocalDate.now());
							t.setLessonPrice(LessonPricing.getPriceByLessonName(lessonName));
							return t;
						}).collect(Collectors.toList());
						System.out.println(lessonName.getlessonName() + " changed successfully!");
						System.out.println("Your Booking Id is " + changeBookingId);
						changeBookingId = null;

					} else {
						// New booking
						var list = CourseController.getBookingList();
						var bookingObj = new Booking();
						bookingObj.setBookingId(Utility.generateRandomString());
						bookingObj.setBookingStatus(BookingStatusEnum.BOOKED);
						bookingObj.setLessonName(lessonName);
						bookingObj.setLessonDate(lessonDate);
						bookingObj.setBookingDate(LocalDate.now());
						bookingObj.setStudentId(globalStudentId);
						bookingObj.setLessonPrice(LessonPricing.getPriceByLessonName(lessonName));
						list.add(bookingObj);
						CourseController.setStudentBookingList(list);
						System.out.println(lessonName.getlessonName() + " booked successfully!");
						System.out.println("Your Booking Id is " + bookingObj.getBookingId());
					}

				} else {
					System.out.println("Slot not available for selected date and lesson");
				}
			} else {
				System.out.println("Please try a different date, slot already booked for this student");
			}
		} else {
			System.out.println("Record not exist in timetable");
		}
		HomeController.getInstance().getMainView();
		globalStudentId = 0;

	}

	public boolean checkIsLessonAlreadyBooked(int studentId, ExcerciseNamesEnum lessonName, LocalDate date) {
		boolean isExist;
		isExist = CourseController.getBookingList().stream().anyMatch(s -> s.getStudentId() == studentId
				&& s.getLessonDate().isEqual(date) && s.getLessonName().equals(lessonName));
		return isExist;

	}

	public void changeBookingView() {

		Scanner sc = new Scanner(System.in);
		System.out.println("\nChange a Booking\n=====================");
		System.out.println("1. Change to a new lesson");
		System.out.println("2. Cancel a booking");
		System.out.println("Please enter your choice:");
		if (!sc.hasNextInt()) {
			System.out.println("Invalid choice");
			return;
		}
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			changeToNewLesson();
			break;
		case 2:
			cancelBookingById();
			break;
		default:
			System.out.println("Invalid choice");
			return;
		}

	}

	public void changeToNewLesson() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Booking Id:");

		do {
			changeBookingId = sc.nextLine();
		} while (changeBookingId.isEmpty() && changeBookingId.isBlank());
		globalStudentId = getStudentIdByBookingId(changeBookingId);
		if (globalStudentId > 0) {
			chooseTimetable();
		} else {
			System.out.println("Invalid Booking Id:");
		}

	}

	

	public void cancelBookingById() {

		String cancelBookingId;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your Booking Id:");

		do {
			cancelBookingId = input.nextLine();
		} while (cancelBookingId.isEmpty() && cancelBookingId.isBlank());
		char confirmOption;
		do {
			System.out.println("Are you sure want to cancel the booking? (Type Y or N): ");
			confirmOption = input.next().charAt(0);
			confirmOption = Character.toLowerCase(confirmOption);
		} while (confirmOption != 'y' && confirmOption != 'n');

		// if confirm change the status
		if (confirmOption == 'y') {
			globalStudentId = getStudentIdByBookingId(cancelBookingId);
			if (globalStudentId > 0) {

				// Cancel booking
				changeBoookingStatusByBookingId(cancelBookingId, BookingStatusEnum.CANCELLED);
				
				System.out.println("Your booking cancelled successfully!");
				HomeController.getInstance().getMainView();
				globalStudentId = 0;
			} else {
				System.out.println("Invalid Booking Id:");
			}
		} else {
			HomeController.getInstance().getMainView();
			globalStudentId = 0;
		}

	}


	public void changeBoookingStatusByBookingId(String bookingId, BookingStatusEnum status) {
		if (bookingId != null && !bookingId.isBlank() && !bookingId.isEmpty()) {
			CourseController.getBookingList().stream().filter(f -> f.getBookingId().equals(bookingId)).map(t -> {
				t.setBookingStatus(status);
				return t;
			}).collect(Collectors.toList());
		}
	}

	public int getStudentIdByBookingId(String changeBookingId) {

		int studentId = 0;
		var studentObj = CourseController.getBookingList().stream().filter(
				f -> f.getBookingId().equals(changeBookingId) && f.getBookingStatus() != BookingStatusEnum.CANCELLED)
				.findFirst().orElse(null);
		studentId = studentObj != null && studentObj.getStudentId() > 0 ? studentObj.getStudentId() : 0;
		return studentId;
	}

	public void attendLessonAndRatingView() {
		String bookingId;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your Booking Id:");

		do {
			bookingId = input.nextLine();
		} while (bookingId.isEmpty() && bookingId.isBlank());
		char confirmOption;
		do {
			System.out.println("Are you sure want to attend a lesson? (Type Y or N): ");
			confirmOption = input.next().charAt(0);
			confirmOption = Character.toLowerCase(confirmOption);
		} while (confirmOption != 'y' && confirmOption != 'n');

		// if confirm change the status to attended
		if (confirmOption == 'y') {
			changeBoookingStatusByBookingId(bookingId, BookingStatusEnum.ATTENDED);
			ratingMenuView(bookingId);
		} else {
			HomeController.getInstance().getMainView();
		}
	}
	public void ratingMenuView(String bookingId) {
		Scanner sc = new Scanner(System.in);
		int rating;
		String review;
		System.out.println("Please provide the rating");
		int i = 1;
		for (RatingEnum value : RatingEnum.values()) {
			System.out.print(i + "." + value.getRating() + "  ");
			i++;
		}
		System.out.println("\nPlease choose a rating option:");
		if (!sc.hasNextInt()) {
			System.out.println("Invalid option");
			return;
		}
		rating = sc.nextInt();

		while(rating < 1 && rating > 4)
		{
			System.out.println("Please enter a valid option: ");
			rating = sc.nextInt();
		}
//		switch (choice) {
//		case 1:
//			rating = 1;
//			break;
//		case 2:
//			rating = 2;
//			break;
//		case 3:
//			rating = 3;
//			break;
//		case 4:
//			rating = RatingEnum.SATISFIED;
//			break;
//		default:
//			System.out.println("Invalid option");
//			return;
//		}

		System.out.println("Please write a review:");
		Scanner input = new Scanner(System.in);
		do {
			review = input.nextLine();
		} while (review.isBlank() && review.isEmpty());
		
		assignRatingAndReview(bookingId,rating,review);
		System.out.println("Thank you for your rating and review");
		HomeController.getInstance().getMainView();
	
	}
	
	public void assignRatingAndReview(String bookingId, int rating, String review) {
		CourseController.getBookingList().stream().filter(f -> f.getBookingId().equals(bookingId)).map(t -> {
			t.setRating(rating);
			t.setReview(review);
			return t;
		}).collect(Collectors.toList());
	}

}
