package ru.namazov.asow.dto;

import ru.namazov.asow.enums.OperationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class OperationDTO {

    private Long id;

    private OperationType type;

    private Long fromRailwayID;

    private Long whereRailwayID;

    private String wagon;
}
