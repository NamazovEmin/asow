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

public interface OperationFacade {

    boolean receive(List<Wagon> wagonList, Long railway);

    boolean move(List<Wagon> wagonList, Long railway);

    boolean bringBack(List<Wagon> wagonList, Long railway);
}
