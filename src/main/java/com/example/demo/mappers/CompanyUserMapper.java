package com.example.demo.mappers;

import com.example.demo.models.dtos.CompanyUserDto;
import com.example.demo.models.entities.CompanyUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CompanyUserMapper {
    CompanyUser toEntity(CompanyUserDto companyUserDto);

    CompanyUserDto toCompanyUserDto(CompanyUser companyUser);

}