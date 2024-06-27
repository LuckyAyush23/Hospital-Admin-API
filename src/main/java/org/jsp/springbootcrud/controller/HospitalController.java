package org.jsp.springbootcrud.controller;

import org.jsp.springbootcrud.dto.Hospital;
import org.jsp.springbootcrud.dto.ResponseStructure;
import org.jsp.springbootcrud.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {
	@Autowired
	private HospitalService hospitalService;

	@PostMapping("/{admin_id}")
	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(@RequestBody Hospital hospital,
			@PathVariable int admin_id) {
		return hospitalService.saveHospital(hospital, admin_id);
	}
}