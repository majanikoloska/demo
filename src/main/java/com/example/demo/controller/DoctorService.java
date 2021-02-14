package com.example.demo.controller;

import com.example.demo.entities.DoctorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorEntity> getDoctors(){
        return doctorRepository.findAll();
    }

    public DoctorEntity getDoctorById(Integer id){
        return doctorRepository.findById(id).get();
    }

    public DoctorEntity addDoctor( DoctorEntity diseaseEntity){
        return doctorRepository.save(diseaseEntity);
    }

    public void addDoctors(List<DoctorEntity> diseaseEntities){
        diseaseEntities.stream().forEach(o -> addDoctor(o));
    }


    public void deleteDoctor(Integer id){
        doctorRepository.deleteById(id);
    }
}
