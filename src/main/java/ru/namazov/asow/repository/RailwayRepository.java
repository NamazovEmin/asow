package ru.namazov.asow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.namazov.asow.entity.Railway;

@Repository
public interface RailwayRepository extends JpaRepository<Railway, Long> {
}
