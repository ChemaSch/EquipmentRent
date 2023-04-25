package com.technology.equipment.rent.tests.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.technology.equipment.rent.entity.Booking;
import com.technology.equipment.rent.entity.Equipment;
import com.technology.equipment.rent.entity.User;
import com.technology.equipment.rent.repository.BookingRepository;
import com.technology.equipment.rent.service.impl.BookingServiceImpl;
import com.technology.equipment.rent.utils.MessageUtils;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
class BookingServiceTest {

	@Mock
	private BookingRepository bookingRepository;

	@InjectMocks
	private BookingServiceImpl bookingService;

	private Booking booking, booking2, booking3;

	@BeforeEach
	void setUp() throws Exception {
		booking = new Booking(1L, "100100", LocalDate.parse("2023-04-24"), LocalDate.parse("2023-04-30"),
				new User(4L, "test", "test@test.com"), new Equipment(5L, "100105", "EQUIPO5"));
		booking2 = new Booking(2L, "100102", LocalDate.parse("2023-04-20"), LocalDate.parse("2023-04-23"), 
				new User(6L, "test3", "test3@test.com"), new Equipment(5L, "100105", "EQUIPO5"));
		booking3 = new Booking(1L, "100103", LocalDate.parse("2023-04-22"), LocalDate.parse("2023-05-01"),
				new User(7L, "test4", "test4@test.com"), new Equipment(5L, "100105", "EQUIPO5"));
	}

	@Test
	void testGetBookingsNullNotFound() {
		when(bookingRepository.findAll()).thenReturn(null);
		ResponseEntity<?> response = bookingService.getBookings();
		assertEquals(MessageUtils.BOOKINGS_NOT_FOUND, response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(bookingRepository).findAll();
	}

	@Test
	void testGetBookingsEmptyNotFound() {
		when(bookingRepository.findAll()).thenReturn(new ArrayList<>());
		ResponseEntity<?> response = bookingService.getBookings();
		assertEquals(MessageUtils.BOOKINGS_NOT_FOUND, response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(bookingRepository).findAll();
	}

	@Test
	void testGetBookingsFound() {
		when(bookingRepository.findAll()).thenReturn(Collections.singletonList(booking));
		ResponseEntity<?> response = bookingService.getBookings();
		assertEquals(Collections.singletonList(booking), response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(bookingRepository).findAll();
	}

	@Test
	void testGetBookingByCodeFound() {
		when(bookingRepository.findByCode("100100")).thenReturn(booking);
		ResponseEntity<?> response = bookingService.getBookingByCode("100100");
		assertEquals(booking, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(bookingRepository).findByCode(any());
	}

	@Test
	void testGetBookingByCodeNotFound() {
		when(bookingRepository.findByCode("100101")).thenReturn(null);
		ResponseEntity<?> response = bookingService.getBookingByCode("100101");
		assertEquals(MessageUtils.BOOKING_NOT_FOUND, response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(bookingRepository).findByCode(any());
	}

	@Test
	void testGetBookingsByDateNullNotFound() {
		when(bookingRepository.findByStartDateBetween(LocalDate.parse("2023-04-10"), LocalDate.parse("2023-04-14"))).thenReturn(null);
		ResponseEntity<?> response = bookingService.getBookingsByDate(LocalDate.parse("2023-04-10"), LocalDate.parse("2023-04-14"));
		assertEquals(MessageUtils.BOOKINGS_NOT_FOUND, response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(bookingRepository).findByStartDateBetween(any(), any());
	}

	@Test
	void testGetBookingsByDateEmptyNotFound() {
		when(bookingRepository.findByStartDateBetween(LocalDate.parse("2023-04-10"), LocalDate.parse("2023-04-14"))).thenReturn(new ArrayList<>());
		ResponseEntity<?> response = bookingService.getBookingsByDate(LocalDate.parse("2023-04-10"), LocalDate.parse("2023-04-14"));
		assertEquals(MessageUtils.BOOKINGS_NOT_FOUND, response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(bookingRepository).findByStartDateBetween(any(), any());
	}
	
	@Test
	void testGetBookingsByDateFound() {
		when(bookingRepository.findByStartDateBetween(LocalDate.parse("2023-04-24"), LocalDate.parse("2023-04-30"))).thenReturn(Collections.singletonList(booking));
		ResponseEntity<?> response = bookingService.getBookingsByDate(LocalDate.parse("2023-04-24"), LocalDate.parse("2023-04-30"));
		assertEquals(Collections.singletonList(booking), response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(bookingRepository).findByStartDateBetween(any(), any());
	}
	
	@Test
	void testSaveBookingNullBookingList() {
		when(bookingRepository.findByStartDateBetween(LocalDate.parse("2023-04-24"), LocalDate.parse("2023-04-30"))).thenReturn(null);
		when(bookingRepository.save(booking)).thenReturn(booking);
		ResponseEntity<?> response = bookingService.saveBooking(booking);
		assertEquals(booking, response.getBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(bookingRepository).findByStartDateBetween(any(), any());
		verify(bookingRepository).save(any());
	}
	
	@Test
	void testSaveBookingEmptyBookingList() {
		when(bookingRepository.findByStartDateBetween(LocalDate.parse("2023-04-24"), LocalDate.parse("2023-04-30"))).thenReturn(new ArrayList<>());
		when(bookingRepository.save(booking)).thenReturn(booking);
		ResponseEntity<?> response = bookingService.saveBooking(booking);
		assertEquals(booking, response.getBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(bookingRepository).findByStartDateBetween(any(), any());
		verify(bookingRepository).save(any());
	}
	
	@Test
	void testSaveBookingNotEmptyBookingList() {
		when(bookingRepository.findByStartDateBetween(LocalDate.parse("2023-04-20"), LocalDate.parse("2023-04-23"))).thenReturn(new ArrayList<>());
		when(bookingRepository.save(booking2)).thenReturn(booking2);
		ResponseEntity<?> response = bookingService.saveBooking(booking2);
		assertEquals(booking2, response.getBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(bookingRepository).findByStartDateBetween(any(), any());
		verify(bookingRepository).save(any());
	}
	
	@Test
	void testSaveBookingNotEmptyBookingListFail() {
		when(bookingRepository.findByStartDateBetween(LocalDate.parse("2023-04-22"), LocalDate.parse("2023-05-01"))).thenReturn(Collections.singletonList(booking));
		ResponseEntity<?> response = bookingService.saveBooking(booking3);
		assertEquals(MessageUtils.BOOKING_NOT_CREATED, response.getBody());
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		verify(bookingRepository).findByStartDateBetween(any(), any());
	}

}
