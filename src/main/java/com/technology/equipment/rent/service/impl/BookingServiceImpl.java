package com.technology.equipment.rent.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technology.equipment.rent.entity.Booking;
import com.technology.equipment.rent.exception.BookingException;
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
		} else {
			// Eliminamos las reservas de equipos que no se corresponden con la solicitud realizada.
			getFilteredBookingByEquipment(booking, bookingsDB);

			// Exponer la información de las reservas en conflicto, los días y la
			// proposición de nueva fecha alternativa propuesta.
			return new ResponseEntity<>(
					new BookingException(MessageUtils.BOOKING_TEXT, bookingsDB, MessageUtils.DAYS_TEXT,
							DateUtils.getDatesByRange(getMaximumDateBookingOfEquipment(bookingsDB).getStartDate(),
									getMaximumDateBookingOfEquipment(bookingsDB).getEndDate()),
							MessageUtils.ALTERNATIVE_START_DATE_TEXT,
							DateUtils.getAlternativeStartDate(getMaximumDateBookingOfEquipment(bookingsDB).getEndDate())),
					HttpStatus.FORBIDDEN);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<?> deleteBookingByCode(String codigo) {
		Booking reservaDB = bookingRepository.findByCode(codigo);

		if (reservaDB == null) {
			return new ResponseEntity<>(MessageUtils.BOOKING_NOT_FOUND, HttpStatus.NOT_FOUND);
		} else {
			bookingRepository.delete(reservaDB);
			return new ResponseEntity<>(MessageUtils.BOOKING_DELETED, HttpStatus.OK);
		}
	}

	private void getFilteredBookingByEquipment(Booking booking, List<Booking> bookingsDB) {
		for (Booking bookingDB : bookingsDB) {
			if (!bookingDB.getEquipment().getCode().equals(booking.getEquipment().getCode())) {
				bookingsDB.remove(bookingDB);
			}
		}
	}

	private Booking getMaximumDateBookingOfEquipment(List<Booking> bookingsDB) {
		Booking booking = bookingsDB.get(0);
		for (Booking bookingDB : bookingsDB) {
			if (bookingDB.getEndDate().isAfter(booking.getEndDate())) {
				booking = bookingDB;
			}
		}
		return booking;
	}

}
