package com.fiipractic.health.controller;

import com.fiipractic.health.exceptions.BadRequestException;
import com.fiipractic.health.exceptions.NotFoundException;
import com.fiipractic.health.mapper.ObjectMapper;
import com.fiipractic.health.model.Doctor;
import com.fiipractic.health.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="doctors")
public class DoctorController {

    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getDoctors(){
        return doctorService.getDoctors();
    }

    @GetMapping(value = "/{id}")
    public Doctor getDoctor(@PathVariable("id") Long id) throws NotFoundException {
        Doctor doctor = doctorService.getDoctor(id);
        if(doctor == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }
        return doctor;
    }

    @PutMapping(value = "/{id}")
    public Doctor updateDoctor(@PathVariable("id") Long id, @RequestBody Doctor doctor) throws BadRequestException, NotFoundException {
        //validate request
        if(!id.equals(doctor.getId())){
            throw new BadRequestException("The id is not the same with id from object");
        }
        Doctor doctorDb = doctorService.getDoctor(id);
        if(doctorDb == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }
        ObjectMapper.map2DoctorDb(doctorDb, doctor);
        return doctorService.updateDoctor(doctorDb);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Doctor saveDoctor(@RequestBody Doctor doctor){
        return doctorService.saveDoctor(doctor);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable Long id) throws NotFoundException {
        Doctor doctorDb = doctorService.getDoctor(id);
        if(doctorDb == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }
        doctorService.deleteDoctor(doctorDb);
    }
}
