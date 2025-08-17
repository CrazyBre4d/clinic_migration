package com.example.demo.models.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
@Builder
public class PatientProfileDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String oldClientGuid;
    private Short statusId;
}