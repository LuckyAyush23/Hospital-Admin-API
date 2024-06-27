package org.jsp.springbootcrud.repository;

import org.jsp.springbootcrud.dto.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer>{

}
