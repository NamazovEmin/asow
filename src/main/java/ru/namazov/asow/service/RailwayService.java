package ru.namazov.asow.service;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.Railway;
import ru.namazov.asow.exception.NotFoundException;
import ru.namazov.asow.repository.RailwayRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RailwayService {

    private final RailwayRepository railwayRepository;

    public Railway save(Railway railway) {
        return railwayRepository.save(railway);
    }

    public Railway findById(Long id) {
        return railwayRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Cargo with id %s not found", id)));
    }

    public void delete(Railway railway) {
        railwayRepository.delete(railway);
    }
}
