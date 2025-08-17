package com.example.demo.mappers;

import com.example.demo.models.dtos.PatientProfileDto;
import com.example.demo.models.entities.PatientProfile;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientProfileMapper {
    PatientProfile toEntity(PatientProfileDto patientProfileDto);

    PatientProfileDto toDto(PatientProfile patientProfile);

}