package com.technology.equipment.rent.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technology.equipment.rent.entity.Booking;
import com.technology.equipment.rent.service.BookingService;
import com.technology.equipment.rent.utils.MessageUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
@RestController
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@ApiOperation(value = "getBookings", notes = "Endpoint that allows obtaining a list of bookings registered to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageUtils.BOOKINGS_FOUND),
			@ApiResponse(code = 404, message = MessageUtils.BOOKINGS_NOT_FOUND)})
	@GetMapping(path = "/bookings")
	public ResponseEntity<?> getBookings() {
		return bookingService.getBookings();
	}
	
	@ApiOperation(value = "getBookingByCode", notes = "Endpoint that allows obtaining a booking by code registered to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageUtils.BOOKING_FOUND),
			@ApiResponse(code = 404, message = MessageUtils.BOOKING_NOT_FOUND)})
	@GetMapping(path = "/bookings/{code}")
	public ResponseEntity<?> getBookingByCode(@PathVariable String code) {
		return bookingService.getBookingByCode(code);
	}
	
	@ApiOperation(value = "getBookingsByDate", notes = "Endpoint that allows obtaining a booking by start date and end date registered to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageUtils.BOOKING_FOUND),
			@ApiResponse(code = 404, message = MessageUtils.BOOKING_NOT_FOUND)})
	@GetMapping(path = "/bookings/date")
	public ResponseEntity<?> getBookingsByDate(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
		return bookingService.getBookingsByDate(startDate, endDate);
	}
	
	@ApiOperation(value = "saveBooking", notes = "Endpoint that allows record a booking to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = MessageUtils.BOOKING_CREATED),
			@ApiResponse(code = 403, message = MessageUtils.BOOKING_NOT_CREATED)})
	@PostMapping(path = "/bookings")
	public ResponseEntity<?> saveBooking(@RequestBody Booking booking) {
		return bookingService.saveBooking(booking);
	}
	
	@ApiOperation(value = "deleteBookingByCode", notes = "Endpoint that allows deleting a booking by code to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageUtils.BOOKING_FOUND),
			@ApiResponse(code = 404, message = MessageUtils.BOOKING_NOT_FOUND)})
	@DeleteMapping(path = "/bookings/{code}")
	public ResponseEntity<?> deleteBookingByCode(@PathVariable String code) {
		return bookingService.deleteBookingByCode(code);
	}

}
