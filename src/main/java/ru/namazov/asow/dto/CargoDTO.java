package ru.namazov.asow.dto;

import java.util.List;

import ru.namazov.asow.entity.Wagon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class CargoDTO {

    private Long id;

    private Long code;

    private String name;

    private List<Wagon> wagonList;
}

