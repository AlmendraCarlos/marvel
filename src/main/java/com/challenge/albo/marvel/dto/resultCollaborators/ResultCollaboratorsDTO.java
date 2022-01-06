package com.challenge.albo.marvel.dto.resultCollaborators;

import lombok.Data;

import java.util.List;

@Data
public class ResultCollaboratorsDTO {

    private String last_sync;
    private List<String> editors;
    private List<String> writers;
    private List<String> colorist;

}
