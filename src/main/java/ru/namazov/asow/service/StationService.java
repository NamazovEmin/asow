package ru.namazov.asow.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.Station;
import ru.namazov.asow.repository.StationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    public Station save(Station station) {
        return stationRepository.save(station);
    }

    public void update(Station station) {
        save(station);
    }

    public Optional<Station> findById(Long id) {
        return stationRepository.findById(id);
    }

    public void delete(Station station) {
        stationRepository.delete(station);
    }
}
