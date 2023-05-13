package ru.namazov.asow.service;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.Cargo;
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
        return save(cargo);
    }

    public void delete(Cargo cargo) {
        cargoRepository.delete(cargo);
    }

    public Cargo findCargoByCode(Long code) {
        return cargoRepository.findCargoByCode(code).orElseThrow();
    }

    public Cargo findById(Long id) {
        return cargoRepository.findById(id).orElseThrow();
    }
}
