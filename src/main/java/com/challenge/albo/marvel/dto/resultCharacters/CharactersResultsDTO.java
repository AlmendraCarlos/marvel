package com.challenge.albo.marvel.dto.resultCharacters;

import com.challenge.albo.marvel.dto.ResultsDTO;
import lombok.Data;

import java.util.List;

@Data
public class CharactersResultsDTO {

    private String last_sync;
    private List<CharactersDTO> characters;

}
