package com.example.demo.controller;

import com.example.demo.entities.DoctorEntity;
import com.example.demo.entities.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    public PatientController(DiseaseService diseaseService, PatientService patientService){
        this.diseaseService = diseaseService;
        this.patientService = patientService;
    }

    @GetMapping("")
    public List<PatientEntity> getPatients(){
        return patientService.getPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientEntity> getPatientById(@PathVariable Integer id){
        try{
            PatientEntity patientEntity  = patientService.getPatientById(id);
            return new ResponseEntity<PatientEntity> (patientEntity , HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<PatientEntity> (HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public void addPatient(@RequestBody PatientEntity patientEntity){
        patientService.addPatient(patientEntity );
        diseaseService.addPatientDiseases(patientEntity .getDiseases(), patientEntity );
    }
//
    @PostMapping("/addPatientsDoctor")
    public void addPatientsDoctor(List<PatientEntity> patientEntities, DoctorEntity doctorEntity){
        patientService.addPatientsDoctor(patientEntities, doctorEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> deletePatient(@RequestBody PatientEntity patientEntity ,
                                           @PathVariable Integer id){
        try{
            PatientEntity existing = patientService.getPatientById(id);
            patientService.addPatient(patientEntity );
            return new ResponseEntity<> (HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Integer id){
        patientService.deletePatient(id);
    }
}
