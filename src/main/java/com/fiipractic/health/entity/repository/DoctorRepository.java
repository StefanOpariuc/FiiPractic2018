package com.fiipractic.health.entity.repository;


import com.fiipractic.health.entity.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
