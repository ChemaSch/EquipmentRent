package com.technology.equipment.rent.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "BOOKINGS")
public class Booking implements Serializable {

	private static final long serialVersionUID = 2177430542091601296L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CODE", unique = true, nullable = false, length = 12)
	private int code;
	
	@Column(name = "START_DATE", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate startDate;
	
	@Column(name = "END_DATE", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate endDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Equipment equipment;
	
	public Booking() {}
	
	public Booking(Long id) {
		this.id = id;
	}

	public Booking(Long id, int code, LocalDate startDate, LocalDate endDate, User user, Equipment equipment) {
		this.id = id;
		this.code = code;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.equipment = equipment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
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

	@Override
	public int hashCode() {
		return Objects.hash(code, endDate, equipment, id, startDate, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		return code == other.code && Objects.equals(endDate, other.endDate)
				&& Objects.equals(equipment, other.equipment) && Objects.equals(id, other.id)
				&& Objects.equals(startDate, other.startDate) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", code=" + code + ", startDate=" + startDate + ", endDate=" + endDate + ", user="
				+ user + ", equipment=" + equipment + "]";
	}

}
