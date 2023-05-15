package ru.namazov.asow.dto;

import java.util.List;

import ru.namazov.asow.entity.Wagon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class TrainDTO {

    private Long id;

    private List<Wagon> wagonList;
}

