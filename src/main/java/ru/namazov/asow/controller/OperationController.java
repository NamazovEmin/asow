package ru.namazov.asow.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.namazov.asow.dto.OperationDTO;
import ru.namazov.asow.dto.WagonDTO;
import ru.namazov.asow.entity.Operation;
import ru.namazov.asow.enums.Position;
import ru.namazov.asow.facade.OperationFacade;
import ru.namazov.asow.mapper.OperationMapper;
import ru.namazov.asow.mapper.WagonMapper;
import ru.namazov.asow.service.OperationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Operation", description = "CRUD operations with Operation")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/operation")
@SecurityRequirement(name = "authenticated")

public class OperationController {

    private final OperationFacade operationFacade;
    private final WagonMapper wagonMapper;
    private final OperationMapper operationMapper;
    private final OperationService operationService;

    @PostMapping("/receive")
    public ResponseEntity<List<OperationDTO>> receiveWagons(@RequestBody List<WagonDTO> wagonDTOList, @RequestParam Long railway) {
        return ResponseEntity.ok(operationMapper.toDTO(operationFacade.receive(wagonMapper.toEntity(wagonDTOList), railway)));
    }

    @PostMapping("/move")
    public ResponseEntity<List<OperationDTO>> moveWagons(@RequestBody List<WagonDTO> wagonDTOList, @RequestParam Long railway, @RequestBody Position position) {
        return ResponseEntity.ok(operationMapper.toDTO(operationFacade.move(wagonMapper.toEntity(wagonDTOList), railway, position)));
    }

    @PostMapping("/return")
    public ResponseEntity<List<OperationDTO>> returnWagons(@RequestBody List<WagonDTO> wagonDTOList, @RequestParam Long railway) {
        return ResponseEntity.ok(operationMapper.toDTO(operationFacade.bringBack(wagonMapper.toEntity(wagonDTOList), railway)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(operationMapper.toDTO(operationService.findById(id)));
    }

    @PutMapping
    public ResponseEntity<OperationDTO> put(@RequestBody OperationDTO operationDTO) {
        return ResponseEntity.ok(operationMapper.toDTO(operationService.put(operationMapper.toEntity(operationDTO))));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        Operation operation = operationService.findById(id);
        operationService.delete(operation);
    }

}
