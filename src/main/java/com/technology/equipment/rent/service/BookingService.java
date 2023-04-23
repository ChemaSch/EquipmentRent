package com.technology.equipment.rent.service;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import com.technology.equipment.rent.entity.Booking;

public interface BookingService {
	
	public ResponseEntity<?> getBookings();
	
	public ResponseEntity<?> getBookingByCode(String code);
	
	public ResponseEntity<?> getBookingsByDate(LocalDate startDate, LocalDate endDate);
	
	public ResponseEntity<?> saveBooking(Booking booking);
	
	public ResponseEntity<?> deleteBookingByCode(String code);

}
