package ru.namazov.asow.dto;

import ru.namazov.asow.entity.Station;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class RailwayDTO {

    private Long number;

    private Station station;
}
