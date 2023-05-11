package ru.namazov.asow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.namazov.asow.entity.Railway;
import ru.namazov.asow.entity.Wagon;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(name = "/operation")
public class OperationController {

    private final OperationFacade operationFacade;

    @PostMapping("/receive")
    public void receiveWagon(List<Wagon> wagonList, Railway railway) {
        operationFacade.receive(wagonList, railway);
    }

    @PostMapping("/move")
    public void moveWagon(List<Wagon> wagonList, Railway railway) {
        operationFacade.move(wagonList, railway);
    }

    @PostMapping("/return")
    public void returnWagon(List<Wagon> wagonList, Railway railway) {
        operationFacade.bringBack(wagonList, railway);
    }

}
