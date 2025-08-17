package com.example.demo.repositories;

import com.example.demo.models.entities.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientProfileRepository extends JpaRepository<PatientProfile, Long>, JpaSpecificationExecutor<PatientProfile> {
}