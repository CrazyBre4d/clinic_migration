package com.example.demo.services;

import com.example.demo.models.dtos.OldClientDto;
import com.example.demo.models.dtos.OldNoteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OldSystemClientService {

    private final RestTemplate restTemplate;

    @Value("${old-system.base-url}")
    private String baseUrl;

    /**
     * Получение всех клиентов из Старой системы
     */
    public List<OldClientDto> getAllClients() {
        String url = baseUrl + "/clients";
        ResponseEntity<OldClientDto[]> response = restTemplate.postForEntity(url, null, OldClientDto[].class);
        return response.getBody() != null ? Arrays.asList(response.getBody()) : Collections.emptyList();
    }

    /**
     * Получение заметок клиента по guid + agency
     */
    public List<OldNoteDto> getNotesForClient(String clientGuid, String agency, LocalDate from, LocalDate to) {
        String url = baseUrl + "/notes";

        Map<String, Object> payload = new HashMap<>();
        payload.put("agency", agency);
        payload.put("clientGuid", clientGuid);
        payload.put("dateFrom", from.toString());
        payload.put("dateTo", to.toString());

        ResponseEntity<OldNoteDto[]> response = restTemplate.postForEntity(url, payload, OldNoteDto[].class);
        return response.getBody() != null ? Arrays.asList(response.getBody()) : Collections.emptyList();
    }
}
