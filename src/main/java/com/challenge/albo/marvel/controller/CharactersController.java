package com.challenge.albo.marvel.controller;

import com.challenge.albo.marvel.dto.resultCharacters.CharactersDTO;
import com.challenge.albo.marvel.dto.resultCharacters.CharactersResultsDTO;
import com.challenge.albo.marvel.dto.resultCollaborators.ResponseDTO;
import com.challenge.albo.marvel.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marvel/character")
@RequiredArgsConstructor
public class CharactersController {

    @Autowired
    private CharacterService characterService;
    private static final Logger log = LoggerFactory.getLogger(CollaboratorsController.class);

    @GetMapping(value="/{character}")
    public ResponseEntity<?> getRelations(@PathVariable String character){

        log.info("inicio controlador getRelations con comic {}", character);
        ResponseEntity<?> response;
        CharactersResultsDTO result = this.characterService.getRelationsByCharacter(character);
        if(result != null){
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            ResponseDTO responseNoOk = new ResponseDTO();
            responseNoOk.setMsg("No se encontro el comic en lista disponible o hubo un error al realizar llamado");
            response = new ResponseEntity<>(responseNoOk, HttpStatus.NOT_FOUND);
        }

        return response;

    }


}
