package com.example.demo.services;

import com.example.demo.models.dtos.OldClientDto;
import com.example.demo.models.dtos.OldNoteDto;
import com.example.demo.models.entities.PatientProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImportService {

    private final OldSystemClientService oldSystemClient;
    private final ClientMappingService clientMappingService;
    private final NoteSyncService noteSyncService;

    @Transactional
    public void runImport() {
        log.info("🚀 Запуск импорта заметок");

        List<OldClientDto> clients = oldSystemClient.getAllClients();
        int totalNotes = 0;
        int imported = 0;
        int skipped = 0;

        for (OldClientDto client : clients) {
            Optional<PatientProfile> patientOpt =
                    clientMappingService.findPatientByOldClientGuid(client.getGuid());

            if (patientOpt.isEmpty()) {
                skipped++;
                continue;
            }

            PatientProfile patient = patientOpt.get();
            if (!(patient.getStatusId() == 200 || patient.getStatusId() == 210 || patient.getStatusId() == 230)) {
                skipped++;
                continue;
            }

            List<OldNoteDto> notes = oldSystemClient.getNotesForClient(
                    client.getAgency(), client.getGuid(),
                    LocalDate.now().minusYears(5),
                    LocalDate.now()
            );

            for (OldNoteDto note : notes) {
                totalNotes++;
                try {
                    noteSyncService.syncNote(note, patient);
                    imported++;
                } catch (Exception e) {
                    log.error("Ошибка при импорте заметки {}: {}", note.getGuid(), e.getMessage());
                }
            }
        }

        log.info("✅ Импорт завершён. Всего заметок: {}, импортировано: {}, пропущено: {}",
                totalNotes, imported, skipped);
    }
}

