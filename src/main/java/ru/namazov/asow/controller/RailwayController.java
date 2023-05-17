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
import ru.namazov.asow.mapper.RailwayMapper;
import ru.namazov.asow.service.RailwayService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Railway", description = "CRUD operations with Railway")
@RestController
@RequestMapping(value = "/railway")
@AllArgsConstructor
@SecurityRequirement(name = "authenticated")
public class RailwayController {

    private final RailwayMapper railwayMapper;
    private final RailwayService railwayService;

    @Operation(summary = "Railway creating")
    @PostMapping
    public ResponseEntity<RailwayDTO> save(@RequestBody RailwayDTO railwayDTO) {
        return ResponseEntity.ok(railwayMapper.toDTO(railwayService.save(railwayMapper.toEntity(railwayDTO))));
    }

    @Operation(summary = "Railway updating")
    @PutMapping
    public ResponseEntity<RailwayDTO> put(@RequestBody RailwayDTO railwayDTO) {
        return ResponseEntity.ok(railwayMapper.toDTO(railwayService.put(railwayMapper.toEntity(railwayDTO))));
    }

    @Operation(summary = "Getting Railway by id")
    @GetMapping("/{id}")
    public ResponseEntity<RailwayDTO> findById(
            @Parameter(description = "id of Railway to be searched")
            @PathVariable(name = "id") Long id)
    {
        return ResponseEntity.ok(railwayMapper.toDTO(railwayService.findById(id)));
    }

    @Operation(summary = "Railway deleting")
    @DeleteMapping("/{id}")
    public void deleteById(
            @Parameter(description = "id of Railway to be deleted")
            @PathVariable Long id)
    {
        railwayService.deleteById(id);
    }
}
