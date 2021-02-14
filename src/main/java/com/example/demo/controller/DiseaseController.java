package com.example.demo.controller;

import com.example.demo.entities.DiseaseEntity;
import com.example.demo.entities.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/disease")
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;
    @Autowired
    public DiseaseController(DiseaseService diseaseService){
        this.diseaseService = diseaseService;
    }

    @GetMapping("/hello")
    public PatientEntity getHello(){
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName("Maja");
        patientEntity.setLastName("Nikoloska");
        return patientEntity;
    }

    @GetMapping("")
    public List<DiseaseEntity> getDiseases(){
        return diseaseService.getDiseases();
    }

//    @GetMapping("/{id}")
//    public List<DiseaseEntity> getDiseasesByPatientId(@PathVariable Integer id){
//        return diseaseService.getDiseasesByPatientId(id);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<DiseaseEntity> getDiseaseById(@PathVariable Integer id){
        try{
            DiseaseEntity diseaseEntity = diseaseService.getDiseaseById(id);
            return new ResponseEntity<DiseaseEntity> (diseaseEntity, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<DiseaseEntity> (HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public void addDisease(@RequestBody DiseaseEntity diseaseEntity){
        diseaseService.addDisease(diseaseEntity);
    }
//
    @PostMapping("/addPatientDiseases")
    public void addPatientDiseases(List<DiseaseEntity> diseaseEntities, PatientEntity patientEntity){
        diseaseService.addPatientDiseases(diseaseEntities, patientEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> deleteDisease(@RequestBody DiseaseEntity diseaseEntity,
                                           @PathVariable Integer id){
        try{
            DiseaseEntity existing = diseaseService.getDiseaseById(id);
            diseaseService.addDisease(diseaseEntity);
            return new ResponseEntity<> (HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public void deleteDisease(@PathVariable Integer id){
         diseaseService.deleteDisease(id);
    }
}
