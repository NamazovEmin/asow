package ru.namazov.asow.controller;

import java.util.List;

import ru.namazov.asow.entity.Railway;
import ru.namazov.asow.entity.Wagon;

public interface OperationFacade {

    void receive(List<Wagon> wagonList, Railway railway);

    void move(List<Wagon> wagonList, Railway railway);

    void bringBack(List<Wagon> wagonList, Railway railway);
}
