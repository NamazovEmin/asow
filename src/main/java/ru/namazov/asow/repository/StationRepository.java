package ru.namazov.asow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.namazov.asow.entity.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}
