package studentsportbookingapp.views;

import java.util.Scanner;

import studentsportbookingapp.controllers.*;

public class HomeView {

	public void printMainView() {

		Scanner sc = new Scanner(System.in);

		// Display menu
		System.out.println("******* Welcome to University Sports Booking System *******");
		System.out.println("***********************************************************");
		System.out.println("(1) Book a group exercise lesson");
		System.out.println("(2) Change a booking");
		System.out.println("(3) Attend a lesson");
		System.out.println("(4) Monthly lesson report");
		System.out.println("(5) Monthly champion exercise report");
		System.out.println("(6) Exit");
		System.out.println("Please enter your choice:");
		try {
			int choice = 0;
			while (!sc.hasNextInt()) {
				System.out.println("Invalid choice!, Please make sure to enter a choice from 1-6");
				sc.next();
			}
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				CourseController.getInstance().bookGroupExerciseView();
				break;
			case 2:
				CourseController.getInstance().changeBookingView();
				break;
			case 3:
				CourseController.getInstance().attendLessonAndRatingView();
				break;
			case 4:
				ReportController.getInstance().monthlyReportView();
				;
				break;
			case 5:
				ReportController.getInstance().monthlyChampionReportView();
				break;
			case 6:
				System.out.println("Good Bye!!");
				break;
			default:
				System.out.println("Invalid choice!");
				printMainView();
			}
		} catch (Exception e) {
			System.out.println("Ah!! Something went Wrong");
		} finally {
			sc.close();
		}

	}
}
