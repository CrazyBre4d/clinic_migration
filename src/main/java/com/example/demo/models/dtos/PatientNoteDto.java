package com.example.demo.models.dtos;

import com.example.demo.models.entities.PatientProfile;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.demo.models.entities.PatientNote}
 */
@Value
public class PatientNoteDto implements Serializable {
    Long id;
    LocalDate createdDateTime;
    LocalDate lastModifiedDateTime;
    CompanyUserDto createdByUser;
    CompanyUserDto lastModifiedByUser;
    String note;
    PatientProfile patient;
}