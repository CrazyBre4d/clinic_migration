package com.example.demo.repositories;

import com.example.demo.models.entities.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyUserRepository extends JpaRepository<CompanyUser, Long>, JpaSpecificationExecutor<CompanyUser> {
    Optional<CompanyUser> findByLogin(String login);

}