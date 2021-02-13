package com.example.demo.controller;

import com.example.demo.entities.DiseaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disease")
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @GetMapping("/{id}")
    public List<DiseaseEntity> getDiseasesByPatientId(@PathVariable int id){
        return diseaseService.getDiseasesByPatientId(id);
    }

    @PostMapping("")
    public void addDisease(DiseaseEntity diseaseEntity){
        diseaseService.addDisease(diseaseEntity);
    }

    @PostMapping("/list")
    public void addDiseases(List<DiseaseEntity> diseaseEntity){
        diseaseService.addDiseases(diseaseEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteDisease(@PathVariable int id){
         diseaseService.deleteDisease(id);
    }
}
