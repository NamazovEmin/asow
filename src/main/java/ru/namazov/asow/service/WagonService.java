package ru.namazov.asow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.Wagon;
import ru.namazov.asow.exception.NotFoundException;
import ru.namazov.asow.repository.WagonRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WagonService {

    private final WagonRepository wagonRepository;

    public Wagon save(Wagon wagon) {
        return wagonRepository.save(wagon);
    }

    public Wagon put(Wagon wagon) {
        wagonRepository.findById(wagon.getId()).orElseThrow(() -> new NotFoundException(String.format("current Wagon with id %s not found to update", wagon.getId())));
        return wagonRepository.save(wagon);
    }

    public Wagon findById(Long id) {
        return wagonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Wagon with id %s not found", id)));
    }

    public void delete(Wagon wagon) {
        wagonRepository.delete(wagon);
    }

    public List<Wagon> saveAll(List<Wagon> wagonList) {
        return wagonRepository.saveAll(wagonList);
    }
}
