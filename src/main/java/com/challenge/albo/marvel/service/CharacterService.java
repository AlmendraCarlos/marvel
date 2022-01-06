package com.challenge.albo.marvel.service;

import com.challenge.albo.marvel.dto.resultCharacters.CharactersDTO;
import com.challenge.albo.marvel.dto.resultCharacters.CharactersResultsDTO;

public interface CharacterService {

    CharactersResultsDTO getRelationsByCharacter(String character);

}
