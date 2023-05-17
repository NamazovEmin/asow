package ru.namazov.asow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class RailwayDTO {

    private Long id;

    private Long number;

    private StationDTO station;
}
