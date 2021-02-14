package com.example.demo.controller;

import com.example.demo.entities.DiseaseEntity;
import com.example.demo.entities.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class DiseaseService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseService(DiseaseRepository diseaseRepository){
        this.diseaseRepository = diseaseRepository;
    }

    public List<DiseaseEntity> getDiseases(){
        return diseaseRepository.findAll();
    }

//    public List<DiseaseEntity> getDiseasesByPatientId(Integer id){
//        return diseaseRepository.getDiseasesByPatientId(id);
//    }

    public DiseaseEntity getDiseaseById(Integer id){
        return diseaseRepository.findById(id).get();
    }

    public void addDisease( DiseaseEntity diseaseEntity){
        diseaseRepository.save(diseaseEntity);
    }

    public void addDiseases(List<DiseaseEntity> diseaseEntities){
        diseaseEntities.stream().forEach(o -> addDisease(o));
    }

    public void addPatientDiseases(List<DiseaseEntity> diseaseEntities, PatientEntity patientEntity){
        diseaseEntities.stream().forEach(o -> o.setPatient(patientEntity));
        addDiseases(diseaseEntities);
    }


    public void deleteDisease(Integer id){
        diseaseRepository.deleteById(id);
    }

}
