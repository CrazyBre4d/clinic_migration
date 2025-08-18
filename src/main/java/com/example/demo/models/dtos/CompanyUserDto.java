package com.example.demo.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyUserDto {
    private Long id;
    private String login;
}