package com.example.demo.models.dtos;

import com.example.demo.models.entities.PatientProfile;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PatientNoteDto {
    private Long id;
    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;
    private CompanyUserDto createdByUser;
    private CompanyUserDto lastModifiedByUser;
    private String note;
    private PatientProfile patient;
}