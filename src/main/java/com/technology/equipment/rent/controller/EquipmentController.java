package com.technology.equipment.rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.technology.equipment.rent.entity.Equipment;
import com.technology.equipment.rent.service.EquipmentService;
import com.technology.equipment.rent.utils.MessageUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;

	@ApiOperation(value = "getEquipments", notes = "Endpoint that allows obtaining a list of equipments registered to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageUtils.EQUIPMENTS_FOUND),
			@ApiResponse(code = 404, message = MessageUtils.EQUIPMENTS_NOT_FOUND)})
	@GetMapping(path = "/equipments")
	public ResponseEntity<?> getEquipments() {
		return equipmentService.getEquipments();
	}

	@ApiOperation(value = "getEquipment", notes = "Endpoint that allows obtaining anequipment registered to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageUtils.EQUIPMENT_FOUND),
			@ApiResponse(code = 404, message = MessageUtils.EQUIPMENT_NOT_FOUND)})
	@GetMapping(path = "/equipments/{id}")
	public ResponseEntity<?> getEquipment(@PathVariable Long id) {
		return equipmentService.getEquipment(id);
	}

	@ApiOperation(value = "saveEquipment", notes = "Endpoint that allows record an equipment to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = MessageUtils.EQUIPMENT_CREATED)})
	@PostMapping(path = "/equipments")
	public ResponseEntity<?> saveEquipment(@RequestBody Equipment equipment) {
		return equipmentService.saveEquipment(equipment);
	}

	@ApiOperation(value = "updateEquipment", notes = "Endpoint that allows updating an equipment to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageUtils.EQUIPMENT_FOUND),
			@ApiResponse(code = 404, message = MessageUtils.EQUIPMENT_NOT_FOUND)})
	@PutMapping(path = "/equipments/{id}")
	public ResponseEntity<?> updateEquipment(@RequestBody Equipment equipment, @PathVariable Long id) {
		return equipmentService.updateEquipment(equipment, id);
	}

	@ApiOperation(value = "deleteEquipment", notes = "Endpoint that allows deleting an equipment to the system.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageUtils.EQUIPMENT_DELETED),
			@ApiResponse(code = 404, message = MessageUtils.EQUIPMENT_NOT_FOUND)})
	@DeleteMapping(path = "/equipments/{id}")
	public ResponseEntity<?> deleteEquipment(@PathVariable Long id) {
		return equipmentService.deleteEquipment(id);
	}

}
