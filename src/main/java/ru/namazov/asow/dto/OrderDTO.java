package ru.namazov.asow.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class OrderDTO {

    private Long id;

    private Long code;

    private List<WagonDTO> wagonList;
}

