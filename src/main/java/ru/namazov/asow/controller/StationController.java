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

import ru.namazov.asow.dto.StationDTO;
import ru.namazov.asow.entity.Station;
import ru.namazov.asow.mapper.StationMapper;
import ru.namazov.asow.service.StationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/station")
@AllArgsConstructor
@SecurityRequirement(name = "authenticated")
public class StationController {

    private final StationMapper stationMapper;
    private final StationService stationService;

    @PostMapping
    public ResponseEntity<StationDTO> save(@RequestBody StationDTO stationDTO) {
        return ResponseEntity.ok(stationMapper.toDTO(stationService.save(stationMapper.toEntity(stationDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StationDTO> put(@RequestBody StationDTO stationDTO, @PathVariable(name = "id") Long id) {
        Station station = stationService.findById(id);
        station.setName(stationDTO.getName());
        return ResponseEntity.ok(stationMapper.toDTO(stationService.save(station)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(stationMapper.toDTO(stationService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Station station = stationService.findById(id);
        stationService.delete(station);
    }
}
