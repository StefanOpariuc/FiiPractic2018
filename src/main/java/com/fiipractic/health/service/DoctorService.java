package com.fiipractic.health.service;

import com.fiipractic.health.model.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> getDoctors();

    Doctor getDoctor(Long id);

    Doctor updateDoctor(Doctor doctor);

    void deleteDoctor(Doctor doctor);

    Doctor saveDoctor(Doctor doctor);
}
