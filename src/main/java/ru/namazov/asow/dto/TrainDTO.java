package ru.namazov.asow.dto;

import java.util.ArrayList;
import java.util.List;

import ru.namazov.asow.entity.Wagon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class TrainDTO {

    private List<Wagon> wagonList = new ArrayList<>();
}

