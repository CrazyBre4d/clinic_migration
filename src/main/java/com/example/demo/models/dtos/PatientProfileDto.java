package com.example.demo.models.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.demo.models.entities.PatientProfile}
 */
@Value
public class PatientProfileDto implements Serializable {
    Long id;
    String firstName;
    String lastName;
    String oldClientGuid;
    Short statusId;
}