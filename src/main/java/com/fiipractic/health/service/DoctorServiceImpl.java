package com.fiipractic.health.service;

import com.fiipractic.health.db.HealthDB;
import com.fiipractic.health.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private HealthDB healthDB;

    @Autowired
    public DoctorServiceImpl(HealthDB healthDB){
        this.healthDB = healthDB;
    }

    @Override
    public List<Doctor> getDoctors() {
        return healthDB.getDoctors();
    }

    @Override
    public Doctor getDoctor(Long id) {
        return healthDB.getDoctor(id);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        return healthDB.updateDoctor(doctor);
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        healthDB.deleteDoctor(doctor);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor){
        return healthDB.saveDoctor(doctor);
    }
}
