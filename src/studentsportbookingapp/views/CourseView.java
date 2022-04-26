/**
 * 
 */
package studentsportbookingapp.views;

import java.time.*;
import java.time.format.DateTimeFormatter;
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

	public int globalStudentId;
	private static final int MAX_STUDENT_COUNT = 4;
	public String changeBookingId;

	public void bookGroupExerciseView() {
		Scanner sc = new Scanner(System.in);
		System.out.println("******* Book a group exercise lesson *********************");
		System.out.println("***********************************************************");
		System.out.println("Enter your student Id from 1 to 20:");
		int studentId = 0;
		while (!sc.hasNextInt()) {
			System.out.println("Invalid Student Id");
			sc.next();
		}

		studentId = sc.nextInt();
		if (studentId > 0 && studentId <= 20) {
			globalStudentId = studentId;
			var studentDetails = CourseController.getInstance().getStudentDetailsbyId(studentId);
			if (studentDetails != null) {
				System.out.println("Welcome " + studentDetails.getStudentName() + "!..\n");
				chooseTimetableView();
			}
		} else {
			System.out.println("Invalid Student Id");
			bookGroupExerciseView();
		}
	}

	public void chooseTimetableView() {

		Scanner sc = new Scanner(System.in);

		// Display menu
		System.out.println("******* Choose Timetable **********************************");
		System.out.println("***********************************************************");
		System.out.println("(1) View timetable by day");
		System.out.println("(2) View timetable by exercise name");
		System.out.println("(3) Exit");
		System.out.println("(0) Main menu");
		System.out.println("Please enter your choice:");

		while (!sc.hasNextInt()) {
			System.out.println("Invalid choice");
			sc.next();
		}
		int choice = sc.nextInt();
		switch (choice) {
		case 0:
			HomeController.getInstance().getMainView();
			break;
		case 1:
			printExerciseDays();
			break;
		case 2:
			printExerciseNames();
			break;
		case 3:
			break;
		default:
			System.out.println("Invalid choice");
			chooseTimetableView();
			return;
		}
	}

	public void printExerciseDays() {
		int i = 1;

		for (ExerciseDaysEnum day : ExerciseDaysEnum.values()) {
			System.out.println("(" + i + ") " + day);
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
		for (ExerciseNamesEnum exercise : ExerciseNamesEnum.values()) {
			System.out.println("(" + i + ") " + exercise.getlessonName());
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
			printTimeTable(false, "", ExerciseNamesEnum.YOGA.toString());
			break;
		case 2:
			printTimeTable(false, "", ExerciseNamesEnum.ZUMBA.toString());
			break;
		case 3:
			printTimeTable(false, "", ExerciseNamesEnum.AQUACISE.toString());
			break;
		case 4:
			printTimeTable(false, "", ExerciseNamesEnum.BOX_FIT.toString());
			break;
		case 5:
			printTimeTable(false, "", ExerciseNamesEnum.BODY_BLITZ.toString());
			break;
		case 6:
			printTimeTable(false, "", ExerciseNamesEnum.BOXING.toString());
			break;
		default:
			System.out.println("Invalid choice");
			return;
		}

	}

	public void printTimeTable(boolean isPrintByDay, String dayName, String exerciseName) {

		CommandLineTable st = new CommandLineTable();
		// st.setRightAlign(true);//if true then cell text is right aligned
		st.setShowVerticalLines(true);// if false (default) then no vertical lines are shown
		var list = TimeTable.getInstance().getTimeTable();

		// filter - get lesson by day
		if (isPrintByDay) {
			list.daysList = list.daysList.stream().filter(s -> s.getDay().toString() == dayName)
					.collect(Collectors.toList());
		}

		System.out.println(
				"****************************************** Timetable *****************************************************************");
		// System.out.println("");

		st.setHeaders("DAY", "DATE", "MORNING(Courses)", "PRICE", "AFTERNOON(Courses)", "PRICE", "EVENING(Courses)",
				"PRICE");
		for (var item : list.daysList) {
			var lessonList = item.lessonList;
			if (!isPrintByDay) {
				// filter - get lesson by lesson name

				lessonList = lessonList.stream().filter(s -> s.getLessonName().toString() == exerciseName)
						.collect(Collectors.toList());
			}
			var morningSession = lessonList.stream().filter(s -> s.getSessionName() == SessionsEnum.MORNING).findFirst()
					.orElse(null);
			var afternoonSession = lessonList.stream().filter(s -> s.getSessionName() == SessionsEnum.AFTERNOON)
					.findFirst().orElse(null);
			var eveningSession = lessonList.stream().filter(s -> s.getSessionName() == SessionsEnum.EVENING).findFirst()
					.orElse(null);
			if (morningSession != null || afternoonSession != null || eveningSession != null) {
				st.addRow(item.getDay().toString(), item.getStringDate(),
						(morningSession != null
								? morningSession.getLessonName().toString() + "("
										+ (MAX_STUDENT_COUNT
												- CourseController.getInstance()
														.getStudentCount(morningSession.getLessonName(), item.getDate())
												+ " slots left)")
								: "-"),
						(morningSession != null ? "$" + String.valueOf(morningSession.getLessonPrice()) : "-"),
						(afternoonSession != null
								? afternoonSession.getLessonName().toString() + "("
										+ (MAX_STUDENT_COUNT
												- CourseController.getInstance().getStudentCount(
														afternoonSession.getLessonName(), item.getDate())
												+ " slots left)")
								: "-"),
						(afternoonSession != null ? "$" + String.valueOf(afternoonSession.getLessonPrice()) : "-"),
						(eveningSession != null
								? eveningSession.getLessonName().toString() + "("
										+ (MAX_STUDENT_COUNT
												- CourseController.getInstance()
														.getStudentCount(eveningSession.getLessonName(), item.getDate())
												+ " slots left)")
								: "-"),
						(eveningSession != null ? "$" + String.valueOf(eveningSession.getLessonPrice()) : "-"));
			}
		}
		st.print();
		bookSlotMenu();
	}

	// global variable
	ExerciseNamesEnum lessonName;
	LocalDate lessonDate;

	public void bookSlotMenu() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Which course would you like to choose?");
		int i = 1;
		for (ExerciseNamesEnum name : ExerciseNamesEnum.values()) {
			System.out.print(i + "." + name.getlessonName() + "  ");
			i++;
		}
		System.out.println("\nPlease select the course shown in the timetable: ");
		if (!sc.hasNextInt()) {
			System.out.println("Invalid choice");
			return;
		}
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			lessonName = ExerciseNamesEnum.YOGA;
			break;
		case 2:
			lessonName = ExerciseNamesEnum.ZUMBA;
			break;
		case 3:
			lessonName = ExerciseNamesEnum.AQUACISE;
			break;
		case 4:
			lessonName = ExerciseNamesEnum.BOX_FIT;
			break;
		case 5:
			lessonName = ExerciseNamesEnum.BODY_BLITZ;
			break;
		case 6:
			lessonName = ExerciseNamesEnum.BOXING;
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
		if (TimeTable.checkLessonAndDateAvailable(lessonName, lessonDate)) {

			// Check lesson already booked or not
			if (!CourseController.getInstance().checkIsLessonAlreadyBooked(globalStudentId, lessonName, lessonDate)) {
				if (CourseController.getInstance().getStudentCount(lessonName, lessonDate) < MAX_STUDENT_COUNT) {

					if (changeBookingId != null && !changeBookingId.isBlank() && !changeBookingId.isEmpty()) {
						// Change Booking
						CourseController.getBookingList().stream().filter(f -> f.getBookingId().equals(changeBookingId))
								.map(t -> {
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
						System.out.println("Thank you for booking the exercise");
						System.out.println("Your course " + lessonName.getlessonName() + " is booked successfully!");
						System.out.println("Your Booking Id is " + bookingObj.getBookingId() + "\n");
					}

				} else {
					System.out.println("Sorry the slot is not available for selected date and lesson");
				}
			} else {
				System.out.println("Please try a different date, slot is already booked for this student");
			}
		} else {
			System.out.println("Record not exist in timetable");
		}
		goBackToMenuView();
		globalStudentId = 0;

	}

	public void changeBookingView() {

		Scanner sc = new Scanner(System.in);
		System.out.println("******* Change a Booking **********************************");
		System.out.println("***********************************************************");
		System.out.println("(1) Change to a new lesson");
		System.out.println("(2) Cancel a booking");
		System.out.println("(3) Exit");
		System.out.println("(0) Main menu");
		System.out.println("Please enter your choice:");
		while (!sc.hasNextInt()) {
			System.out.println("Invalid choice");
			sc.next();
		}
		int choice = sc.nextInt();
		switch (choice) {
		case 0:
			HomeController.getInstance().getMainView();
			break;
		case 1:
			changeToNewLesson();
			break;
		case 2:
			cancelBookingById();
			break;
		case 3:
			break;
		default:
			System.out.println("Invalid choice");
			changeBookingView();
		}

	}

	public void changeToNewLesson() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Booking Id:");

		do {
			changeBookingId = sc.nextLine();
		} while (changeBookingId.isEmpty() && changeBookingId.isBlank());
		globalStudentId = CourseController.getInstance().getStudentIdByBookingId(changeBookingId);
		if (globalStudentId > 0) {
			chooseTimetableView();
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
			globalStudentId = CourseController.getInstance().getStudentIdByBookingId(cancelBookingId);
			if (globalStudentId > 0) {

				// Cancel booking
				CourseController.getInstance().changeBoookingStatusByBookingId(cancelBookingId,
						BookingStatusEnum.CANCELLED);

				System.out.println("Your booking cancelled successfully!");
				goBackToMenuView();
				globalStudentId = 0;
			} else {
				System.out.println("Invalid Booking Id:");
			}
		} else {
			goBackToMenuView();
			globalStudentId = 0;
		}

	}

	public void attendLessonAndRatingView() {
		String bookingId;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your Booking Id:");

		do {
			bookingId = input.nextLine();
		} while (bookingId.isEmpty() && bookingId.isBlank());
		int studentId = CourseController.getInstance().getStudentIdByBookingId(changeBookingId);
		if (studentId > 0) {
			char confirmOption;
			do {
				System.out.println("Are you sure want to attend a lesson? (Type Y or N): ");
				confirmOption = input.next().charAt(0);
				confirmOption = Character.toLowerCase(confirmOption);
			} while (confirmOption != 'y' && confirmOption != 'n');

			// if confirm change the status to attended
			if (confirmOption == 'y') {
				CourseController.getInstance().changeBoookingStatusByBookingId(bookingId, BookingStatusEnum.ATTENDED);
				ratingMenuView(bookingId);
			} else {
				goBackToMenuView();
			}
		} else {
			System.out.println("Invalid Booking Id");
			attendLessonAndRatingView();
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

		while (rating < 1 && rating > 4) {
			System.out.println("Please enter a valid option: ");
			rating = sc.nextInt();
		}
		System.out.println("Please write a review:");
		Scanner input = new Scanner(System.in);
		do {
			review = input.nextLine();
		} while (review.isBlank() && review.isEmpty());

		CourseController.getInstance().assignRating(bookingId, rating, review);
		System.out.println("Thank you for your rating and review\n");
		goBackToMenuView();

	}

	public void goBackToMenuView() {
		Scanner sc = new Scanner(System.in);
		System.out.println("***********************************************************");
		System.out.println("if you want to go back to the main menu choose option (1) or (2) to exit");
		System.out.println("(1) Exit");
		System.out.println("(2) Main menu");
		System.out.println("Please enter your choice:");

		while (!sc.hasNextInt()) {
			System.out.println("Invalid choice");
			sc.next();
		}
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			break;
		case 2:
			HomeController.getInstance().getMainView();
			break;
		default:
			System.out.println("Invalid choice");
			HomeController.getInstance().getMainView();
		}

	}

}
