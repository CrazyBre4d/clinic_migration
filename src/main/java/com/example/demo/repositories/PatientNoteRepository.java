package com.example.demo.repositories;

import com.example.demo.models.entities.PatientNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientNoteRepository extends JpaRepository<PatientNote, Long>, JpaSpecificationExecutor<PatientNote> {
}