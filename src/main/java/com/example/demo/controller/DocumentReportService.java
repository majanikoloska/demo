package com.example.demo.controller;

import com.example.demo.entities.DocumentReportEntity;
import com.example.demo.entities.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DocumentReportService {

    @Autowired
    private DocumentReportRepository documentReportRepository;

    @Autowired
    public DocumentReportService(DocumentReportRepository documentReportRepository){
        this.documentReportRepository = documentReportRepository;
    }


    public DocumentReportEntity add(DocumentReportEntity documentReportEntity){
        return documentReportRepository.save(documentReportEntity);
    }

}
