package com.example.demo.services;

import com.example.demo.models.dtos.OldNoteDto;
import com.example.demo.models.entities.CompanyUser;
import com.example.demo.models.entities.PatientNote;
import com.example.demo.models.entities.PatientProfile;
import com.example.demo.repositories.PatientNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteSyncService {

    private final PatientNoteRepository patientNoteRepository;
    private final UserMappingService userMappingService;

    private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Transactional
    public void syncNote(OldNoteDto oldNote, PatientProfile patient) {
        Optional<PatientNote> existing = patientNoteRepository.findById(Long.valueOf(oldNote.getGuid()));

        LocalDate created = LocalDate.parse(oldNote.getCreatedDateTime(), FORMAT);
        LocalDate modified = LocalDate.parse(oldNote.getModifiedDateTime(), FORMAT);

        CompanyUser user = userMappingService.getOrCreateUser(oldNote.getLoggedUser());

        if (existing.isEmpty()) {
            PatientNote note = new PatientNote();
            note.setId(Long.valueOf(oldNote.getGuid()));
            note.setCreatedDateTime(created);
            note.setLastModifiedDateTime(modified);
            note.setCreatedByUser(user);
            note.setLastModifiedByUser(user);
            note.setPatient(patient);
            note.setNote(oldNote.getComments());
            patientNoteRepository.save(note);
        } else {
            PatientNote note = existing.get();
            if (modified.isAfter(note.getLastModifiedDateTime())) {
                note.setNote(oldNote.getComments());
                note.setLastModifiedDateTime(modified);
                note.setLastModifiedByUser(user);
                patientNoteRepository.save(note);
            }
        }
    }
}
