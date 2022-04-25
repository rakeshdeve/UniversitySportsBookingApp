package studentsportbookingapp.models;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import studentsportbookingapp.utilities.BookingStatusEnum;
import studentsportbookingapp.utilities.ExerciseNamesEnum;
import studentsportbookingapp.utilities.LessonPricing;

public class BookingData {
	
	public static ArrayList<Booking> bookingHistoryData() {
	ArrayList<Booking> bookingList = new ArrayList<Booking>();	
	bookingList.add(new Booking(1,"AJKL898990CX",LocalDate.of(2022, Month.APRIL, 3),BookingStatusEnum.BOOKED,ExerciseNamesEnum.BOXING,4,"Good Experience",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.BOXING)));
	bookingList.add(new Booking(2,"BKKL898550TT",LocalDate.of(2022, Month.APRIL, 3),BookingStatusEnum.BOOKED,ExerciseNamesEnum.BODY_BLITZ,5,"Good Experience",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.BODY_BLITZ)));
	bookingList.add(new Booking(3,"JEKL898660DD",LocalDate.of(2022, Month.APRIL, 2),BookingStatusEnum.BOOKED,ExerciseNamesEnum.YOGA,4,"Excellent",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.YOGA)));
	bookingList.add(new Booking(3,"PSKL898660DD",LocalDate.of(2022, Month.APRIL, 2),BookingStatusEnum.BOOKED,ExerciseNamesEnum.AQUACISE,5,"Excellent",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.AQUACISE)));
	bookingList.add(new Booking(3,"87KL898660DD",LocalDate.of(2022, Month.APRIL, 3),BookingStatusEnum.BOOKED,ExerciseNamesEnum.BOX_FIT,5,"Excellent",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.BOX_FIT)));
	bookingList.add(new Booking(4,"TTKL438660GF",LocalDate.of(2022, Month.APRIL, 2),BookingStatusEnum.BOOKED,ExerciseNamesEnum.ZUMBA,2,"Ok",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.ZUMBA)));
	bookingList.add(new Booking(5,"ABJL438660GF",LocalDate.of(2022, Month.APRIL, 16),BookingStatusEnum.BOOKED,ExerciseNamesEnum.ZUMBA,5,"Excellent",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.ZUMBA)));
	bookingList.add(new Booking(6,"KLSL438660GF",LocalDate.of(2022, Month.APRIL, 10),BookingStatusEnum.BOOKED,ExerciseNamesEnum.BODY_BLITZ,5,"Excellent",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.BODY_BLITZ)));
	bookingList.add(new Booking(7,"ETSL438660GF",LocalDate.of(2022, Month.APRIL, 9),BookingStatusEnum.BOOKED,ExerciseNamesEnum.ZUMBA,1,"Bad Experience",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.ZUMBA)));
	bookingList.add(new Booking(19,"L088438660GF",LocalDate.of(2022, Month.APRIL, 9),BookingStatusEnum.BOOKED,ExerciseNamesEnum.YOGA,5,"Good Experience",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.YOGA)));
	bookingList.add(new Booking(8,"PUYL438660GF",LocalDate.of(2022, Month.APRIL, 9),BookingStatusEnum.BOOKED,ExerciseNamesEnum.AQUACISE,4,"Good Experience",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.AQUACISE)));
	bookingList.add(new Booking(9,"GUAL438660GF",LocalDate.of(2022, Month.APRIL, 10),BookingStatusEnum.BOOKED,ExerciseNamesEnum.BOX_FIT,3,"Average",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.BOX_FIT)));
	bookingList.add(new Booking(10,"MMSL438660GF",LocalDate.of(2022, Month.APRIL, 17),BookingStatusEnum.BOOKED,ExerciseNamesEnum.BOX_FIT,2,"Good Experience",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.BOX_FIT)));
	bookingList.add(new Booking(11,"TAEL438660GF",LocalDate.of(2022, Month.APRIL, 23),BookingStatusEnum.BOOKED,ExerciseNamesEnum.YOGA,5,"Good Experience",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.YOGA)));
	bookingList.add(new Booking(12,"POLL438660GF",LocalDate.of(2022, Month.APRIL, 24),BookingStatusEnum.BOOKED,ExerciseNamesEnum.BODY_BLITZ,4,"Good Experience",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.BODY_BLITZ)));
	
	bookingList.add(new Booking(13,"KAPL438660GF",LocalDate.of(2022, Month.MAY, 7),BookingStatusEnum.BOOKED,ExerciseNamesEnum.YOGA,3,"Average",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.YOGA)));
	bookingList.add(new Booking(14,"REAL438660GF",LocalDate.of(2022, Month.MAY, 8),BookingStatusEnum.BOOKED,ExerciseNamesEnum.BODY_BLITZ,5,"Good Experience",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.BODY_BLITZ)));
	bookingList.add(new Booking(15,"YAES438660GF",LocalDate.of(2022, Month.MAY, 14),BookingStatusEnum.BOOKED,ExerciseNamesEnum.ZUMBA,5,"Good Experience",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.ZUMBA)));
	bookingList.add(new Booking(16,"ZXSS438660GF",LocalDate.of(2022, Month.MAY, 1),BookingStatusEnum.BOOKED,ExerciseNamesEnum.BOXING,2,"Ok",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.BOXING)));
	bookingList.add(new Booking(17,"(9OTS438660GF",LocalDate.of(2022, Month.MAY, 15),BookingStatusEnum.BOOKED,ExerciseNamesEnum.BOXING,1,"Bad Experience",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.BOXING)));
	bookingList.add(new Booking(18,"7ZTS438660GF",LocalDate.of(2022, Month.MAY, 22),BookingStatusEnum.BOOKED,ExerciseNamesEnum.BOX_FIT,3,"Average",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.BOX_FIT)));
	bookingList.add(new Booking(19,"4EYD438660GF",LocalDate.of(2022, Month.MAY, 3),BookingStatusEnum.BOOKED,ExerciseNamesEnum.BOX_FIT,4,"Good Experience",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.BOX_FIT)));
	bookingList.add(new Booking(20,"4KKL438660GF",LocalDate.of(2022, Month.APRIL, 16),BookingStatusEnum.BOOKED,ExerciseNamesEnum.YOGA,4,"Good Experience",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.YOGA)));
	
	bookingList.add(new Booking(1,"9NKL438660GF",LocalDate.of(2022, Month.MAY, 28),BookingStatusEnum.BOOKED,ExerciseNamesEnum.AQUACISE,5,"Excellent",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.AQUACISE)));
	bookingList.add(new Booking(2,"3YKL438660GF",LocalDate.of(2022, Month.MAY, 29),BookingStatusEnum.BOOKED,ExerciseNamesEnum.BOXING,2,"Ok",LessonPricing.getPriceByLessonName(ExerciseNamesEnum.BOXING)));
	return bookingList;
	}
	
	
}


