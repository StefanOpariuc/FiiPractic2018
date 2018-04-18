package com.fiipractic.health.boundry.controller;

import com.fiipractic.health.control.service.PatientService;
import com.fiipractic.health.entity.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "patients")
public class PatientController {

    private PatientService patientService;

    private JavaMailSender javaMailSender;

    @GetMapping
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @GetMapping("/filter")
    public List<Patient> getPatientsByAge(@RequestParam("patientAge") Long patientAge) {
        return patientService.findAllPatientsWithAge(patientAge);
    }

}
