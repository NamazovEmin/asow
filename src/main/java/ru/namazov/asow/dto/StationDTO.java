package ru.namazov.asow.dto;

import java.util.List;

import ru.namazov.asow.entity.Railway;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class StationDTO {

    private Long id;

    private String name;

    private List<Railway> railwaysList;
}
