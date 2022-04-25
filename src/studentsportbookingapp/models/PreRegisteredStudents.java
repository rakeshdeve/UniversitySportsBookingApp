/**
 * 
 */
package studentsportbookingapp.models;
import java.util.*;

/**
 * @author Rakeshsharma
 *
 */
public class PreRegisteredStudents {

	public static ArrayList<Student>getPreRegisteredStudents()
	{
		ArrayList<Student> studentList = new ArrayList<Student>();	
		studentList.add(new Student(1,"Joe","Tribianny"));
		studentList.add(new Student(2,"Chandler","Bing"));
		studentList.add(new Student(3,"Monica","Geller"));
		studentList.add(new Student(4,"Ross","Geller"));
		studentList.add(new Student(5,"Phoebe","Buffay"));
		studentList.add(new Student(6,"Michael","Scott"));
		studentList.add(new Student(7,"Jim","Halpert"));
		studentList.add(new Student(8,"Pam","Beasley"));
		studentList.add(new Student(9,"Dwight","Schrute"));
		studentList.add(new Student(10,"Alan","Walker"));
		studentList.add(new Student(11,"Hary","Styles"));
		studentList.add(new Student(12,"Paul","Walker"));
		studentList.add(new Student(13,"Ellie","Goulding"));
		studentList.add(new Student(14,"Simeon","Jebaz"));
		studentList.add(new Student(15,"James","Williams"));
		studentList.add(new Student(16,"Tom","Hardy"));
		studentList.add(new Student(17,"Tom","Holland"));
		studentList.add(new Student(18,"Christian","Bale"));
		studentList.add(new Student(19,"Hugh","Jackman"));
		studentList.add(new Student(20,"Anne","Marrie"));
		return studentList;
	}
	
}
