package com.example.demo.controller;

import com.example.demo.entities.DiseaseEntity;
import com.example.demo.entities.DoctorEntity;
import com.example.demo.entities.DocumentReportEntity;
import com.example.demo.entities.PatientEntity;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.internal.verification.MockAwareVerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestConfiguration
@RunWith(SpringRunner.class)
class DoctorControllerTest {

    @Autowired
    private static MockMvc mockMvc;
    @InjectMocks
    private static DoctorRepository doctorRepository = mock(DoctorRepository.class) ;

    @InjectMocks
    private static PatientRepository patientRepository  = mock(PatientRepository.class);

    @InjectMocks
    private static DiseaseRepository diseaseRepository  = mock(DiseaseRepository.class);

    @InjectMocks
    private static DocumentReportRepository documentReportRepository  = mock(DocumentReportRepository.class);

    @Autowired
    private static DoctorService doctorService = new DoctorService(doctorRepository);

    @MockBean
    private static PatientService patientService = new PatientService(patientRepository);

    @MockBean
    private static DiseaseService diseaseService = new DiseaseService(diseaseRepository);

    @MockBean
    private static DocumentReportService documentReportService = new DocumentReportService(documentReportRepository);

    @MockBean
    private static PatientController patientController = new PatientController(diseaseService, patientService);

    @MockBean
    private static DiseaseController diseaseController = new DiseaseController(diseaseService);

    @MockBean
    private static DocumentReportController documentReportController = new DocumentReportController(documentReportService);

    @MockBean
    private static DoctorController doctorController = new DoctorController(doctorService ,patientController, diseaseController,
            documentReportController);




    static DoctorEntity doctorEntity = new DoctorEntity();
    static DocumentReportEntity documentReportEntity = new DocumentReportEntity();
    static List<PatientEntity> patientEntity = new ArrayList<>();
    static List<DiseaseEntity> diseaseEntity1 = new ArrayList<>();
    static List<DiseaseEntity> diseaseEntity2 = new ArrayList<>();
    static List<DiseaseEntity> diseaseEntity3 = new ArrayList<>();

    @BeforeAll
    public static void setup(){

        DiseaseEntity disease1 = new DiseaseEntity();
        disease1.setDiseaseId("nice_to_people");
        disease1.setId(1);
        DiseaseEntity disease2 = new DiseaseEntity();
        disease1.setDiseaseId("long_legs");
        disease1.setId(2);
        DiseaseEntity disease3 = new DiseaseEntity();
        disease1.setDiseaseId("used_to_have_dredds");
        disease1.setId(3);
        DiseaseEntity disease4 = new DiseaseEntity();
        disease1.setDiseaseId("nice_to_people");
        disease1.setId(4);
        DiseaseEntity disease5 = new DiseaseEntity();
        disease1.setDiseaseId("chocaholic");
        disease1.setId(5);
        DiseaseEntity disease6 = new DiseaseEntity();
        disease1.setDiseaseId("great_haircut");
        disease1.setId(6);

        diseaseEntity1.add(disease1);
        diseaseEntity1.add(disease2);
        diseaseEntity2.add(disease3);
        diseaseEntity2.add(disease4);
        diseaseEntity3.add(disease5);
        diseaseEntity3.add(disease6);


        PatientEntity patient1 = new PatientEntity();
        patient1.setId(1);
        patient1.setFirstName("John");
        patient1.setLastName("Smith");
        patient1.setDiseases(diseaseEntity1);
        PatientEntity patient2 = new PatientEntity();
        patient2.setId(2);
        patient2.setFirstName("Jenny");
        patient2.setLastName("Cox");
        patient2.setDiseases(diseaseEntity2);
        PatientEntity patient3 = new PatientEntity();
        patient3.setId(3);
        patient3.setFirstName("Martin");
        patient3.setLastName("Lewis");
        patient3.setDiseases(diseaseEntity3);

        patientEntity.add(patient1);
        patientEntity.add(patient2);
        patientEntity.add(patient3);

        doctorEntity.setDepartment("marand");
        doctorEntity.setId(100);
        doctorEntity.setPatients(patientEntity);

//        mockMvc.perform(post("/doctor")
//                .contentType("application/json")
//                .content(String.valueOf(doctorEntity))
//                .andExpect(status().isOk())
//        );

//        Mockito.when(doctorController.addDoctor(doctorEntity)).thenReturn(new ResponseEntity<DoctorEntity> (doctorEntity , HttpStatus.OK));

        documentReportEntity.setDoctorId(doctorEntity.getId());
        documentReportEntity.setExecutiontime(123);
        documentReportEntity.setError(HttpStatus.OK.toString());
        Mockito.when(documentReportController.add(documentReportEntity)).thenReturn(new ResponseEntity<DocumentReportEntity> (documentReportEntity , HttpStatus.OK));

    }


    @Test
    void addDoctor() throws Exception {

        assertEquals(new ResponseEntity<DoctorEntity> (doctorEntity , HttpStatus.OK), doctorController.addDoctor(doctorEntity));
        assertEquals(new ResponseEntity<DocumentReportEntity> (documentReportEntity , HttpStatus.OK), documentReportController.add(documentReportEntity));
    }
}