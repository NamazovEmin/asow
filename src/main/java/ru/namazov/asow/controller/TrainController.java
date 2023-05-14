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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/train")
@AllArgsConstructor
@SecurityRequirement(name = "authenticated")
public class TrainController {

    private final TrainMapper trainMapper;
    private final TrainService trainService;

    @PostMapping
    public ResponseEntity<TrainDTO> save(@RequestBody TrainDTO trainDTO) {
        return ResponseEntity.ok(trainMapper.toDTO(trainService.save(trainMapper.toEntity(trainDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainDTO> put(@RequestBody TrainDTO trainDTO, @PathVariable(name = "id") Long id) {
        Train train = trainService.findById(id);
        train.setWagonList(trainDTO.getWagonList());
        return ResponseEntity.ok(trainMapper.toDTO(trainService.save(train)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(trainMapper.toDTO(trainService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Train train = trainService.findById(id);
        trainService.delete(train);
    }
}
