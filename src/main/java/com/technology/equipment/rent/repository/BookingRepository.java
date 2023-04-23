package com.technology.equipment.rent.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.technology.equipment.rent.entity.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
	
	public Booking findByCode(String code);

	public List<Booking> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

}
