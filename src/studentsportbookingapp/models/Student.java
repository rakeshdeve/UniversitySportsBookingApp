/**
 * 
 */
package studentsportbookingapp.models;

import java.util.List;

/**
 * @author rakeshsharma
 *
 */
public class Student {

	private int studentId;

	private String firstName;

	private String lastName;
	
	private String fullName;
	
	public Student() 
	{
		
	}

	public Student(int studentId, String firstName, String lastName) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = firstName +" "+ lastName;
	}

	public void setStudentFullName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = firstName +" "+ lastName;
	}
	public String getStudentName() {
		return fullName;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getStudentId() {
		return studentId;
	}
	

}
