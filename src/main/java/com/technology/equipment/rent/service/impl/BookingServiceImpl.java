package com.technology.equipment.rent.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technology.equipment.rent.entity.Booking;
import com.technology.equipment.rent.repository.BookingRepository;
import com.technology.equipment.rent.service.BookingService;
import com.technology.equipment.rent.utils.DateUtils;
import com.technology.equipment.rent.utils.MessageUtils;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> getBookings() {
		List<Booking> bookingsDB = (List<Booking>) bookingRepository.findAll();

		if (bookingsDB == null || bookingsDB.isEmpty()) {
			return new ResponseEntity<>(MessageUtils.BOOKINGS_NOT_FOUND, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(bookingsDB, HttpStatus.OK);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> getBookingByCode(String code) {
		Booking bookingDB = bookingRepository.findByCode(code);

		if (bookingDB == null) {
			return new ResponseEntity<>(MessageUtils.BOOKING_NOT_FOUND, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(bookingDB, HttpStatus.OK);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> getBookingsByDate(LocalDate startDate, LocalDate endDate) {
		List<Booking> bookingsDB = bookingRepository.findByStartDateBetween(startDate, endDate);

		if (bookingsDB == null || bookingsDB.isEmpty()) {
			return new ResponseEntity<>(MessageUtils.BOOKINGS_NOT_FOUND, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(bookingsDB, HttpStatus.OK);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<?> saveBooking(Booking booking) {
		List<Booking> bookingsDB = bookingRepository.findByStartDateBetween(booking.getStartDate(),
				booking.getEndDate());

		if (bookingsDB == null || bookingsDB.isEmpty()) {
			return new ResponseEntity<>(bookingRepository.save(booking), HttpStatus.CREATED);
		}

		// Delete equipment bookings list that do not correspond to the request made.
		filteredBookingByEquipment(booking, bookingsDB);

		// Check availability of the dates to make the reservation.
		if (checkAvailability(booking, bookingsDB)) {
			return new ResponseEntity<>(bookingRepository.save(booking), HttpStatus.CREATED);
		} else {
			// TO DO: Expose information of the bookings in conflict, the days and the
			// proposal of a new alternative date. Return BookingException.
			return new ResponseEntity<>(MessageUtils.BOOKING_NOT_CREATED, HttpStatus.FORBIDDEN);
		}

	}

	@Override
	@Transactional
	public ResponseEntity<?> deleteBookingByCode(String codigo) {
		Booking bookingDB = bookingRepository.findByCode(codigo);

		if (bookingDB == null) {
			return new ResponseEntity<>(MessageUtils.BOOKING_NOT_FOUND, HttpStatus.NOT_FOUND);
		} else {
			bookingRepository.delete(bookingDB);
			return new ResponseEntity<>(MessageUtils.BOOKING_DELETED, HttpStatus.OK);
		}
	}

	private void filteredBookingByEquipment(Booking booking, List<Booking> bookingsDB) {
		for (Booking bookingDB : bookingsDB) {
			if (!bookingDB.getEquipment().getCode().equals(booking.getEquipment().getCode())) {
				bookingsDB.remove(bookingDB);
			}
		}
	}

	private boolean checkAvailability(Booking booking, List<Booking> bookingsDB) {
		boolean availability = true;
		for (Booking bookingDB : bookingsDB) {
			if (DateUtils.isDateBetweenOrEqual(booking.getStartDate(), booking.getEndDate(), bookingDB.getStartDate(),
					bookingDB.getEndDate())) {
				availability = false;
			}
		}
		return availability;
	}

}
