package ru.namazov.asow.facade;

import java.util.List;

import ru.namazov.asow.entity.Operation;
import ru.namazov.asow.entity.Wagon;
import ru.namazov.asow.enums.Position;

public interface OperationFacade {

    List<Operation> receive(List<Wagon> wagonList, Long railway);

    List<Operation> move(List<Wagon> wagonList, Long railway, Position position);

    List<Operation> bringBack(List<Wagon> wagonList, Long railway);
}
