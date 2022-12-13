package com.company.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.company.model.Designation;

public interface DesignationRepo extends JpaRepository<Designation, Long> {

	@Query(nativeQuery = true, value = "select * from designation where employee_id=?")
	List<Designation> findByEmployeeId(Long id);

	@Query(nativeQuery = true, value = "delete from designation where employee_id=?")
	@Transactional
	@Modifying
	void deleteByEmployeeId(Long id);

}
