package ru.namazov.asow.service;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.Station;
import ru.namazov.asow.exception.NotFoundException;
import ru.namazov.asow.repository.StationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    public Station save(Station station) {
        return stationRepository.save(station);
    }

    public Station findById(Long id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Cargo with id %s not found", id)));
    }

    public void delete(Station station) {
        stationRepository.delete(station);
    }
}
