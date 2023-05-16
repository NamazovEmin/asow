package ru.namazov.asow.dto;

import java.util.List;

import ru.namazov.asow.entity.Cargo;
import ru.namazov.asow.entity.Order;
import ru.namazov.asow.entity.Railway;
import ru.namazov.asow.entity.WagonPassport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WagonDTO {

    private WagonPassport wagonPassport;

    private Long positionNumber;

    private List<Cargo> cargoList;

    private Long totalCargoWeight;

    private List<Order> orderList;

    private Railway railway;
}
