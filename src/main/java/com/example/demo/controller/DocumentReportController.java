package com.example.demo.controller;

import com.example.demo.entities.DocumentReportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/documentReport")
public class DocumentReportController {

    @Autowired
    private DocumentReportService documentReportService;

    @Autowired
    public DocumentReportController(DocumentReportService documentReportService){
        this.documentReportService = documentReportService;
    }

    @PostMapping("")
    public ResponseEntity<DocumentReportEntity> add(@RequestBody DocumentReportEntity documentReportEntity){

        try{
            DocumentReportEntity document = documentReportService.add(documentReportEntity );
            return new ResponseEntity<DocumentReportEntity> (document , HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<DocumentReportEntity> (HttpStatus.NOT_FOUND);
        }
    }
}
