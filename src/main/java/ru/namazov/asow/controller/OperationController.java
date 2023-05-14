package ru.namazov.asow.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.namazov.asow.dto.WagonDTO;
import ru.namazov.asow.facade.OperationFacade;
import ru.namazov.asow.mapper.WagonMapper;
import ru.namazov.asow.response.Successful;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/operation")
@SecurityRequirement(name = "authenticated")

public class OperationController {

    private final OperationFacade operationFacade;
    private final WagonMapper wagonMapper;


    @PostMapping("/receive")
    public ResponseEntity<Successful> receiveWagons(@RequestBody List<WagonDTO> wagonDTOList, @RequestParam Long railway) {
        return ResponseEntity.ok(new Successful(operationFacade.receive(wagonMapper.toEntity(wagonDTOList), railway)));
    }

    @PostMapping("/move")
    public ResponseEntity<Successful> moveWagons(@RequestBody List<WagonDTO> wagonDTOList, @RequestParam Long railway, Integer po) {
        // TODO: 12.05.2023 нужно получить переменную указывающую куда переместить вагоны, в голову или хвост
        return ResponseEntity.ok(new Successful(operationFacade.move(wagonMapper.toEntity(wagonDTOList), railway)));
    }

    @PostMapping("/return")
    public ResponseEntity<Successful> returnWagons(@RequestBody List<WagonDTO> wagonDTOList, @RequestParam Long railway) {
        return ResponseEntity.ok(new Successful(operationFacade.bringBack(wagonMapper.toEntity(wagonDTOList), railway)));
    }
}
