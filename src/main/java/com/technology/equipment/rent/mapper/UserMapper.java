package com.technology.equipment.rent.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.technology.equipment.rent.dto.UserDTO;
import com.technology.equipment.rent.entity.User;

@Mapper(uses = { BookingMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

	User toUserEntity(UserDTO userDTO);

	UserDTO toUserDTO(User user);

}
