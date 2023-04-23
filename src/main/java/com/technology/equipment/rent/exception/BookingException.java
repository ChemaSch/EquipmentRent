package com.technology.equipment.rent.exception;

import java.time.LocalDate;
import java.util.List;

import com.technology.equipment.rent.entity.Booking;

public class BookingException {

	private String bookingText;
	private List<Booking> bookings;
	private String daysText;
	private List<LocalDate> days;
	private String alternativeStartDateText;
	private LocalDate newStartDate;

	public BookingException() {}

	public BookingException(String bookingText, List<Booking> bookings, String daysText, List<LocalDate> days,
			String alternativeStartDateText, LocalDate newStartDate) {
		this.bookingText = bookingText;
		this.bookings = bookings;
		this.daysText = daysText;
		this.days = days;
		this.alternativeStartDateText = alternativeStartDateText;
		this.newStartDate = newStartDate;
	}

	public String getBookingText() {
		return bookingText;
	}

	public void setBookingText(String bookingText) {
		this.bookingText = bookingText;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public String getDaysText() {
		return daysText;
	}

	public void setDaysText(String daysText) {
		this.daysText = daysText;
	}

	public List<LocalDate> getDays() {
		return days;
	}

	public void setDays(List<LocalDate> days) {
		this.days = days;
	}

	public String getAlternativeStartDateText() {
		return alternativeStartDateText;
	}

	public void setAlternativeStartDateText(String alternativeStartDateText) {
		this.alternativeStartDateText = alternativeStartDateText;
	}

	public LocalDate getNewStartDate() {
		return newStartDate;
	}

	public void setNewStartDate(LocalDate newStartDate) {
		this.newStartDate = newStartDate;
	}

}
