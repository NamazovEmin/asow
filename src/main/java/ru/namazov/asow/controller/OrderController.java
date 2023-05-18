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

import ru.namazov.asow.dto.OrderDTO;
import ru.namazov.asow.mapper.OrderMapper;
import ru.namazov.asow.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Order", description = "CRUD operations with Order")
@RestController
@RequestMapping(value = "/Order")
@AllArgsConstructor
@SecurityRequirement(name = "authenticated")
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @Operation(summary = "Order creating")
    @PostMapping
    public ResponseEntity<OrderDTO> save(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderMapper.toDTO(orderService.save(orderMapper.toEntity(orderDTO))));
    }

    @Operation(summary = "Order updating")
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> put(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderMapper.toDTO(orderService.put(orderMapper.toEntity(orderDTO))));
    }

    @Operation(summary = "Getting Order by id")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(
            @Parameter(description = "id of Order to be searched")
            @PathVariable(name = "id") Long id)
    {
        return ResponseEntity.ok(orderMapper.toDTO(orderService.findById(id)));
    }

    @Operation(summary = "Order deleting")
    @DeleteMapping("/{id}")
    public void deleteById(
            @Parameter(description = "id of Order to be deleted")
            @PathVariable Long id)
    {
        orderService.deleteById(id);
    }
}
