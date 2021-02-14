package com.example.demo.controller;

import com.example.demo.entities.DiseaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiseaseRepository extends JpaRepository<DiseaseEntity, Integer> {

//    @Query("SELECT d FROM DiseaseEntity d WHERE d.diseaseId = :id")
//    public List<DiseaseEntity> getDiseasesByPatientId(@Param("id") Integer id);

//    public List<DiseaseEntity> getDiseases();
}
