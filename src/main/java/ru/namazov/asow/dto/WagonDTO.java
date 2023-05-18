package ru.namazov.asow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WagonDTO {

    private Long id;

    private WagonPassportDTO wagonPassport;

    private Long positionNumber;

    private Long totalCargoWeight;
}
