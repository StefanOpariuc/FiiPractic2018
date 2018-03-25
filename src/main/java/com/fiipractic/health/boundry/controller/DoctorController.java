package com.fiipractic.health.boundry.controller;

import com.fiipractic.health.control.service.DoctorService;
import com.fiipractic.health.entity.model.Doctor;
import com.fiipractic.health.boundry.exceptions.BadRequestException;
import com.fiipractic.health.boundry.exceptions.NotFoundException;
import com.fiipractic.health.boundry.mapper.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "doctors")
public class DoctorController {

    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getDoctors() {
        return doctorService.getDoctors();
    }

    @GetMapping(value = "/{id}")
    public Doctor getDoctor(@PathVariable("id") Long id) throws NotFoundException {
        Doctor doctor = doctorService.getDoctor(id);
        if (doctor == null) {
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }
        return doctor;
    }

    @PutMapping(value = "/{id}")
    public Doctor updateDoctor(@PathVariable("id") Long id, @RequestBody Doctor doctor) throws BadRequestException, NotFoundException {
        //validate request
        if (!id.equals(doctor.getId())) {
            throw new BadRequestException("The id is not the same with id from object");
        }
        Doctor doctorDb = doctorService.getDoctor(id);
        if (doctorDb == null) {
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }
        ObjectMapper.map2DoctorDb(doctorDb, doctor);
        return doctorService.updateDoctor(doctorDb);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Doctor saveDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable Long id) throws NotFoundException {
        Doctor doctorDb = doctorService.getDoctor(id);
        if (doctorDb == null) {
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }
        doctorService.deleteDoctor(doctorDb);
    }
}
