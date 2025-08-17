package com.example.demo.mappers;

import com.example.demo.models.dtos.PatientNoteDto;
import com.example.demo.models.entities.PatientNote;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientNoteMapper {
    PatientNote toEntity(PatientNoteDto patientNoteDto);

    PatientNoteDto toDto(PatientNote patientNote);

}