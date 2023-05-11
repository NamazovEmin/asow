package ru.namazov.asow.controller;

import java.util.List;

import ru.namazov.asow.entity.Wagon;

public interface OperationFacade {

    boolean receive(List<Wagon> wagonList, Long railway);

    boolean move(List<Wagon> wagonList, Long railway);

    boolean bringBack(List<Wagon> wagonList, Long railway);
}
