package ru.namazov.asow.service;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.Cargo;
import ru.namazov.asow.exception.NotFoundException;
import ru.namazov.asow.repository.CargoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CargoService {

    private final CargoRepository cargoRepository;

    public Cargo save(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    public Cargo put(Cargo cargo) {
        cargoRepository.findById(cargo.getId()).orElseThrow(() -> new NotFoundException(String.format("current Cargo with id %s not found to update", cargo.getId())));
        return cargoRepository.save(cargo);
    }

    public Cargo findById(Long id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Cargo with id %s not found", id)));
    }

    public void deleteById(Long id) {
        cargoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Cargo with id %s not found", id)));
        cargoRepository.deleteById(id);
    }
}
