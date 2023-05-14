package ru.namazov.asow.facade;

import java.util.List;

import ru.namazov.asow.entity.Wagon;
import ru.namazov.asow.enums.Position;

public interface OperationFacade {

    boolean receive(List<Wagon> wagonList, Long railway);

    boolean move(List<Wagon> wagonList, Long railway, Position position);

    boolean bringBack(List<Wagon> wagonList, Long railway);
}
