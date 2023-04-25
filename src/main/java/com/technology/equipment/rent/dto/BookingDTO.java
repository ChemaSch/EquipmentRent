package com.technology.equipment.rent.dto;

import java.time.LocalDate;

import com.technology.equipment.rent.entity.Equipment;
import com.technology.equipment.rent.entity.User;

public class BookingDTO {
	
	private Long id;
	private String code;
	private LocalDate startDate;
	private LocalDate endDate;
	private User user;
	private Equipment equipment;
	
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
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Equipment getEquipment() {
		return equipment;
	}
	
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

}
