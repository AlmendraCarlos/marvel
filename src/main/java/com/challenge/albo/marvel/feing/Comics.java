package com.challenge.albo.marvel.feing;

import com.challenge.albo.marvel.dto.ComicDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Comics", url = "${rest.endpoints.comics.marvel.url}/v1")
public interface Comics {

    @GetMapping(value="/public/comics?ts=1&apikey=4154aaa4ff859093eb1c9f7912c94439&hash=3d350c6c762d4cc25f16d0e054f10b5d")
    ComicDTO getComics();

    @GetMapping(value="/public/characters?ts=1&apikey=4154aaa4ff859093eb1c9f7912c94439&hash=3d350c6c762d4cc25f16d0e054f10b5d")
    ComicDTO getCharacters();

    @GetMapping(value="/public/characters/{characterId}/comics?ts=1&apikey=4154aaa4ff859093eb1c9f7912c94439&hash=3d350c6c762d4cc25f16d0e054f10b5d")
    ComicDTO getComicsByCharacterId(@PathVariable String characterId);

    @GetMapping(value="/public/comics/{comicId}/characters?ts=1&apikey=4154aaa4ff859093eb1c9f7912c94439&hash=3d350c6c762d4cc25f16d0e054f10b5d")
    ComicDTO getCharactersByComicId(@PathVariable String comicId);

}
