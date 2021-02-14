package com.example.demo.controller;

import com.example.demo.entities.DocumentReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentReportRepository  extends JpaRepository<DocumentReportEntity, Integer> {
}
