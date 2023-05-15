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

    public Station put(Station station) {
        stationRepository.findById(station.getId()).orElseThrow(() -> new NotFoundException(String.format("current Station with id %s not found to update", station.getId())));
        return stationRepository.save(station);
    }

    public Station findById(Long id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Station with id %s not found", id)));
    }

    public void deleteById(Long id) {
        stationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Station with id %s not found", id)));
        stationRepository.deleteById(id);
    }
}
