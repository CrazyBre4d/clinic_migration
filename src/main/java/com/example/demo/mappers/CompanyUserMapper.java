package com.example.demo.mappers;

import com.example.demo.CompanyUserDto;
import com.example.demo.models.entities.CompanyUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CompanyUserMapper {
    CompanyUser toEntity(CompanyUserDto companyUserDto);

    CompanyUserDto toCompanyUserDto(CompanyUser companyUser);

    CompanyUser updateWithNull(CompanyUserDto companyUserDto, @MappingTarget CompanyUser companyUser);
}