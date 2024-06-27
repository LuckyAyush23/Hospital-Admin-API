package org.jsp.springbootcrud.service;

import java.util.List;
import java.util.Optional;

import org.jsp.springbootcrud.dto.Admin;
import org.jsp.springbootcrud.dto.ResponseStructure;
import org.jsp.springbootcrud.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
		structure.setMessage("Admin saved successfully");
		structure.setData(adminRepository.save(admin));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin) {
		Optional<Admin> recAdmin = adminRepository.findById(admin.getId());
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		if(recAdmin.isPresent()) {
			Admin dbAdmin = recAdmin.get();
			dbAdmin.setEmail(admin.getEmail());
			dbAdmin.setName(admin.getName());
			dbAdmin.setPassword(admin.getPassword());
			dbAdmin.setPhone(admin.getPhone());
			structure.setMessage("Admin updated");
			structure.setData(adminRepository.save(admin));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		else {
			structure.setData(null);
			structure.setMessage("Cannot update Admin as Id is invalid");
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
	}
	
	public ResponseEntity<ResponseStructure<Admin>> findById(int id) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminRepository.findById(id);
		if(recAdmin.isPresent()) {
			structure.setData(recAdmin.get());
			structure.setMessage("Admin found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		else {
			structure.setData(null);
			structure.setMessage("Invalid id");
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Admin>>> findByName(String name) {
		ResponseStructure<List<Admin>> structure = new ResponseStructure<>();
		List<Admin> admins = adminRepository.findByName(name);
		structure.setData(admins);
		if(admins.isEmpty()) {
			structure.setMessage("No admin found");
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
		}
		structure.setMessage("Admin found");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<Admin>> verify(long phone,String password){
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminRepository.findByPhoneAndPassword(phone,password);
		if(recAdmin.isPresent()) {
		      structure.setData(recAdmin.get());
		      structure.setMessage("Admin verified");
		      structure.setStatusCode(HttpStatus.OK.value());
		      return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		structure.setData(null);
		structure.setMessage("Admin not verified as invalid phone or password");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<String>> delete(int id){
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminRepository.findById(id);
		if(recAdmin.isPresent()) {
			structure.setData("Admin found");
			structure.setMessage("Admin deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		structure.setData("Admin not found");
		structure.setMessage("Admin cannot be deleted");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}

}
