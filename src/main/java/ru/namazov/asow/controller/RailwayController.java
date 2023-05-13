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

import ru.namazov.asow.dto.RailwayDTO;
import ru.namazov.asow.entity.Cargo;
import ru.namazov.asow.entity.Railway;
import ru.namazov.asow.mapper.RailwayMapper;
import ru.namazov.asow.service.RailwayService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(name = "/railway")
@AllArgsConstructor
// TODO: 13.05.2023 программа игнорирует /railway
public class RailwayController {

    private final RailwayMapper railwayMapper;
    private final RailwayService railwayService;

    @PostMapping
    public ResponseEntity<RailwayDTO> save(@RequestBody RailwayDTO railwayDTO) {
        return ResponseEntity.ok(railwayMapper.toDTO(railwayService.save(railwayMapper.toEntity(railwayDTO))));
    }

    @PutMapping
    public ResponseEntity<RailwayDTO> put(@RequestBody RailwayDTO railwayDTO) {
        Cargo cargo = railwayService.findCargoByCode(cargoDTO.getCode());
        cargo.setCode(cargoDTO.getCode());
        cargo.setName(cargoDTO.getName());
        return ResponseEntity.ok(railwayMapper.toDTO(railwayService.update(cargo)));
        // TODO: 14.05.2023 как получать без id?
    }

    @GetMapping("/{id}")
    public ResponseEntity<RailwayDTO> getByID(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(railwayMapper.toDTO(railwayService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Railway railway = railwayService.findById(id);
        railwayService.delete(railway);
    }
}
