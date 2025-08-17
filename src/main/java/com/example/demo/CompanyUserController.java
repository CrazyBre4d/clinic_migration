package com.example.demo;

import com.example.demo.mappers.CompanyUserMapper;
import com.example.demo.models.entities.CompanyUser;
import com.example.demo.repositories.CompanyUserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/admin-ui/company-users")
@RequiredArgsConstructor
public class CompanyUserController {

    private final CompanyUserRepository companyUserRepository;

    private final CompanyUserMapper companyUserMapper;

    private final ObjectMapper objectMapper;

    @GetMapping
    public PagedModel<CompanyUserDto> getAll(Pageable pageable) {
        Page<CompanyUser> companyUsers = companyUserRepository.findAll(pageable);
        Page<CompanyUserDto> companyUserDtoPage = companyUsers.map(companyUserMapper::toCompanyUserDto);
        return new PagedModel<>(companyUserDtoPage);
    }

    @GetMapping("/{id}")
    public CompanyUserDto getOne(@PathVariable Long id) {
        Optional<CompanyUser> companyUserOptional = companyUserRepository.findById(id);
        return companyUserMapper.toCompanyUserDto(companyUserOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<CompanyUserDto> getMany(@RequestParam List<Long> ids) {
        List<CompanyUser> companyUsers = companyUserRepository.findAllById(ids);
        return companyUsers.stream()
                .map(companyUserMapper::toCompanyUserDto)
                .toList();
    }

    @PostMapping
    public CompanyUserDto create(@RequestBody CompanyUserDto dto) {
        CompanyUser companyUser = companyUserMapper.toEntity(dto);
        CompanyUser resultCompanyUser = companyUserRepository.save(companyUser);
        return companyUserMapper.toCompanyUserDto(resultCompanyUser);
    }

    @PatchMapping("/{id}")
    public CompanyUserDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        CompanyUser companyUser = companyUserRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        CompanyUserDto companyUserDto = companyUserMapper.toCompanyUserDto(companyUser);
        objectMapper.readerForUpdating(companyUserDto).readValue(patchNode);
        companyUserMapper.updateWithNull(companyUserDto, companyUser);

        CompanyUser resultCompanyUser = companyUserRepository.save(companyUser);
        return companyUserMapper.toCompanyUserDto(resultCompanyUser);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<CompanyUser> companyUsers = companyUserRepository.findAllById(ids);

        for (CompanyUser companyUser : companyUsers) {
            CompanyUserDto companyUserDto = companyUserMapper.toCompanyUserDto(companyUser);
            objectMapper.readerForUpdating(companyUserDto).readValue(patchNode);
            companyUserMapper.updateWithNull(companyUserDto, companyUser);
        }

        List<CompanyUser> resultCompanyUsers = companyUserRepository.saveAll(companyUsers);
        return resultCompanyUsers.stream()
                .map(CompanyUser::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public CompanyUserDto delete(@PathVariable Long id) {
        CompanyUser companyUser = companyUserRepository.findById(id).orElse(null);
        if (companyUser != null) {
            companyUserRepository.delete(companyUser);
        }
        return companyUserMapper.toCompanyUserDto(companyUser);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        companyUserRepository.deleteAllById(ids);
    }
}
