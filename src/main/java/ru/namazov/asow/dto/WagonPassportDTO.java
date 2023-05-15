package ru.namazov.asow.dto;

import ru.namazov.asow.enums.WagonType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class WagonPassportDTO {

    private Long id;

    private Long number;

    private WagonType wagonType;

    private Long containerWeight;

    private Long carryingCapacity;
}
