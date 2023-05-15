package ru.namazov.asow.service;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.Order;
import ru.namazov.asow.exception.NotFoundException;
import ru.namazov.asow.repository.TrainRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrainService {

    private final TrainRepository trainRepository;

    public Order save(Order order) {
        return trainRepository.save(order);
    }

    public Order put(Order order) {
        trainRepository.findById(order.getId()).orElseThrow(() -> new NotFoundException(String.format("current Train with id %s not found to update", order.getId())));
        return trainRepository.save(order);
    }

    public Order findById(Long id) {
        return trainRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Cargo with id %s not found", id)));
    }

    public void delete(Order order) {
        trainRepository.delete(order);
    }
}
