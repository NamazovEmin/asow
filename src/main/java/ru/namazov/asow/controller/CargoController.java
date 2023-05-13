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

import ru.namazov.asow.dto.CargoDTO;
import ru.namazov.asow.entity.Cargo;
import ru.namazov.asow.mapper.CargoMapper;
import ru.namazov.asow.service.CargoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(name = "/cargo")
@AllArgsConstructor
// TODO: 13.05.2023 программа игнорирует /cargo
public class CargoController {

    private final CargoMapper cargoMapper;
    private final CargoService cargoService;

    @PostMapping
    public ResponseEntity<CargoDTO> save(@RequestBody CargoDTO cargoDTO) {
        return ResponseEntity.ok(cargoMapper.toDTO(cargoService.save(cargoMapper.toEntity(cargoDTO))));
    }

    @PutMapping
    public ResponseEntity<CargoDTO> put(@RequestBody CargoDTO cargoDTO) {
        Cargo cargo = cargoService.findCargoByCode(cargoDTO.getCode());
        cargo.setCode(cargoDTO.getCode());
        cargo.setName(cargoDTO.getName());
        return ResponseEntity.ok(cargoMapper.toDTO(cargoService.put(cargo)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoDTO> getByCode(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(cargoMapper.toDTO(cargoService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Cargo cargo = cargoService.findById(id);
        cargoService.delete(cargo);
    }
}
