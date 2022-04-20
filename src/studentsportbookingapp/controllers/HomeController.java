package studentsportbookingapp.controllers;

import studentsportbookingapp.views.HomeView;

public class HomeController {

	private HomeView homeView;
	private static HomeController instance = null;
	//Todo model
	public HomeController() 
	{
		this.homeView = new HomeView();
	}
	
	public static HomeController getInstance() {
		if (instance == null) {
			instance = new HomeController();
		}
		return instance;
	}
	public void getMainView() 
	{
		homeView.printMainView();
	}
	
}

/*
public class Timetable 
{
	List<day> DayList {get; set;}
}

public class Day
{
	List<Session> sessions {get; set;}
}

public class Slot 
{
	List<Student> students {get; set;}
}

public class Student
{
	int Sid;
 string name;
}


class hardcordTimetable 
{
	Timetable tb = new TimeTable();
	void addTimeTable()
	{
		
		tb.DayList = new List<Day>();
		var  day1 = new Day();
		day.Name = "Sunday";
		day.Date = "01/02/2022";
		var slots = new List<Slot>();
		slots.Add( { slotName = "Morning" ,
				Lesson = [{}]}, { "Afternoon"}, {"Evening"})
		day.Add()
	}
	
	
	void SetStudentTOTimeTable()
	{
		tb.select(day => Week2Sat)
	}
	
	void assignstudenttoTimeTable(bookstatus, bookingid)
	{
	if bookid> 0
		coursename, date
		time table ->date filter islessonavailabe
		student - create instance booking obj,id,date,status, lesson name, rating, review
		
		lesson - student count + 1
		

	}
	}
*/