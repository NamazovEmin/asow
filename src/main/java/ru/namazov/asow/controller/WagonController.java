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

import ru.namazov.asow.dto.WagonDTO;
import ru.namazov.asow.entity.Wagon;
import ru.namazov.asow.mapper.WagonMapper;
import ru.namazov.asow.service.WagonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Wagon", description = "CRUD operations with Wagon")
@RestController
@RequestMapping(value = "/wagon")
@AllArgsConstructor
@SecurityRequirement(name = "authenticated")
public class WagonController {

    private final WagonMapper wagonMapper;
    private final WagonService wagonService;

    @Operation(summary = "Wagon creating")
    @PostMapping
    public ResponseEntity<WagonDTO> save(@RequestBody WagonDTO wagonDTO) {
        return ResponseEntity.ok(wagonMapper.toDTO(wagonService.save(wagonMapper.toEntity(wagonDTO))));
    }

    @Operation(summary = "Wagon updating")
    @PutMapping("/{id}")
    public ResponseEntity<WagonDTO> put(@RequestBody WagonDTO wagonDTO, @PathVariable(name = "id") Long id) {
        Wagon wagon = wagonService.findById(id);
        wagon.setWagonPassport(wagonDTO.getWagonPassport());
        wagon.setSerialNumber(wagonDTO.getSerialNumber());
        wagon.setCargosList(wagonDTO.getCargosList());
        wagon.setCargosWeight(wagonDTO.getCargosWeight());
        wagon.setTrain(wagonDTO.getTrain());
        wagon.setRailway(wagonDTO.getRailway());
        return ResponseEntity.ok(wagonMapper.toDTO(wagonService.save(wagon)));
    }

    @Operation(summary = "Getting Wagon by id")
    @GetMapping("/{id}")
    public ResponseEntity<WagonDTO> findById(
            @Parameter(description = "id of Wagon to be searched")
            @PathVariable(name = "id") Long id)
    {
        return ResponseEntity.ok(wagonMapper.toDTO(wagonService.findById(id)));
    }

    @Operation(summary = "Wagon deleting")
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "id of Wagon to be deleted")
            @PathVariable Long id) {
        Wagon wagon = wagonService.findById(id);
        wagonService.delete(wagon);
    }
}
