package com.fiipractic.health.control.service;

import com.fiipractic.health.boundry.exceptions.NotFoundException;
import com.fiipractic.health.entity.model.Doctor;
import com.fiipractic.health.entity.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctor(Long id) {
        return doctorRepository.findById(id).get();

    }

    @Override
    public Doctor updateDoctor(Doctor doctor) throws NotFoundException {
        if (doctor.getId() != null) {
            return doctorRepository.save(doctor);
        }
        throw new NotFoundException("The doctor you tried to update does not have an id");
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        doctorRepository.delete(doctor);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
