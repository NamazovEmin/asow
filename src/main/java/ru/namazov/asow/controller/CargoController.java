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
import ru.namazov.asow.mapper.CargoMapper;
import ru.namazov.asow.service.CargoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Cargo", description = "CRUD operations with Cargo")
@RestController
@RequestMapping(value = "/cargo")
@AllArgsConstructor
@SecurityRequirement(name = "authenticated")
public class CargoController {

    private final CargoMapper cargoMapper;
    private final CargoService cargoService;

    @Operation(summary = "Cargo creating")
    @PostMapping
    public ResponseEntity<CargoDTO> save(@RequestBody CargoDTO cargoDTO) {
        return ResponseEntity.ok(cargoMapper.toDTO(cargoService.save(cargoMapper.toEntity(cargoDTO))));
    }

    @Operation(summary = "Cargo updating")
    @PutMapping
    public ResponseEntity<CargoDTO> put(@RequestBody CargoDTO cargoDTO) {
        return ResponseEntity.ok(cargoMapper.toDTO(cargoService.put(cargoMapper.toEntity(cargoDTO))));
    }

    @Operation(summary = "Getting Cargo by id")
    @GetMapping("/{id}")
    public ResponseEntity<CargoDTO> findById(
            @Parameter(description = "id of Cargo to be searched")
            @PathVariable(name = "id") Long id)
    {
        return ResponseEntity.ok(cargoMapper.toDTO(cargoService.findById(id)));
    }

    @Operation(summary = "Cargo deleting")
    @DeleteMapping("/{id}")
    public void deleteById(
            @Parameter(description = "id of Cargo to be deleted")
            @PathVariable Long id)
    {
        cargoService.deleteById(id);
    }
}
