package com.technology.equipment.rent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technology.equipment.rent.entity.Equipment;
import com.technology.equipment.rent.repository.EquipmentRepository;
import com.technology.equipment.rent.service.EquipmentService;
import com.technology.equipment.rent.utils.MessageUtils;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentRepository equipmentRepository;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> getEquipments() {
		List<Equipment> equipmentDB = (List<Equipment>) equipmentRepository.findAll();

		if (equipmentDB == null || equipmentDB.isEmpty()) {
			return new ResponseEntity<>(MessageUtils.EQUIPMENTS_NOT_FOUND, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(equipmentDB, HttpStatus.OK);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> getEquipment(Long id) {
		Optional<Equipment> equipmentDB = equipmentRepository.findById(id);
		if(equipmentDB.isPresent()) {
			return new ResponseEntity<>(equipmentDB.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(MessageUtils.EQUIPMENT_NOT_FOUND ,HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<?> saveEquipment(Equipment equipment) {
		return new ResponseEntity<>(equipmentRepository.save(equipment), HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public ResponseEntity<?> updateEquipment(Equipment equipo, Long id) {
		return equipmentRepository.findById(id).map((equipmentDB) -> {
			updateEquipmentProperties(equipo, equipmentDB);
			return new ResponseEntity<>(equipmentRepository.save(equipmentDB), HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		});
	}

	@Override
	@Transactional
	public ResponseEntity<?> deleteEquipment(Long id) {
		if (!equipmentRepository.existsById(id)) {
			return new ResponseEntity<>(MessageUtils.EQUIPMENT_NOT_FOUND, HttpStatus.NOT_FOUND);
		} else {
			equipmentRepository.deleteById(id);
			return new ResponseEntity<>(MessageUtils.EQUIPMENT_FOUND, HttpStatus.OK);
		}
	}

	private void updateEquipmentProperties(Equipment equipment, Equipment equipmentDB) {
		equipmentDB.setCode(equipment.getCode());
		equipmentDB.setDescription(equipment.getDescription());
	}

}
