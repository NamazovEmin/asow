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
import ru.namazov.asow.mapper.WagonPassportMapper;
import ru.namazov.asow.service.WagonPassportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.namazov.asow.dictionary.WagonControllerDictionary.*;

@Tag(name = "WagonPassport", description = "CRUD operations with WagonPassport")
@RestController
@RequestMapping(value = "/wagonpassport")
@AllArgsConstructor
@SecurityRequirement(name = "authenticated")
public class WagonPassportController {

    private final WagonPassportMapper wagonPassportMapper;
    private final WagonPassportService wagonPassportService;

    @Operation(summary = "WagonPassport creating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the user",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = WagonPassportDTO.class),
                            examples = {@ExampleObject(value = EXAMPLE_REQUEST_BODY_CREATE_WAGON_PASSPORT)})})
    })
    @PostMapping
    public ResponseEntity<WagonPassportDTO> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User to be updated",
            required = true, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = WagonPassportDTO.class),
            examples = {@ExampleObject(value = EXAMPLE_REQUEST_BODY_CREATE_WAGON_PASSPORT)}))
            @RequestBody WagonPassportDTO wagonPassportDTO)
    {
        return ResponseEntity.ok(wagonPassportMapper.toDTO(wagonPassportService.save(wagonPassportMapper.toPOSTEntity(wagonPassportDTO))));
    }

    @Operation(summary = "WagonPassport updating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the user",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject(value = EXAMPLE_RESPONSE_UPDATE_WAGON_PASSPORT_OK_200)})})})
            @PutMapping
    public ResponseEntity<WagonPassportDTO> put(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User to be updated",
                    required = true, content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = WagonPassportDTO.class),
                    examples = {@ExampleObject(value = EXAMPLE_REQUEST_BODY_UPDATE_WAGON_PASSPORT)}))
            @RequestBody WagonPassportDTO wagonPassportDTO)
    {
        return ResponseEntity.ok(wagonPassportMapper.toDTO(wagonPassportService.put(wagonPassportMapper.toPOSTEntity(wagonPassportDTO))));
    }

    @Operation(summary = "Getting WagonPassport by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the WagonPassport",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = WagonPassportDTO.class),
                            examples = {@ExampleObject(value = EXAMPLE_RESPONSE_GET_WAGON_PASSPORT_OK_200)})})})
            @GetMapping("/{id}")
    public ResponseEntity<WagonPassportDTO> findById(
            @Parameter(description = "id of WagonPassport to be searched")
            @PathVariable(name = "id") Long id)
    {
        return ResponseEntity.ok(wagonPassportMapper.toDTO(wagonPassportService.findById(id)));
    }

    @Operation(summary = "WagonPassport deleting")
    @DeleteMapping("/{id}")
    public void deleteById(
            @Parameter(description = "id of WagonPassport to be deleted")
            @PathVariable Long id) {
        wagonPassportService.deleteById(id);
    }
}
