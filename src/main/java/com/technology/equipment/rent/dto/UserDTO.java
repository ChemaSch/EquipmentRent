package com.technology.equipment.rent.dto;

import java.util.ArrayList;
import java.util.List;

import com.technology.equipment.rent.entity.Booking;

public class UserDTO {

	private Long id;
	private String username;
	private String email;
	private List<Booking> bookings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Booking> getBookings() {
		if (bookings == null) {
			bookings = new ArrayList<>();
		}
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

}
