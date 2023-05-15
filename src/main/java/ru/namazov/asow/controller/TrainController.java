package ru.namazov.asow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.namazov.asow.dto.TrainDTO;
import ru.namazov.asow.entity.Train;
import ru.namazov.asow.mapper.TrainMapper;
import ru.namazov.asow.service.TrainService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Train", description = "CRUD operations with Train")
@RestController
@RequestMapping(value = "/train")
@AllArgsConstructor
@SecurityRequirement(name = "authenticated")
public class TrainController {

    private final TrainMapper trainMapper;
    private final TrainService trainService;

    @Operation(summary = "Train creating")
    @PostMapping
    public ResponseEntity<TrainDTO> save(@RequestBody TrainDTO trainDTO) {
        return ResponseEntity.ok(trainMapper.toDTO(trainService.save(trainMapper.toEntity(trainDTO))));
    }

    @Operation(summary = "Train updating")
    @PutMapping("/{id}")
    public ResponseEntity<TrainDTO> put(@RequestBody TrainDTO trainDTO, @PathVariable(name = "id") Long id) {
        Train train = trainService.findById(id);
        train.setWagonList(trainDTO.getWagonList());
        return ResponseEntity.ok(trainMapper.toDTO(trainService.save(train)));
    }

    @Operation(summary = "Getting Train by id")
    @GetMapping("/{id}")
    public ResponseEntity<TrainDTO> findById(
            @Parameter(description = "id of Train to be searched")
            @PathVariable(name = "id") Long id)
    {
        return ResponseEntity.ok(trainMapper.toDTO(trainService.findById(id)));
    }

    @Operation(summary = "Train deleting")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Train train = trainService.findById(id);
        trainService.delete(train);
    }
}
