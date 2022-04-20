package studentsportbookingapp.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utility {

	public static boolean isValidDate(String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate.parse(date, formatter);
			return true;
		} catch (Exception e) {
			System.out.println("Invalid date value");
			return false;
		}
	}

	public static String generateRandomString() {
		Random r = new Random();
		String s = Long.toString(r.nextLong() & Long.MAX_VALUE, 36);
		return s.toUpperCase();
	}
}
