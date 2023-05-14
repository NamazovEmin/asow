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
import ru.namazov.asow.entity.Railway;
import ru.namazov.asow.mapper.RailwayMapper;
import ru.namazov.asow.service.RailwayService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/railway")
@AllArgsConstructor
@SecurityRequirement(name = "authenticated")
public class RailwayController {

    private final RailwayMapper cargoMapper;
    private final RailwayService cargoService;

    @PostMapping
    public ResponseEntity<RailwayDTO> save(@RequestBody RailwayDTO railwayDTO) {
        return ResponseEntity.ok(cargoMapper.toDTO(cargoService.save(cargoMapper.toEntity(railwayDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RailwayDTO> put(@RequestBody RailwayDTO railwayDTO, @PathVariable(name = "id") Long id) {
        Railway railway = cargoService.findById(id);
        railway.setNumber(railwayDTO.getNumber());
        return ResponseEntity.ok(cargoMapper.toDTO(cargoService.save(railway)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RailwayDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(cargoMapper.toDTO(cargoService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Railway railway = cargoService.findById(id);
        cargoService.delete(railway);
    }
}
