package com.challenge.albo.marvel.utils;

import com.challenge.albo.marvel.dto.CreatorsDTO;
import com.challenge.albo.marvel.dto.ItemsDTO;
import com.challenge.albo.marvel.dto.resultCollaborators.ResultCollaboratorsDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperUtil {

    private final String DATA_TEXT = "Fecha de la última sincronización en ";
    private static final Logger log = LoggerFactory.getLogger(MapperUtil.class);

    public ResultCollaboratorsDTO resultsToResponse(CreatorsDTO creators){
        log.info("inicio mapeo de respuesta");
        ResultCollaboratorsDTO response = new ResultCollaboratorsDTO();

        List<ItemsDTO> creatorsResult = creators.getItems();

        List<String> colorist =  creatorsResult.stream()
                .filter(p -> p.getRole().equals("colorist"))
                .map(ItemsDTO:: getName)
                .collect(Collectors.toList());

        response.setColorist(colorist);

        List<String> writers =  creatorsResult.stream()
                .filter(p -> p.getRole().equals("writer"))
                .map(ItemsDTO:: getName)
                .collect(Collectors.toList());

        response.setWriters(writers);

        List<String> editors =  creatorsResult.stream()
                .filter(p -> p.getRole().equals("editor"))
                .map(ItemsDTO:: getName)
                .collect(Collectors.toList());

        response.setEditors(editors);
        response.setLast_sync(getDataText());

        log.info("fin mapeo de respuesta {}", response);
        return response;
    }

    public String getDataText(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return DATA_TEXT+dtf.format(now);
    }

}
