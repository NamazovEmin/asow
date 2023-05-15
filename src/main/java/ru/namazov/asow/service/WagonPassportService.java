package ru.namazov.asow.service;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.WagonPassport;
import ru.namazov.asow.exception.NotFoundException;
import ru.namazov.asow.repository.WagonPassportRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WagonPassportService {

    private final WagonPassportRepository wagonPassportRepository;

    public WagonPassport save(WagonPassport wagonPassport) {
        return wagonPassportRepository.save(wagonPassport);
    }

    public WagonPassport put(WagonPassport wagonPassport) {
        wagonPassportRepository.findById(wagonPassport.getId()).orElseThrow(() -> new NotFoundException(String.format("current WagonPassport with id %s not found to update", wagonPassport.getId())));
        return wagonPassportRepository.save(wagonPassport);
    }

    public WagonPassport findById(Long id) {
        return wagonPassportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("WagonPassport with id %s not found", id)));
    }

    public void deleteById(Long id) {
        wagonPassportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("WagonPassport with id %s not found", id)));
        wagonPassportRepository.deleteById(id);
    }
}
