package com.technology.equipment.rent.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.technology.equipment.rent.dto.BookingDTO;
import com.technology.equipment.rent.entity.Booking;

@Mapper(uses = { UserMapper.class, EquipmentMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

	BookingMapper MAPPER = Mappers.getMapper(BookingMapper.class);

	Booking toBookingEntity(BookingDTO bookingDTO);

	BookingDTO toBookingDTO(Booking booking);

	List<BookingDTO> toBookingDTOList(List<Booking> bookings);

	List<Booking> toBookingList(List<BookingDTO> bookingsDTO);

}
