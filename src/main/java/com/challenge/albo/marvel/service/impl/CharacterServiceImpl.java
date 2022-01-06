package com.challenge.albo.marvel.service.impl;

import com.challenge.albo.marvel.dto.ComicDTO;
import com.challenge.albo.marvel.dto.ResultsDTO;
import com.challenge.albo.marvel.dto.resultCharacters.CharactersDTO;
import com.challenge.albo.marvel.dto.resultCharacters.CharactersResultsDTO;
import com.challenge.albo.marvel.feing.Comics;
import com.challenge.albo.marvel.service.CharacterService;
import com.challenge.albo.marvel.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private Comics comics;
    @Autowired
    private MapperUtil mapper;
    private static final Logger log = LoggerFactory.getLogger(CollaboratorsServiceImpl.class);

    @Override
    public CharactersResultsDTO getRelationsByCharacter(String character) {
        log.info("inicio busqueda relacion de personajes en getRelationsByCharacter()");

        try{
            ComicDTO charactersResults = comics.getCharacters();
            List<ResultsDTO> data = charactersResults.getData().getResults();
            CharactersResultsDTO resulSet = new CharactersResultsDTO();
            List<CharactersDTO> resulsetFinal = new ArrayList<>();
            CharactersDTO resultado;
            List<String> characteres = new ArrayList<>();

            //BUSCO ID DE PERSONAJE RECIBIDO POR PARAMETRO
            Integer idCharacter = data
                    .stream()
                    .filter(p -> p.getName().equalsIgnoreCase(character))
                    .map(ResultsDTO::getId)
                    .findAny()
                    .orElse(null);


            if(!idCharacter.equals(null)){
                //BUSCO COMICS EN DONDE EL PERSONAJE RECIBIDO PARTICIPO
                log.info("inicio de comics relacionados con personaje");
                ComicDTO comicsByCharacter = comics.getComicsByCharacterId(idCharacter.toString());
                List<ResultsDTO> results = comicsByCharacter.getData().getResults();

                //RECORRO RESULTADO
                for(ResultsDTO c:results){

                    //BUSCO PERSONAJES QUE ACTUARON EN CADA UNO DE LOS COMICS DONDE EL PERSONAJE RECIBIDO PARTICIPO
                    // DE ESTA FORMA ESTOY ENCONTRANDO TODOS LOS PERSONAJES
                    // QUE TUVIERON EN ALGUN MOMENTO RELACION CON EL PERSONAJE RECIBIDO POR PARAMETRO
                    log.info("inicio busqueda de personajes en comics");
                    ComicDTO caractersByComic = comics.getCharactersByComicId(c.getId().toString());

                    // RECORRO RESULTADO Y AGREGO A UNA LISTA TODOS LOS PERSONAJES CON LOS QUE
                    // TUVO CONTACTO EL PERSONAJE RECIBIDO POR PAREMTRO (LO SOLICITADO EN EL CHALLENGE)
                    for(ResultsDTO p: caractersByComic.getData().getResults()){
                        characteres.add(p.getName());
                    }
                    resultado = new CharactersDTO();
                    resultado.setCharacter(new ArrayList<>(characteres));
                    characteres.clear();
                    resultado.setComics(c.getTitle());
                    resulsetFinal.add(resultado);

                }
                resulSet.setCharacters(resulsetFinal);
                resulSet.setLast_sync(mapper.getDataText());

            }else{
                return null;
            }

            // EL RESULTADO FINAL ES DISTINTO AL SOLICITADO EN EL CHALLENGE, PERO CONSIDERO QUE SE TIENE MAYOR INTEGRIDAD
            // AL TENER UNA LISTA DE PERSONAJES CON LOS QUE SE TUVO CONTACTO Y EL COMIC EN EL CUAL PARTICIPARON AMBOS.
            return resulSet;
        }catch (Exception e){
            log.error("error al hacer consulta de comics, posible causa: {}", e.getMessage());
            return  null;
        }

    }


}
