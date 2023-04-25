package com.technology.equipment.rent.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.technology.equipment.rent.dto.EquipmentDTO;
import com.technology.equipment.rent.entity.Equipment;

@Mapper(uses = { BookingMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EquipmentMapper {
	
	EquipmentMapper MAPPER = Mappers.getMapper(EquipmentMapper.class);
	
	Equipment toEquipmentEntity(EquipmentDTO equipmentDTO);
	
	EquipmentDTO toEquipmentDTO(Equipment equipment);
	
}
