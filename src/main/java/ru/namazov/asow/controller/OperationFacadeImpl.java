package ru.namazov.asow.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import ru.namazov.asow.entity.Railway;
import ru.namazov.asow.entity.Wagon;
import ru.namazov.asow.service.OperationService;
import ru.namazov.asow.service.WagonService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OperationFacadeImpl implements OperationFacade {

    private final OperationService operationService;
    private final WagonService wagonService;


    @Override
    public void receive(List<Wagon> wagonList, Railway railway) {

    }

    @Override
    public void move(List<Wagon> wagonList, Railway railway) {

    }

    @Override
    public void bringBack(List<Wagon> wagonList, Railway railway) {

    }
}
