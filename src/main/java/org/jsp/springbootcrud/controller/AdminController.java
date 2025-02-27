package org.jsp.springbootcrud.controller;

import java.util.List;

import org.jsp.springbootcrud.dto.Admin;
import org.jsp.springbootcrud.dto.ResponseStructure;
import org.jsp.springbootcrud.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
     @Autowired
	private AdminService adminService;
	
     @PostMapping
     public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody Admin admin) {
    	 return adminService.saveAdmin(admin);
     }
     
     @PutMapping
     public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody Admin admin) {
    	 return adminService.updateAdmin(admin);
     }
     
     @GetMapping("/{id}")
     public ResponseEntity<ResponseStructure<Admin>> findById(@PathVariable int id){
    	 return adminService.findById(id);
     }
     
     @GetMapping("/{name}")
     public ResponseEntity<ResponseStructure<List<Admin>>> findByName(@PathVariable String name){
    	 return adminService.findByName(name);
     }
   
     
     
     
}
