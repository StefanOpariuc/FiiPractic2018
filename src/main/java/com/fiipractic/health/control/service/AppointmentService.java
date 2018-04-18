package com.fiipractic.health.control.service;

import com.fiipractic.health.boundry.exceptions.NotFoundException;
import com.fiipractic.health.entity.Appointment;
import com.fiipractic.health.entity.model.Doctor;

import java.util.List;

public interface AppointmentService {

    Appointment getById(Long id);
    List<Appointment> getAppointments();
    List<Appointment> getAppointmentsByPatient(Long patientId);
    List<Appointment> getAppointmentsByDoctor(Long doctorId);
    Appointment save(Appointment appointment);
    void delete(Long appointmentId);

}
