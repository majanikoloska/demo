package com.example.demo.controller;

import com.example.demo.entities.DoctorEntity;
import com.example.demo.entities.DocumentReportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DocumentReportController documentReportController;

    @Autowired
    private PatientController patientController;

    @Autowired
    private DiseaseController diseaseController;
    
    @Autowired
    public DoctorController(DoctorService doctorService, PatientController patientController, 
                            DiseaseController diseaseController, DocumentReportController documentReportController){
        this.doctorService = doctorService;
        this.diseaseController = diseaseController;
        this.patientController = patientController;
        this.documentReportController = documentReportController;
    }

    @GetMapping("")
    public List<DoctorEntity> getDoctors(){
        return doctorService.getDoctors();
    }

//    @GetMapping("/{id}")
//    public List<DoctorEntity> getDoctorsByPatientId(@PathVariable Integer id){
//        return doctorService.getDoctorsByPatientId(id);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorEntity> getDoctorById(@PathVariable Integer id){
        try{
            DoctorEntity doctorEntity  = doctorService.getDoctorById(id);
            return new ResponseEntity<DoctorEntity> (doctorEntity , HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<DoctorEntity> (HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<DoctorEntity> addDoctor(@RequestBody DoctorEntity doctorEntity){
        String error = HttpStatus.OK.toString();
        Long startTime = System.currentTimeMillis();
        try{
            startTime = System.currentTimeMillis();
            DoctorEntity doctor = doctorService.addDoctor(doctorEntity);
            patientController.addPatientsDoctor(doctorEntity.getPatients(), doctorEntity);
            doctorEntity.getPatients().stream().forEach(o ->
                    diseaseController.addPatientDiseases(o.getDiseases(), o));
            DocumentReportEntity documentReportEntity = new DocumentReportEntity();
            documentReportEntity.setDoctorId(doctorEntity.getId());
            documentReportEntity.setExecutiontime(startTime.intValue());
            documentReportEntity.setError(error);
            documentReportController.add(documentReportEntity);
            return new ResponseEntity<DoctorEntity>(doctor, HttpStatus.OK);


        }catch(Exception e){
            error = e.toString();
            DocumentReportEntity documentReportEntity = new DocumentReportEntity();
            documentReportEntity.setDoctorId(doctorEntity.getId());
            documentReportEntity.setExecutiontime(startTime.intValue());
            documentReportEntity.setError(error);
            documentReportController.add(documentReportEntity);
            return new ResponseEntity<DoctorEntity>( HttpStatus.NOT_FOUND);
        }


    }
//
//    @PostMapping("/list")
//    public void addDoctors(List<DoctorEntity> doctorEntity ){
//        doctorService.addDoctors(doctorEntity );
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@RequestBody DoctorEntity doctorEntity ,
                                           @PathVariable Integer id){
        try{
            DoctorEntity existing = doctorService.getDoctorById(id);
            doctorService.addDoctor(doctorEntity );
            return new ResponseEntity<> (HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Integer id){
        doctorService.deleteDoctor(id);
    }
}
