package com.technology.equipment.rent.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.technology.equipment.rent.entity.Equipment;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long> {

}
