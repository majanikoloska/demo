package com.example.demo.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "document_report")
public class DocumentReportEntity {


    private Integer id;
    private String error;
    private Integer executiontime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentReportEntity)) return false;
        DocumentReportEntity that = (DocumentReportEntity) o;
        return
                Objects.equals(getError(), that.getError()) &&
                Objects.equals(getExecutiontime(), that.getExecutiontime()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getError(), getExecutiontime(), getDoctorId());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "error", nullable = true)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Basic
    @Column(name = "executiontime", nullable = true)
    public Integer getExecutiontime() {
        return executiontime;
    }

    public void setExecutiontime(Integer executiontime) {
        this.executiontime = executiontime;
    }

    @Basic
    @Column(name = "doctor_id", nullable = true)
    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    private Integer doctorId;
}
