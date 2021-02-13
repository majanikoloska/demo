package com.example.demo.controller;

import com.example.demo.entities.DiseaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class DiseaseService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    public List<DiseaseEntity> getDiseasesByPatientId(int id){
        return diseaseRepository.getDiseasesByPatientId(id);
    }

    public void addDisease(DiseaseEntity diseaseEntity){
        diseaseRepository.save(diseaseEntity);
    }

    public void addDiseases(List<DiseaseEntity> diseaseEntities){
        diseaseEntities.stream().forEach(o -> addDisease(o));
    }


    public void deleteDisease(int id){
        diseaseRepository.deleteById(id);
    }

}
