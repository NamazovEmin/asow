package ru.namazov.asow.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.Train;
import ru.namazov.asow.repository.TrainRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrainService {

    private final TrainRepository trainRepository;

    public Train save(Train train) {
        return trainRepository.save(train);
    }

    public void update(Train train) {
        save(train);
    }

    public Optional<Train> findById(Long id) {
        return trainRepository.findById(id);
    }

    public void delete(Train train) {
        trainRepository.delete(train);
    }
}
