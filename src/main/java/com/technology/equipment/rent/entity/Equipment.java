package com.technology.equipment.rent.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EQUIPMENTS")
public class Equipment implements Serializable {

	private static final long serialVersionUID = 4052792365595921150L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CODE", nullable = false)
	private String code;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
	private List<Booking> bookings;

	public Equipment() {}

	public Equipment(Long id, String code, String description) {
		this.id = id;
		this.code = code;
		this.description = description;
		this.bookings = new ArrayList<>();
	}

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
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public void addBooking(Booking booking) {
		bookings.add(booking);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookings, code, description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		return Objects.equals(bookings, other.bookings) && Objects.equals(code, other.code)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Equipment [id=" + id + ", code=" + code + ", description=" + description + ", bookings=" + bookings
				+ "]";
	}

}
