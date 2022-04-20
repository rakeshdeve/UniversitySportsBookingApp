/**
 * 
 */
package studentsportbookingapp;
import studentsportbookingapp.controllers.*;
import studentsportbookingapp.views.*;


public class StudentSportBookingApp {

	private static HomeController homeController;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		homeController = HomeController.getInstance();
		homeController.getMainView();
		
	}
}
