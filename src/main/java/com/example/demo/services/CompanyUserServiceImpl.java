package com.example.demo.services;

import com.example.demo.repositories.CompanyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CompanyUserServiceImpl implements CompanyUserService {

    private final CompanyUserRepository companyUserRepository;


}
