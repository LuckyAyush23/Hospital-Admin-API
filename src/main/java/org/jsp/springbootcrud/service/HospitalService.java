package org.jsp.springbootcrud.service;

import java.util.Optional;

import org.jsp.springbootcrud.dto.Admin;
import org.jsp.springbootcrud.dto.Hospital;
import org.jsp.springbootcrud.dto.ResponseStructure;
import org.jsp.springbootcrud.repository.AdminRepository;
import org.jsp.springbootcrud.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;
	@Autowired
	private AdminRepository adminRepository;
	
	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(Hospital hospital,int admin_id){
		ResponseStructure<Hospital> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminRepository.findById(admin_id);
		if (recAdmin.isPresent()) {
			Admin dbAdmin = recAdmin.get();
			dbAdmin.getHospitals().add(hospital);// Adding Hospital to Admin
			hospital.setAdmin(dbAdmin);// Assigning Admin to Hospital
			adminRepository.save(dbAdmin);// Updating Admin
			structure.setData(hospitalRepository.save(hospital));// Adding Hospital
			structure.setMessage("Hospital Added succesfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		structure.setMessage("Cannot add Hospital as Admin Id is Invalid");
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
}
