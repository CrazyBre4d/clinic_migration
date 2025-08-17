package com.example.demo.models.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.demo.models.entities.CompanyUser}
 */
@Value
public class CompanyUserDto {
    Long id;
    String login;
}