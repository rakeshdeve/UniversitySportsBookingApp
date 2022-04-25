/**
 * 
 */
package studentsportbookingapp.models;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import studentsportbookingapp.utilities.*;

/**
 * @author Rakeshsharma
 *
 */
public class Booking extends Student {
	private String bookingId;
	private LocalDate bookingDate;
	private LocalDate lessonDate;
	private BookingStatusEnum bookingStatus;
	private ExerciseNamesEnum lessonName;
	private int rating;
	private String review;
	private double lessonPrice;

	public Booking() {

	}

	public Booking(int studentId, String bookingId, LocalDate lessonDate,
			BookingStatusEnum bookingStatus, ExerciseNamesEnum lessonName, int rating, String review,
			double lessonPrice) {
		this.setStudentId(studentId);
		this.setBookingId(bookingId);
		this.setLessonDate(lessonDate);
		this.setBookingStatus(bookingStatus);
		this.setLessonName(lessonName);
		this.setRating(rating);
		this.setReview(review);
		this.setLessonPrice(lessonPrice);
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDate getLessonDate() {
		return lessonDate;
	}

	public void setLessonDate(LocalDate lessonDate) {
		this.lessonDate = lessonDate;
	}

	public BookingStatusEnum getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatusEnum bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public ExerciseNamesEnum getLessonName() {
		return lessonName;
	}

	public void setLessonName(ExerciseNamesEnum lessonName) {
		this.lessonName = lessonName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public double getLessonPrice() {
		return lessonPrice;
	}

	public void setLessonPrice(double lessonPrice) {
		this.lessonPrice = lessonPrice;
	}
}
