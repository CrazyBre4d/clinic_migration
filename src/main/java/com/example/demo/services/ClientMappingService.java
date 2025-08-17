package com.example.demo.services;


import com.example.demo.models.entities.PatientProfile;
import com.example.demo.repositories.PatientProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientMappingService {

    private final PatientProfileRepository patientProfileRepository;

    public Optional<PatientProfile> findPatientByOldClientGuid(String clientGuid) {
        return patientProfileRepository.findAll().stream()
                .filter(p -> p.getOldClientGuid() != null &&
                        Arrays.asList(p.getOldClientGuid().split(","))
                                .contains(clientGuid))
                .findFirst();
    }
}
