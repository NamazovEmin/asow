package ru.namazov.asow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.namazov.asow.entity.WagonPassport;

@Repository
public interface WagonPassportRepository extends JpaRepository<WagonPassport, Long> {
}
