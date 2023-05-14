package ru.namazov.asow.service;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.Train;
import ru.namazov.asow.exception.NotFoundException;
import ru.namazov.asow.repository.TrainRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrainService {

    private final TrainRepository trainRepository;

    public Train save(Train train) {
        return trainRepository.save(train);
    }

    public Train findById(Long id) {
        return trainRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Cargo with id %s not found", id)));
    }

    public void delete(Train train) {
        trainRepository.delete(train);
    }
}
