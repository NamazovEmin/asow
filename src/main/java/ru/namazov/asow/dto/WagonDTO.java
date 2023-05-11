package ru.namazov.asow.dto;

import ru.namazov.asow.entity.Cargo;
import ru.namazov.asow.entity.Railway;
import ru.namazov.asow.entity.Train;
import ru.namazov.asow.entity.WagonPassport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WagonDTO {

    private WagonPassport wagonPassport;

    private Long serialNumber;

    private Cargo cargo;

    private Long cargosWeight;

    private Train train;

    private Railway railway;
}
