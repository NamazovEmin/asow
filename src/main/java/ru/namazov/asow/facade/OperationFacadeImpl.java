/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.asow.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import org.springframework.stereotype.Component;

import ru.namazov.asow.entity.Operation;
import ru.namazov.asow.entity.Railway;
import ru.namazov.asow.entity.Wagon;
import ru.namazov.asow.enums.OperationType;
import ru.namazov.asow.service.OperationService;
import ru.namazov.asow.service.RailwayService;
import ru.namazov.asow.service.WagonService;

import lombok.AllArgsConstructor;
import lombok.Synchronized;

@Component
@AllArgsConstructor
public class OperationFacadeImpl implements OperationFacade {

    private final OperationService operationService;
    private final WagonService wagonService;
    private final RailwayService railwayService;
    private final Long rjd = 1L;


    @Override
    @Synchronized
    public boolean receive(List<Wagon> comingWagonList, Long railwayID) {
        Railway railwayToMove = railwayService.findById(railwayID);
        List<Wagon> movedWagonList = moveToTail(comingWagonList, railwayToMove);
        wagonService.saveAll(movedWagonList);
        List<Operation> operationList = new ArrayList<>();
        movedWagonList.forEach(i -> operationList.add(new Operation(OperationType.RECEIVE, rjd, railwayID, i.getId())));
        operationService.saveAll(operationList);
        return true;
    }

    private List<Wagon> moveToTail(List<Wagon> comingWagonList, Railway railwayToMove) {
        AtomicLong a = new AtomicLong();
        a.set(railwayToMove.getWagonList().size() - 2L);
        comingWagonList.forEach(i -> {
            a.set(a.get() + 1L);
            i.setRailway(railwayToMove);
            i.setSerialNumber(a.get());
        });
        return comingWagonList;
    }

    @Override
    public boolean move(List<Wagon> wagonList, Long railwayID) {
        try {
            ObjectMapper mapper = new JsonMapper();
            String expectedJson = mapper.writeValueAsString(wagonList.get(1));
        }  catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public boolean bringBack(List<Wagon> comingWagonList, Long railwayID) {
        Railway railwayToMove = railwayService.findById(railwayID);
        List<Wagon> loadedWagonList = moveToHead(comingWagonList, railwayToMove);
        wagonService.saveAll(loadedWagonList);
        List<Operation> operationList = new ArrayList<>();
        loadedWagonList.forEach(i -> operationList.add(new Operation(OperationType.RECEIVE, railwayID, rjd, i.getId())));
        operationService.saveAll(operationList);
        return true;
    }

    private List<Wagon> moveToHead(List<Wagon> comingWagonList, Railway railwayToMove) {
        AtomicLong a = new AtomicLong();
        comingWagonList.forEach(i -> {
            a.set(a.get() + 1L);
            i.setRailway(railwayToMove);
            i.setSerialNumber(a.get());
        });
        railwayToMove.getWagonList().forEach(i -> {
            i.setSerialNumber(i.getSerialNumber() + comingWagonList.size());
            comingWagonList.add(i);
        });
        return comingWagonList;
    }
}
