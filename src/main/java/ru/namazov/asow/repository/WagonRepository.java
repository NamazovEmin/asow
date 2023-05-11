package ru.namazov.asow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.namazov.asow.entity.Wagon;

@Repository
public interface WagonRepository extends JpaRepository<Wagon, Long> {
}
