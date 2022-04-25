package studentsportbookingapp.models;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import studentsportbookingapp.utilities.*;

public class Days {

	 private LocalDate date;
	 private ExerciseDaysEnum dayName;
	 public List<Lesson> lessonList;
	 
	 public Days() {
		 
	 }
	 
	 public Days(LocalDate date, ExerciseDaysEnum dayName) {
			this.date = date;
			this.dayName = dayName;
		}
	 
	 public String getStringDate() {
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        return date.format(formatter);
	 }
	 public ExerciseDaysEnum getDay() {
		 return dayName;
	 }	
	 
	 public LocalDate getDate() {
	        return date;
	 }
}
