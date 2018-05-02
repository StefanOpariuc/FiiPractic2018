package com.fiipractic.health.boundry.controller;

import com.fiipractic.health.aspects.CustomLogAnnotation;
import com.fiipractic.health.boundry.exceptions.BadRequestException;
import com.fiipractic.health.control.service.PatientService;
import com.fiipractic.health.entity.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping(value = "patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Patient savePatient(@RequestBody Patient patient) throws BadRequestException {
        Patient patientReturned = null;
        try {
            patientReturned = patientService.savePatient(patient);
        } catch (ConstraintViolationException ex) {
            throw new BadRequestException(ex.getMessage());
        }
        return patientReturned;
    }

    @GetMapping("/filter")
    @CustomLogAnnotation
    public List<Patient> getPatientsByAge(@RequestParam("patientAge") Long patientAge) {
        return patientService.findAllPatientsWithAge(patientAge);
    }

}
