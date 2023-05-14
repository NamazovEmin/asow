/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.asow.facade;

import java.util.List;

import ru.namazov.asow.entity.Wagon;
import ru.namazov.asow.enums.Position;

public interface OperationFacade {

    boolean receive(List<Wagon> wagonList, Long railway);

    boolean move(List<Wagon> wagonList, Long railway, Position position);

    boolean bringBack(List<Wagon> wagonList, Long railway);
}
