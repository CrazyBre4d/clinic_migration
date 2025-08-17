package com.example.demo.models.dtos;

import com.example.demo.models.entities.PatientProfile;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PatientNoteDto {
    private Long id;
    private LocalDate createdDateTime;
    private LocalDate lastModifiedDateTime;
    private CompanyUserDto createdByUser;
    private CompanyUserDto lastModifiedByUser;
    private String note;
    private PatientProfile patient;
}