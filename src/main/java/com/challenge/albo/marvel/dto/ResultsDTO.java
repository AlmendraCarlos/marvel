package com.challenge.albo.marvel.dto;

import lombok.Data;

@Data
public class ResultsDTO {

    private Integer id;
    private String title;
    private CreatorsDTO creators;
    private String name;
    private ComicsListDTO comics;

}
