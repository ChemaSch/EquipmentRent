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
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = -6754692721085533825L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
    @Column(name = "USERNAME", nullable = false)
	private String username;

	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Booking> bookings;
	
	public User() {}

	public User(Long id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.bookings = new ArrayList<>();
	}

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
		return Objects.hash(bookings, email, id, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(bookings, other.bookings) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", bookings=" + bookings + "]";
	}

}
