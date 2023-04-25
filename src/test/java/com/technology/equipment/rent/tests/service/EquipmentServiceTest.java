package com.technology.equipment.rent.tests.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.technology.equipment.rent.entity.Equipment;
import com.technology.equipment.rent.repository.EquipmentRepository;
import com.technology.equipment.rent.service.impl.EquipmentServiceImpl;
import com.technology.equipment.rent.utils.MessageUtils;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
class EquipmentServiceTest {
	
	@Mock
	private EquipmentRepository equipmentRepository;
	
	@InjectMocks
	private EquipmentServiceImpl equipmentService;
	
	private Equipment equipment;

	@BeforeEach
	void setUp() throws Exception {
		equipment = new Equipment(5L, "100105", "EQUIPO5");
	}

	@Test
	void testGetEquipmentsNotFound() {
		when(equipmentRepository.findAll()).thenReturn(null);
		ResponseEntity<?> response = equipmentService.getEquipments();
		assertEquals(MessageUtils.EQUIPMENTS_NOT_FOUND, response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(equipmentRepository).findAll();
	}
	
	@Test
	void testGetEquipmentsFound() {
		when(equipmentRepository.findAll()).thenReturn(Collections.singletonList(equipment));
		ResponseEntity<?> response = equipmentService.getEquipments();
		assertEquals(Collections.singletonList(equipment), response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(equipmentRepository).findAll();
	}
	
	@Test
	void testGetEquipmentFound() {
		when(equipmentRepository.findById(5L)).thenReturn(Optional.of(equipment));
		ResponseEntity<?> response = equipmentService.getEquipment(5L);
		assertEquals(equipment, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(equipmentRepository).findById(5L);
	}
	
	@Test
	void testGetEquipmentNotFound() {
		when(equipmentRepository.findById(6L)).thenReturn(Optional.ofNullable(null));
		ResponseEntity<?> response = equipmentService.getEquipment(6L);
		assertEquals(MessageUtils.EQUIPMENT_NOT_FOUND, response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		verify(equipmentRepository).findById(6L);
	}

}
