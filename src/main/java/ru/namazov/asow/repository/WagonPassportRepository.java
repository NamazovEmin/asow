package ru.namazov.asow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.namazov.asow.entity.WagonPassport;

@Repository
public interface WagonPassportRepository extends JpaRepository<WagonPassport, Long> {
    Optional<WagonPassport> findBySerialNumber(Long serialNumber);
}
