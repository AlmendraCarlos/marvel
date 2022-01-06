package com.challenge.albo.marvel.service;

import com.challenge.albo.marvel.dto.resultCollaborators.ResultCollaboratorsDTO;

public interface CollaboratorsService {

    ResultCollaboratorsDTO getCollaboratorsFromComic(String comic);
}
