package com.technology.equipment.rent.service;

import org.springframework.http.ResponseEntity;

import com.technology.equipment.rent.entity.Equipment;

public interface EquipmentService {
	
	public ResponseEntity<?> getEquipments();
	
	public ResponseEntity<?> getEquipment(Long id);
	
	public ResponseEntity<?> saveEquipment(Equipment equipment);
	
	public ResponseEntity<?> updateEquipment(Equipment equipment, Long id);
	
	public ResponseEntity<?> deleteEquipment(Long id);

}
