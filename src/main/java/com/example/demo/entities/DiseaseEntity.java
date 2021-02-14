package com.example.demo.entities;

import com.example.demo.models.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "disease")
@NamedQueries(value =
        {
                @NamedQuery(name= DiseaseEntity.GET_BY_PATIENT_ID,
                        query = "SELECT d FROM DiseaseEntity d WHERE d.diseaseId = :diseaseId")
        })
public class DiseaseEntity {

    public static final String GET_BY_PATIENT_ID = "DiseaseEntity.getByPatientId";



    private Integer id;
    private String diseaseId;


    @JsonIgnore
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id", nullable = false, length = -1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "disease_id", nullable = false, length = -1)
    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiseaseEntity that = (DiseaseEntity) o;
        return Objects.equals(diseaseId, that.diseaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diseaseId);
    }
}
