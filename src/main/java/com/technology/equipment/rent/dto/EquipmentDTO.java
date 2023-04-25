package com.technology.equipment.rent.dto;

import java.util.ArrayList;
import java.util.List;

import com.technology.equipment.rent.entity.Booking;

public class EquipmentDTO {
	
	private Long id;
	private String code;
	private String description;
	private List<Booking> bookings;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Booking> getBookings() {
		if(bookings == null) {
			bookings = new ArrayList<>();
		}
		return bookings;
	}
	
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	
}
