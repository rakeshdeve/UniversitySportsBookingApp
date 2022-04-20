package studentsportbookingapp.views;

import java.util.Scanner;

import studentsportbookingapp.controllers.*;

public class HomeView {

	public void printMainView() {

		Scanner sc = new Scanner(System.in);

		// Display menu
		System.out.println("\nSPORTS BOOKING SYSTEM\n=====================");
		System.out.println("1. Book a group exercise lesson");
		System.out.println("2. Change a booking");
		System.out.println("3. Attend a lesson");
		System.out.println("4. Monthly lesson report");
		System.out.println("5. Monthly champion exercise report");

		System.out.println("Please enter your choice:");
		// while (!sc.hasNextInt()) sc.next();
		//It allows only integer, if any other data type then the program will exit
		if (!sc.hasNextInt()) {
			System.out.println("Invalid choice");
			return;
		}
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			CourseController.getInstance().bookGroupExcerciseView();
			break;
		case 2:
			CourseController.getInstance().changeBookingView();
			break;
		case 3:
			CourseController.getInstance().attendLessonAndRatingView();
			break;
		case 4:
			ReportController.getInstance().monthlyReportView();;
			break;
		case 5:
			ReportController.getInstance().monthlyChampionReportView();
			break;
		default:
			System.out.println("Invalid choice");
			return;
		}
	}
}
