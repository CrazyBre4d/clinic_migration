package com.example.demo.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class OldClientDto {
    private String agency;
    private String guid;
    private String firstName;
    private String lastName;
    private String status;
    private String dob;
    private String createdDateTime;
    private Set<OldNoteDto> oldNotes;
}
