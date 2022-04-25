/**
 * 
 */
package studentsportbookingapp.models;

import studentsportbookingapp.utilities.*;

/**
 * @author Rakeshsharma
 *
 */
public class Lesson {

	private ExerciseNamesEnum lessonName;
	private double lessonPrice;
	public SessionsEnum sessionName;
	
	public Lesson() {
		
	}
	
	public Lesson(ExerciseNamesEnum lessonName, double lessonPrice) 
	{
		this.lessonName = lessonName;
		this.lessonPrice = lessonPrice;
	}
	
	public void setLessonName(ExerciseNamesEnum lessonName) 
	{
		this.lessonName = lessonName;
	}
	public ExerciseNamesEnum getLessonName() 
	{
		return lessonName;
	}
	public void setLessonPrice(double lessonPrice) 
	{
		this.lessonPrice = lessonPrice;
	}
	public double getLessonPrice() 
	{
		return lessonPrice;
	}
	public void setSessionName(SessionsEnum sessionName) 
	{
		this.sessionName = sessionName;
	}
	public SessionsEnum getSessionName() 
	{
		return sessionName;
	}
}
