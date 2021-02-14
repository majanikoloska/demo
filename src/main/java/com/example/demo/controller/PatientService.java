package com.example.demo.controller;

import com.example.demo.entities.DoctorEntity;
import com.example.demo.entities.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }


    public List<PatientEntity> getPatients(){
        return patientRepository.findAll();
    }

    public PatientEntity getPatientById(Integer id){
        return patientRepository.findById(id).get();
    }

    public void addPatient( PatientEntity patientEntity){
        patientRepository.save(patientEntity);
    }

    public void addPatientsDoctor(List<PatientEntity> patientEntities, DoctorEntity doctorEntity){
        patientEntities.stream().forEach(o -> {o.setDoctor(doctorEntity); patientRepository.save(o);});
    }


    public void deletePatient(Integer id){
        patientRepository.deleteById(id);
    }
}
