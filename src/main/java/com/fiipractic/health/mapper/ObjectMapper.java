package com.fiipractic.health.mapper;

import com.fiipractic.health.model.Doctor;

public class ObjectMapper {

    public static void map2DoctorDb(Doctor doctorDb, Doctor doctorRequest){
        doctorDb.setName(doctorRequest.getName());
        doctorDb.setFunction(doctorRequest.getFunction());
    }
}
