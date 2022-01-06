package com.challenge.albo.marvel.service.impl;

import com.challenge.albo.marvel.dto.ResultsDTO;
import com.challenge.albo.marvel.dto.ComicDTO;
import com.challenge.albo.marvel.dto.resultCollaborators.ResultCollaboratorsDTO;
import com.challenge.albo.marvel.feing.Comics;
import com.challenge.albo.marvel.service.CollaboratorsService;
import com.challenge.albo.marvel.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollaboratorsServiceImpl implements CollaboratorsService {

    @Autowired
    private Comics comics;
    @Autowired
    private MapperUtil mapper;
    private static final Logger log = LoggerFactory.getLogger(CollaboratorsServiceImpl.class);

    @Override
    public ResultCollaboratorsDTO getCollaboratorsFromComic(String comic) {
        log.info("inicio busqueda de comics en collaboratorsServiceImpl()");
        try {
            ComicDTO comicResult = comics.getComics();
            List<ResultsDTO> data = comicResult.getData().getResults();
            ResultsDTO filtered = data
                    .stream()
                    .filter(p -> p.getTitle().equalsIgnoreCase(comic))
                    .findAny()
                    .orElse(null);

            if(data != null){
                return mapper.resultsToResponse(filtered.getCreators());
            }else{
                return null;
            }
        }catch (Exception e){
            log.error("error al hacer consulta de comics, posible causa: {}", e.getMessage());
            return null;
        }

    }
}
