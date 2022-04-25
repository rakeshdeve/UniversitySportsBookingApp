package studentsportbookingapp.views;

import studentsportbookingapp.controllers.*;
import studentsportbookingapp.models.*;
import studentsportbookingapp.utilities.*;

import java.time.*;
import java.util.*;
import java.util.Scanner;

public class ReportView {

	public void monthlyReportView() {

		int month = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("******* Monthly Report ******************");
		System.out.println("***********************************************************");

		System.out.println("Note: Reports are available only in April and May month, please choose between the months 4-5");
		System.out.println("Enter the month for report you need (in format: MM):");
		month = sc.nextInt();

		while (month < 0 || month > 12) {
			System.out.println("Please enter a valid month: ");
			month = sc.nextInt();
		}

		CommandLineTable st = new CommandLineTable();

		st.setShowVerticalLines(true);// if false (default) then no vertical lines are shown
		st.setHeaders("Exercise Name", "Time", "Exercise Date", "No. of Students", "Avg Rating");

		var timeTableList = TimeTable.getTimeTableByMonthName(month);
		var bookingList = CourseController.getBookingList();
		for (var item : timeTableList) {
			var lessonList = item.lessonList;
			var morningSession = lessonList.stream().filter(s -> s.getSessionName() == SessionsEnum.MORNING).findFirst()
					.orElse(null);
			var afternoonSession = lessonList.stream().filter(s -> s.getSessionName() == SessionsEnum.AFTERNOON)
					.findFirst().orElse(null);
			var eveningSession = lessonList.stream().filter(s -> s.getSessionName() == SessionsEnum.EVENING).findFirst()
					.orElse(null);
			double avgRatingMorning = bookingList.stream()
					.filter(s -> s.getLessonDate().equals(item.getDate())
							&& s.getLessonName() == morningSession.getLessonName())
					.mapToInt(s -> s.getRating()).average().orElse(0);
			double avgRatingafternoon = bookingList.stream()
					.filter(s -> s.getLessonDate().equals(item.getDate())
							&& s.getLessonName() == afternoonSession.getLessonName())
					.mapToInt(s -> s.getRating()).average().orElse(0);
			double avgRatingevening = bookingList.stream()
					.filter(s -> s.getLessonDate().equals(item.getDate())
							&& s.getLessonName() == eveningSession.getLessonName())
					.mapToInt(s -> s.getRating()).average().orElse(0);
			st.addRow(morningSession.getLessonName().toString(), morningSession.getSessionName().toString(),
					item.getStringDate(), String.valueOf(CourseController.getInstance()
							.getStudentCount(morningSession.getLessonName(), item.getDate())),
					String.valueOf(avgRatingMorning));
			st.addRow(afternoonSession.getLessonName().toString(), afternoonSession.getSessionName().toString(),
					item.getStringDate(), String.valueOf(CourseController.getInstance()
							.getStudentCount(afternoonSession.getLessonName(), item.getDate())),
					String.valueOf(avgRatingafternoon));
			st.addRow(eveningSession.getLessonName().toString(), eveningSession.getSessionName().toString(),
					item.getStringDate(), String.valueOf(CourseController.getInstance()
							.getStudentCount(eveningSession.getLessonName(), item.getDate())),
					String.valueOf(avgRatingevening));
		}
		st.print();
		HomeController.getInstance().getMainView();
	}

	public void monthlyChampionReportView() {

		int month = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("******* Monthly Champion Exercise Report ******************");
		System.out.println("***********************************************************");
		System.out.println("Note: Reports are available only in April and May month, please choose between the months 4-5");
		System.out.println("Enter the month for report you need (in format: MM):");

		month = sc.nextInt();

		while (month < 0 || month > 12) {
			System.out.println("Please enter a valid month: ");
			month = sc.nextInt();
		}
		CommandLineTable st = new CommandLineTable();
		st.setShowVerticalLines(true);// if false (default) then no vertical lines are shown
		st.setHeaders("Exercise Name", "Income");
		var bookingList = CourseController.getBookingList();
		if (bookingList != null && !bookingList.isEmpty()) {
			int filterMonth = month;
			String highestIncomeLesson = "";
			double highestIncome = 0;
			for (ExerciseNamesEnum name : ExerciseNamesEnum.values()) {
				double totalIncome = bookingList.stream()
						.filter(s -> s.getLessonDate().getMonthValue() == filterMonth && s.getLessonName().equals(name)
								&& s.getBookingStatus() != BookingStatusEnum.CANCELLED)
						.mapToDouble(s -> s.getLessonPrice()).sum();
				st.addRow(name.getlessonName(), "$" + String.valueOf(totalIncome));
				if (totalIncome > highestIncome) {
					highestIncome = totalIncome;
					highestIncomeLesson = name.getlessonName();
				}
			}
			if (highestIncome > 0) {
				System.out.println("Highest income: " + highestIncomeLesson + " $" + highestIncome);
			}
		}
		st.print();
		HomeController.getInstance().getMainView();

	}

}
