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
import ru.namazov.asow.dto.WagonPassportDTO;
import ru.namazov.asow.mapper.StationMapper;
import ru.namazov.asow.service.StationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.namazov.asow.dictionary.StationControllerDictionary.EXAMPLE_REQUEST_BODY_CREATE_STATION;

@Tag(name = "Station", description = "CRUD operations with Station")
@RestController
@RequestMapping(value = "/station")
@AllArgsConstructor
@SecurityRequirement(name = "authenticated")
public class StationController {

    private final StationMapper stationMapper;
    private final StationService stationService;

    @Operation(summary = "Station creating")
    @PostMapping
    public ResponseEntity<StationDTO> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User to be updated",
                    required = true, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = WagonPassportDTO.class),
                    examples = {@ExampleObject(value = EXAMPLE_REQUEST_BODY_CREATE_STATION)}))
            @RequestBody StationDTO stationDTO) {
        return ResponseEntity.ok(stationMapper.toDTO(stationService.save(stationMapper.toEntity(stationDTO))));
    }

    @Operation(summary = "Station updating")
    @PutMapping("/{id}")
    public ResponseEntity<StationDTO> put(@RequestBody StationDTO stationDTO) {
        return ResponseEntity.ok(stationMapper.toDTO(stationService.put(stationMapper.toEntity(stationDTO))));
    }

    @Operation(summary = "Getting Station by id")
    @GetMapping("/{id}")
    public ResponseEntity<StationDTO> findById(
            @Parameter(description = "id of Station to be searched")
            @PathVariable(name = "id") Long id)
    {
        return ResponseEntity.ok(stationMapper.toDTO(stationService.findById(id)));
    }

    @Operation(summary = "Station deleting")
    @DeleteMapping("/{id}")
    public void deleteById(
            @Parameter(description = "id of Station to be deleted")
            @PathVariable Long id)
    {
        stationService.deleteById(id);
    }
}
