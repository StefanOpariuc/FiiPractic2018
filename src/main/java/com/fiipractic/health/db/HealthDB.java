package com.fiipractic.health.db;

import com.fiipractic.health.model.Doctor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class HealthDB {

    private Map<Long, Doctor> doctors;

    public HealthDB(){
        this.doctors = new HashMap<>();
    }

    public List<Doctor> getDoctors(){
        return new ArrayList<Doctor>(doctors.values());
    }

    public Doctor getDoctor(Long id){
        return doctors.get(id);
    }

    public Doctor saveDoctor(Doctor doctor){
        //generate an id
        doctor.setId(ThreadLocalRandom.current().nextLong(0, 10000));
        this.doctors.put(doctor.getId(), doctor);
        return doctor;
    }

    public Doctor updateDoctor(Doctor doctor){
        this.doctors.put(doctor.getId(), doctor);
        return doctor;
    }

    public void deleteDoctor(Doctor doctor){
        this.doctors.remove(doctor.getId());
    }
}
