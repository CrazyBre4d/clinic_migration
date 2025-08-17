package com.example.demo.services;

import com.example.demo.models.entities.CompanyUser;
import com.example.demo.repositories.CompanyUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMappingService {

    private final CompanyUserRepository companyUserRepository;

    @Transactional
    public CompanyUser getOrCreateUser(String login) {
        return companyUserRepository.findByLogin(login)
                .orElseGet(() -> {
                    CompanyUser user = new CompanyUser();
                    user.setLogin(login);
                    return companyUserRepository.save(user);
                });
    }
}
