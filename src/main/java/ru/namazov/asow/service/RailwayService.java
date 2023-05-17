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
    private final StationService stationService;

    public Railway save(Railway railway) {
//        railway.setStation(stationService.findById(railway.getStation().getId()));
        return railwayRepository.save(railway);
    }

    public Railway put(Railway railway) {
        railwayRepository.findById(railway.getId()).orElseThrow(() -> new NotFoundException(String.format("current Railway with id %s not found to update", railway.getId())));
        return railwayRepository.save(railway);
    }

    public Railway findById(Long id) {
        return railwayRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Railway with id %s not found", id)));
    }

    public void deleteById(Long id) {
        railwayRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Railway with id %s not found", id)));
        railwayRepository.deleteById(id);
    }
}
