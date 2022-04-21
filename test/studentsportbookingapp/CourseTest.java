package studentsportbookingapp;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import studentsportbookingapp.models.Student;
import studentsportbookingapp.utilities.ExcerciseNamesEnum;
import studentsportbookingapp.controllers.*;
import studentsportbookingapp.views.*;
import studentsportbookingapp.models.*;

public class CourseTest {

	public CourseView courseViewObj;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		CourseController.getInstance();
		courseViewObj = CourseController.getInstance().getCourseView();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPreRegisteredStudentByIdTrue() {
		// Test pass
		int studentId = 1; // Hard coded student
		Student studentDetails = new Student();
		// CourseController.getInstance().get
		studentDetails = CourseController.getInstance().getCourseView().getStudentDetailsbyId(studentId);
		//
		assertNotNull(studentDetails);
		assertEquals(studentId, studentDetails.getStudentId());
	}

	@Test
	public void testGetPreRegisteredStudentByIdFalse() {
		// Test Fail
		int studentId = 30;
		Student studentDetails = new Student();
		studentDetails = courseViewObj.getStudentDetailsbyId(studentId);
		assertNotNull(studentDetails);
		assertEquals(studentId, studentDetails.getStudentId());
	}

	@Test
	public void testGetStudentByBookingIdTrue() {
		// Test Pass
		String bookingId = "87KL898660DD";
		int studentId = 3;
		int result = courseViewObj.getStudentIdByBookingId(bookingId);
		assertEquals(studentId, result);
	}

	@Test
	public void testGetStudentByBookingIdFalse() {
		// Test Fails
		String bookingId = "87KL898660DD";
		int studentId = 4;
		int result = courseViewObj.getStudentIdByBookingId(bookingId);
		assertEquals(studentId, result);
	}

	@Test
	public void testCheckIsLessonAlreadyBookedTrue() {
		// Test Pass
		int studentId = 3;
		LocalDate lessonDate = LocalDate.of(2022, Month.APRIL, 3);
		ExcerciseNamesEnum lessonName =  ExcerciseNamesEnum.BOX_FIT;
		boolean result = courseViewObj.checkIsLessonAlreadyBooked(studentId,lessonName,lessonDate);
		assertEquals(true, result);
	}
	@Test
	public void testCheckIsLessonAlreadyBookedFalse() {
		// Test Fail
		int studentId = 3;
		LocalDate lessonDate = LocalDate.of(2022, Month.APRIL, 4);
		ExcerciseNamesEnum lessonName =  ExcerciseNamesEnum.BOX_FIT;
		boolean result = courseViewObj.checkIsLessonAlreadyBooked(studentId,lessonName,lessonDate);
		assertEquals(true, result);
	}

	@Test
	public void testNullorNotGetPreregisteredStudents() {
		// Test Pass
		var studentsList = CourseController.getInstance().getPreregisteredStudents();
		assertNotNull(studentsList);
	}
	
	@Test
	public void testBookedStudentCountByLesson() {
		// Test Pass
		LocalDate lessonDate = LocalDate.of(2022, Month.APRIL, 3);
		ExcerciseNamesEnum lessonName =  ExcerciseNamesEnum.BOX_FIT;
		long result = CourseController.getInstance().getStudentCount(lessonName, lessonDate);
		assertEquals(true, result > 0);
	}
	
	@Test
	public void testNullorNotBookingList() {
		// Test Pass
		var bookingList = CourseController.getBookingList();
		assertNotNull(bookingList);
	}
	
	@Test
	public void testNullorNotGeneratedTimeTable() {
		// Test Pass
		var timeTable = TimeTable.getInstance();
		assertNotNull(timeTable);
	}

}
