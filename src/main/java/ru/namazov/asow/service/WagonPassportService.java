package ru.namazov.asow.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.WagonPassport;
import ru.namazov.asow.repository.WagonPassportRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WagonPassportService {

    private final WagonPassportRepository wagonPassportRepository;

    public WagonPassport save(WagonPassport wagonPassport) {
        return wagonPassportRepository.save(wagonPassport);
    }

    public void update(WagonPassport wagonPassport) {
        save(wagonPassport);
    }

    public Optional<WagonPassport> findById(Long id) {
        return wagonPassportRepository.findById(id);
    }

    public void delete(WagonPassport wagonPassport) {
        wagonPassportRepository.delete(wagonPassport);
    }
}
