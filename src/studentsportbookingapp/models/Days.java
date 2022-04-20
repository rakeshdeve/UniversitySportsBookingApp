package studentsportbookingapp.models;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import studentsportbookingapp.utilities.*;

public class Days {

	 private LocalDate date;
	 private ExcerciseDaysEnum dayName;
	 public List<Lesson> lessonList;
	 
	 public Days() {
		 
	 }
	 
	 public Days(LocalDate date, ExcerciseDaysEnum dayName) {
			this.date = date;
			this.dayName = dayName;
		}
	 
	 public String getDate() {
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        return date.format(formatter);
	 }
	 public ExcerciseDaysEnum getDay() {
		 return dayName;
	 }	
	 
	 public LocalDate getLocalDate() {
	        return date;
	 }
}
