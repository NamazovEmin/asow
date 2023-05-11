package ru.namazov.asow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.namazov.asow.dto.CargoDTO;
import ru.namazov.asow.entity.Cargo;
import ru.namazov.asow.mapper.CargoMapper;
import ru.namazov.asow.response.CheckDelete;
import ru.namazov.asow.service.CargoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(name = "/cargo")
public class CargoController {

    private final CargoMapper cargoMapper;
    private final CargoService cargoService;

    @PostMapping
    public ResponseEntity<CargoDTO> save(@RequestParam("cargoDTO") CargoDTO cargoDTO) {
        return ResponseEntity.ok(cargoMapper.toDTO(cargoService.save(cargoMapper.toEntity(cargoDTO))));
    }

    @PutMapping
    public ResponseEntity<CargoDTO> put(@RequestParam("cargoDTO") CargoDTO cargoDTO) {
        Cargo cargo = cargoService.findCargoByCode(cargoDTO.getCode());
        cargo.setCode(cargoDTO.getCode());
        cargo.setName(cargoDTO.getName());
        return ResponseEntity.ok(cargoMapper.toDTO(cargoService.update(cargo)));
        /// TODO: 11.05.2023 по какому параметру найти обект, который нужно обновить?
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoDTO> getByCode(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(cargoMapper.toDTO(cargoService.findById(id)));
        /// TODO: 11.05.2023 Обычно получают по ID, но врятли он есть на фронте....
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CheckDelete> delete(@PathVariable Long id) {
        Cargo cargo = cargoService.findById(id);
        cargoService.delete(cargo);
        return ResponseEntity.ok(new CheckDelete(true));
        /// TODO: 11.05.2023 Обычно по ID, но откуда у фронта id? если в ДТО id не передают
    }
}
