package com.challenge.albo.marvel.controller;

import com.challenge.albo.marvel.dto.resultCollaborators.ResponseDTO;
import com.challenge.albo.marvel.dto.resultCollaborators.ResultCollaboratorsDTO;
import com.challenge.albo.marvel.service.CollaboratorsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marvel/colaborators")
@RequiredArgsConstructor
public class CollaboratorsController {

    @Autowired
    private CollaboratorsService collaboratorsService;
    private static final Logger log = LoggerFactory.getLogger(CollaboratorsController.class);

    @GetMapping(value="/{comic}")
    public ResponseEntity<?> getColaborators(@PathVariable String comic){

        log.info("inicio controlador getCollaborators con comic {}", comic);
        ResponseEntity<?> response;
        ResultCollaboratorsDTO result = this.collaboratorsService.getCollaboratorsFromComic(comic);
        if(result != null){
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            ResponseDTO responseNoOk = new ResponseDTO();
            responseNoOk.setMsg("No se encontro el comic en lista disponible");
            response = new ResponseEntity<>(responseNoOk, HttpStatus.NOT_FOUND);
        }

        return response;

    }
}
