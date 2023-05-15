package ru.namazov.asow.service;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.Order;
import ru.namazov.asow.exception.NotFoundException;
import ru.namazov.asow.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order put(Order order) {
        orderRepository.findById(order.getId()).orElseThrow(() -> new NotFoundException(String.format("current Train with id %s not found to update", order.getId())));
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Cargo with id %s not found", id)));
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
