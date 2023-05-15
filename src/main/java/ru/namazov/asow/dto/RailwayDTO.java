package ru.namazov.asow.dto;

import java.util.List;

import ru.namazov.asow.entity.Station;
import ru.namazov.asow.entity.Wagon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class RailwayDTO {

    private Long id;

    private Long number;

    private Station station;

    private List<Wagon> wagonList;
}
