package ru.namazov.asow.dto;

import java.util.List;

import ru.namazov.asow.entity.Wagon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class OrderDTO {

    private Long id;

    private Long code;

    private List<Wagon> wagonList;
}

