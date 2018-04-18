package com.fiipractic.health.boundry.controller;

import com.fiipractic.health.boundry.exceptions.BadRequestException;
import com.fiipractic.health.boundry.mapper.ObjectMapper;
import com.fiipractic.health.boundry.transferobject.AppointmentDto;
import com.fiipractic.health.control.service.AppointmentService;
import com.fiipractic.health.control.service.DoctorService;
import com.fiipractic.health.control.service.PatientService;
import com.fiipractic.health.entity.Appointment;
import com.fiipractic.health.entity.model.Doctor;
import com.fiipractic.health.entity.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;
    private DoctorService doctorService;
    private PatientService patientService;
    private JavaMailSender javaMailSender;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService, PatientService patientService, JavaMailSender javaMailSender) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.javaMailSender = javaMailSender;
    }

    @PostMapping
    public Appointment createAppointment(@Valid @RequestBody AppointmentDto appointmentDto) throws BadRequestException {
        Doctor doctor = doctorService.getDoctor(appointmentDto.getDoctorId());
        Patient patient = patientService.getPatient(appointmentDto.getPatientId());
        if (doctor == null || patient == null) {
            throw new BadRequestException("Invalid data sent");
        }

        Appointment appointment = appointmentService.save(ObjectMapper.toObject(appointmentDto, patient, doctor));

        //sendEmail(appointment);
        return appointment;
    }

    @GetMapping
    public List<Appointment> getAppointments(){
        return appointmentService.getAppointments();
    }

    @GetMapping(value = "/{id}")
    public Appointment getAppointment(@PathVariable Long id){
        return appointmentService.getById(id);
    }

    @GetMapping(value = "/filter")
    public List<Appointment> filterAppointments(@RequestParam("type") String type, @RequestParam("id") Long id) throws BadRequestException {
        switch(type){
            case "doctor": return appointmentService.getAppointmentsByDoctor(id);
            case "patient": return appointmentService.getAppointmentsByPatient(id);
            default: throw new BadRequestException("Type is not known");
        }
    }


}
