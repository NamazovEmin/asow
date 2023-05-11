package ru.namazov.asow.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.Wagon;
import ru.namazov.asow.repository.WagonRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WagonService {

    private final WagonRepository wagonRepository;

    public Wagon save(Wagon wagon) {
        return wagonRepository.save(wagon);
    }

    public void update(Wagon wagon) {
        save(wagon);
    }

    public Optional<Wagon> findById(Long id) {
        return wagonRepository.findById(id);
    }

    public void delete(Wagon wagon) {
        wagonRepository.delete(wagon);
    }
}
