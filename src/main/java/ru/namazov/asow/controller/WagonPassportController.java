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

import ru.namazov.asow.dto.WagonPassportDTO;
import ru.namazov.asow.entity.WagonPassport;
import ru.namazov.asow.mapper.WagonPassportMapper;
import ru.namazov.asow.service.WagonPassportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "WagonPassport", description = "CRUD operations with WagonPassport")
@RestController
@RequestMapping(value = "/wagonpassport")
@AllArgsConstructor
@SecurityRequirement(name = "authenticated")
public class WagonPassportController {

    private final WagonPassportMapper wagonPassportMapper;
    private final WagonPassportService wagonPassportService;

    @Operation(summary = "WagonPassport creating")
    @PostMapping
    public ResponseEntity<WagonPassportDTO> save(@RequestBody WagonPassportDTO wagonPassportDTO) {
        return ResponseEntity.ok(wagonPassportMapper.toDTO(wagonPassportService.save(wagonPassportMapper.toEntity(wagonPassportDTO))));
    }

    @Operation(summary = "WagonPassport updating")
    @PutMapping("/{id}")
    public ResponseEntity<WagonPassportDTO> put(@RequestBody WagonPassportDTO wagonPassportDTO, @PathVariable(name = "id") Long id) {
        WagonPassport wagonPassport = wagonPassportService.findById(id);
        wagonPassport.setNumber(wagonPassportDTO.getNumber());
        wagonPassport.setWagonType(wagonPassportDTO.getWagonType());
        wagonPassport.setCarryingCapacity(wagonPassportDTO.getCarryingCapacity());
        wagonPassport.setContainerWeight(wagonPassportDTO.getContainerWeight());
        return ResponseEntity.ok(wagonPassportMapper.toDTO(wagonPassportService.save(wagonPassport)));
    }

    @Operation(summary = "Getting WagonPassport by id")
    @GetMapping("/{id}")
    public ResponseEntity<WagonPassportDTO> findById(
            @Parameter(description = "id of WagonPassport to be searched")
            @PathVariable(name = "id") Long id)
    {
        return ResponseEntity.ok(wagonPassportMapper.toDTO(wagonPassportService.findById(id)));
    }

    @Operation(summary = "WagonPassport deleting")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        WagonPassport wagonPassport = wagonPassportService.findById(id);
        wagonPassportService.delete(wagonPassport);
    }
}
