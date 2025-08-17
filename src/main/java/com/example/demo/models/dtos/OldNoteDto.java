package com.example.demo.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OldNoteDto {
    private String guid;
    private String comments;
    private String clientGuid;
    private String loggedUser;
    private String createdDateTime;
    private String modifiedDateTime;
}
