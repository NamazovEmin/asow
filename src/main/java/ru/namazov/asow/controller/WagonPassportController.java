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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/wagonpassport")
@AllArgsConstructor
@SecurityRequirement(name = "authenticated")
public class WagonPassportController {

    private final WagonPassportMapper wagonPassportMapper;
    private final WagonPassportService wagonPassportService;

    @PostMapping
    public ResponseEntity<WagonPassportDTO> save(@RequestBody WagonPassportDTO wagonPassportDTO) {
        return ResponseEntity.ok(wagonPassportMapper.toDTO(wagonPassportService.save(wagonPassportMapper.toEntity(wagonPassportDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WagonPassportDTO> put(@RequestBody WagonPassportDTO wagonPassportDTO, @PathVariable(name = "id") Long id) {
        WagonPassport wagonPassport = wagonPassportService.findById(id);
        wagonPassport.setNumber(wagonPassportDTO.getNumber());
        wagonPassport.setWagonType(wagonPassportDTO.getWagonType());
        wagonPassport.setCarryingCapacity(wagonPassportDTO.getCarryingCapacity());
        wagonPassport.setContainerWeight(wagonPassportDTO.getContainerWeight());
        return ResponseEntity.ok(wagonPassportMapper.toDTO(wagonPassportService.save(wagonPassport)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WagonPassportDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(wagonPassportMapper.toDTO(wagonPassportService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        WagonPassport wagonPassport = wagonPassportService.findById(id);
        wagonPassportService.delete(wagonPassport);
    }
}
