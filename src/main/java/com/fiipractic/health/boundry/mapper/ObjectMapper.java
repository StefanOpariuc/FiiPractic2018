package com.fiipractic.health.boundry.mapper;

import com.fiipractic.health.boundry.transferobject.AppointmentDto;
import com.fiipractic.health.entity.Appointment;
import com.fiipractic.health.entity.model.Doctor;
import com.fiipractic.health.entity.model.Patient;

public class ObjectMapper {

    public static void map2DoctorDb(Doctor doctorDb, Doctor doctorRequest) {
        doctorDb.setName(doctorRequest.getName());
        doctorDb.setFunction(doctorRequest.getFunction());
    }

    public static Appointment toObject(AppointmentDto appointmentDto, Patient patient, Doctor doctor){
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setReason(appointmentDto.getReason());
        return appointment;
    }
}
